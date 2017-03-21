package com.ibaixiong.manage.service.crm.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.SsssCityMerchant;
import com.ibaixiong.entity.SsssInfo;
import com.ibaixiong.entity.SsssOrder;
import com.ibaixiong.entity.util.OrderStatusEnum;
import com.ibaixiong.entity.util.ServiceBackStatusEnum;
import com.ibaixiong.manage.dao.crm.SsssCityMerchantDao;
import com.ibaixiong.manage.dao.crm.SsssInfoDao;
import com.ibaixiong.manage.dao.crm.SsssOrderDao;
import com.ibaixiong.manage.service.crm.SsssOrderService;
import com.papabear.order.api.AfterSaleService;
import com.papabear.order.api.OrderService;
import com.papabear.order.entity.MallAfterSalesService;
import com.papabear.order.entity.MallOrder;
import com.papabear.order.entity.MallOrderItem;
import com.papabear.order.entity.OrderConstant.OrderStatus;
import com.papabear.pay.api.PayService;

@Service
public class SsssOrderServiceImpl implements SsssOrderService {
	Logger logger=LoggerFactory.getLogger(this.getClass());
	@Resource
	private SsssOrderDao ssssOrderDao;
	@Resource
	private SsssInfoDao ssssInfoDao;
	@Resource
	private SsssCityMerchantDao ssssCityMerchantDao;
	@Resource
	private PayService payService;
	@Resource
	private OrderService orderService;
	@Resource
	AfterSaleService afterSaleService;
	@Override
	public void ssssOrderProfitConvert(Date date) {
		List<SsssOrder> list = ssssOrderDao.getOrderByFreezedMoney(Constant.Status.WAIT.getStatus(), date);
		if (list != null && list.size() > 0) {
			for (SsssOrder ssssOrder : list) {
				if (ssssOrder != null) {
					// 判断订单是否为完成状态，完成了才可以进行如下操作
					// MallOrder order =
					// mallOrderDao.getByOrderNumber(ssssOrder.getOrderNumber());
					MallOrder order = orderService.getMallOrder(ssssOrder.getOrderNumber());
					if (order != null) {
						if (order.getStatus().intValue() == (int) OrderStatusEnum.COMPLETED.getCode()
								|| order.getStatus().intValue() == (int) OrderStatusEnum.AFTERSALE.getCode()) {
							// 将金额设置添加移入到可用余额中
							Long ssssId = ssssOrder.getSsssId();
							Long merchantId = ssssOrder.getMerchantId();
							// 设置资金变动相关表信息
							Float money = null;
							Long userId = null;
							if (ssssId != null && ssssId.intValue() > 0) {
								// 说明是4s店,修改余额字段,冻结金额字段
								SsssInfo ssssInfo = ssssInfoDao.selectByPrimaryKey(ssssId);
								money = ssssOrder.getProfit();
								userId = ssssInfo.getUserId();
							}
							if (merchantId != null && merchantId.intValue() > 0) {
								// 说明是代理商,修改余额字段,冻结金额字段
								SsssCityMerchant merchant = ssssCityMerchantDao.selectByPrimaryKey(merchantId);
								merchant.setMoney((merchant.getMoney()==null?0:merchant.getMoney())+ssssOrder.getProfit());
								System.out.println(merchant.getFreezedMoney()-ssssOrder.getProfit()+"....");
								merchant.setFreezedMoney(merchant.getFreezedMoney()-ssssOrder.getProfit());
								ssssCityMerchantDao.updateByPrimaryKeySelective(merchant);
								money = ssssOrder.getProfit();
								userId = merchant.getUserId();
							}
							String remark = "返利，订单号：" + order.getOrderNumber();
							logger.info("订单号是{}，4s店利润是{}，用户ID{}，4S店ID{},代理商ID{}",order.getOrderNumber(),ssssOrder.getProfit(),userId,ssssId,merchantId);
							payService.createReBatePay(ssssOrder.getProfit(), userId, ssssId, merchantId, remark);
							// 修改订单状态为正常使用，金额生效
							ssssOrder.setStatus(Constant.Status.NORMAL.getStatus());
							ssssOrderDao.updateByPrimaryKeySelective(ssssOrder);
						}
					}
				}
			}
		}
	}

	@Override
	public List<SsssOrder> selectByMerchantId(Long merchantId,Integer pageNo) {
		PageHelper page = new PageHelper();
		page.startPage(pageNo, PageConstant.bbspageSize, true);
		return ssssOrderDao.selectByMerchantId(merchantId);
	}

	@Override
	public List<SsssOrder> selectByInfoId(Long id, Integer pageNo) {
		PageHelper page = new PageHelper();
		page.startPage(pageNo, PageConstant.bbspageSize, true);
		return ssssOrderDao.selectByInfoId(id);
	}

	@Override
	public void ssssAfterOrderProfit(Date date) {
		//查询订单状态为售后服务中的订单
		List<MallAfterSalesService> afterSalesService = afterSaleService.queryServiceByStatus(OrderStatus.AFTERSALE.getStatus());
		for(MallAfterSalesService afterSale : afterSalesService){
			if(afterSale!=null){
				SsssOrder ssssOrder = ssssOrderDao.getSsssByOrderNumber(afterSale.getOrderNumber());
				//判断订单是否在脱货支持的范围之内
				if(ssssOrder!=null && ssssOrder.getStatus()==0 && ssssOrder.getCreateDateTime().getTime()>date.getTime()){
					Long cityId = ssssOrder.getMerchantId();
					//判断退款订单是否被同意
					if(afterSale.getStatus()>ServiceBackStatusEnum.BACK_AGREE_REFUSE.getCode() && afterSale.getStatus()!=ServiceBackStatusEnum.BACK_REFUSE.getCode()){
						SsssCityMerchant city = ssssCityMerchantDao.selectByPrimaryKey(cityId);
						city.setFreezedMoney(city.getFreezedMoney()-ssssOrder.getProfit());
						ssssCityMerchantDao.updateByPrimaryKeySelective(city);
						// 修改订单状态为退货订单，金额不生效
						ssssOrder.setStatus(Constant.Status.DELETE.getStatus());
						ssssOrderDao.updateByPrimaryKeySelective(ssssOrder);
					}
				}
			}
		}
//		List<SsssOrder> list = ssssOrderDao.getOrderByFreezedMoneyList(Constant.Status.WAIT.getStatus(), date);
//		for(SsssOrder ssssOrder : list){
//			if(ssssOrder !=null){
//				MallOrder order = orderService.getMallOrder(ssssOrder.getOrderNumber());
//				if(order!=null && order.getStatus().intValue() == (int)OrderStatusEnum.AFTERSALE.getCode()){
//					List<MallOrderItem> orderItems = orderService.queryOrderItems(order.getOrderNumber());
//					for(MallOrderItem item : orderItems){
//						
//					}
//				}
//				MallAfterSalesService afterSale = afterSaleService.getByOrderNumber(ssssOrder.getOrderNumber());
//				Long cityId = ssssOrder.getMerchantId();
//				Long infoId = ssssOrder.getId();
//				if(afterSale!=null && afterSale.getStatus()==41){
//					SsssCityMerchant city = ssssCityMerchantDao.selectByPrimaryKey(cityId);
//					System.out.println(city.getFreezedMoney()+","+ssssOrder.getProfit()+","+(city.getFreezedMoney()-ssssOrder.getProfit()));
//					city.setFreezedMoney(city.getFreezedMoney()-ssssOrder.getProfit());
//					ssssCityMerchantDao.updateByPrimaryKeySelective(city);
//					// 修改订单状态为退货订单，金额不生效
//					ssssOrder.setStatus(Constant.Status.DELETE.getStatus());
//				}
//				ssssOrderDao.updateByPrimaryKeySelective(ssssOrder);
//			}
//		}
	}

	@Override
	public SsssOrder getSsssOrderByOrderNumber(String orderNumber) {
		// TODO Auto-generated method stub
		return ssssOrderDao.getSsssByOrderNumber(orderNumber);
	}
}
