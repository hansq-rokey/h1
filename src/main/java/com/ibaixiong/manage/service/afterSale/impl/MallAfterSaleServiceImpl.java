package com.ibaixiong.manage.service.afterSale.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.SsssCityMerchant;
import com.ibaixiong.entity.SsssInfo;
import com.ibaixiong.entity.SsssInvitationCode;
import com.ibaixiong.entity.SsssMerchantFormatPrice;
import com.ibaixiong.entity.SsssOrder;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.manage.dao.crm.SsssCityMerchantDao;
import com.ibaixiong.manage.dao.crm.SsssInfoDao;
import com.ibaixiong.manage.dao.crm.SsssInvitationCodeDao;
import com.ibaixiong.manage.dao.crm.SsssMerchantFormatPriceDao;
import com.ibaixiong.manage.dao.crm.SsssOrderDao;
import com.ibaixiong.manage.service.afterSale.MallAfterSaleService;
import com.papabear.order.api.AfterSaleService;
import com.papabear.order.api.OrderService;
import com.papabear.order.entity.MallAfterSalesService;
import com.papabear.order.entity.MallAfterSalesServiceItem;
import com.papabear.order.entity.MallOrderItem;
import com.papabear.order.entity.OrderConstant.ServiceBackStatusEnum;
import com.papabear.pay.api.PayService;
import com.papabear.product.api.CategoryQueryService;
import com.papabear.product.entity.MallBasicCategoryModelFormat;

@Service
public class MallAfterSaleServiceImpl implements MallAfterSaleService {

	@Resource
	private SsssOrderDao ssssOrderDao;
	@Resource
	private SsssInvitationCodeDao ssssInvitationCodeDao;
	@Resource
	private OrderService orderService;
	@Resource
	private CategoryQueryService categoryQueryService;
	@Resource
	private SsssMerchantFormatPriceDao ssssMerchantFormatPriceDao;
	@Resource
	private SsssCityMerchantDao ssssCityMerchantDao;
	@Resource
	private SsssInfoDao ssssInfoDao;
	@Resource
	private PayService payService;
	@Resource
	private AfterSaleService afterSaleService;

	@Override
	public int confirmRefund(Long serviceId, SysAdmin loginAdmin) {
		int flag = 0;
		MallAfterSalesService mallAfterSales = afterSaleService.getAfterSalesService(serviceId);
		if (mallAfterSales != null) {
			if(mallAfterSales.getStatus()==ServiceBackStatusEnum.BACK_AGREE.getCode()){
				flag = afterSaleService.updateAfterSalesService(ServiceBackStatusEnum.BACK_REIMBURSED.getCode(), null, serviceId);
				afterSaleService.addAfterSalesServiceLog(loginAdmin.getId(), serviceId, ServiceBackStatusEnum.BACK_REIMBURSED.getCode());
				String orderNumber = mallAfterSales.getOrderNumber();
				// 如何查询本次退货的信息是否涉及到4S店邀请码使用
				List<SsssOrder> orderList = ssssOrderDao.getOrderByNumber(orderNumber);
				if (orderList != null && orderList.size() > 0) {// 说明是4s店订单，邀请码订单下级4s店订单
					// List<MallAfterSalesServiceItem> mallAfterSalesServiceItem =
					// mallAfterSalesServiceItemDao.queryAfterSalesServiceItemByServiceId(mallAfterSales.getId());
					List<MallAfterSalesServiceItem> mallAfterSalesServiceItem = afterSaleService.queryAfterSalesServiceItems(mallAfterSales.getId());

					// 计算需要减去的返利
					// 下探本次这个订单是否使用了邀请码
					/**
					 * key:formatId,value:num 产品规格ID,退货数量
					 */
					Map<Long, Float> salesMap = new HashMap<Long, Float>();
					for (MallAfterSalesServiceItem mallAfterSalesServiceItem2 : mallAfterSalesServiceItem) {
						salesMap.put(mallAfterSalesServiceItem2.getProductModelFormatId(), mallAfterSalesServiceItem2.getNum());
					}
					// 查询销售订单详情
					Set<Long> set = salesMap.keySet();
					/**
					 * key:itemId,value:formatId 销售订单详情ID,产品规格ID
					 */
					Map<Long, Long> orderItemMap = new HashMap<Long, Long>();
					/**
					 * key:formatId,value:pric 产品规格ID,原价
					 */
					Map<Long, Float> formatMap = new HashMap<Long, Float>();
					List<Long> itemIds = new ArrayList<Long>();
					for (Long long1 : set) {
						MallOrderItem mallOrderItem = orderService.getMallOrderItem(orderNumber, long1);
						// MallOrderItem mallOrderItem =
						// mallOrderItemDao.getByOrderNumberAndFormatId(orderNumber,
						// long1);
						if (mallOrderItem != null) {
							orderItemMap.put(mallOrderItem.getId(), long1);
							itemIds.add(long1);
						}
						MallBasicCategoryModelFormat modelFormat = categoryQueryService.getCategoryModelFormat(long1);
						if (modelFormat != null)
							formatMap.put(long1, modelFormat.getPrice());
					}
					// 查询是否使用了邀请码
					/**
					 * key:itemId,value:invitationCode 销售订单详情ID,邀请码对象
					 */
					Map<Long, SsssInvitationCode> invitationCodeMap = new HashMap<Long, SsssInvitationCode>();
					Long ssssId = 0L;// 4s店的ID，如果使用了邀请码这个值肯定是存在的
					List<SsssInvitationCode> ssssInvitationCodeList = ssssInvitationCodeDao.getListByOrderItemId(itemIds);
					for (SsssInvitationCode ssssInvitationCode : ssssInvitationCodeList) {
						if (ssssInvitationCode.getStatus().intValue() != -1) {
							invitationCodeMap.put(ssssInvitationCode.getOrderItemId(), ssssInvitationCode);
							if (ssssId.intValue() == 0)
								ssssId = ssssInvitationCode.getSsssId();
						}
					}
					// 先计算
					for (SsssOrder ssssOrder : orderList) {
						// 说明找到了4S店相关Id
						if (ssssOrder.getSsssId() != null && ssssOrder.getSsssId().intValue() > 0)
							ssssId = ssssOrder.getSsssId();
					}
					// 4s店对象
					SsssInfo sinfo = ssssInfoDao.selectByPrimaryKey(ssssId);
					// 4s店代理商这一层肯定有
					SsssCityMerchant ssssCityMerchant1 = ssssCityMerchantDao.selectByPrimaryKey(sinfo.getCityMerchantId());
					SsssCityMerchant ssssCityMerchant2 = null;
					if (ssssCityMerchant1 != null && ssssCityMerchant1.getParentCityMerchantId() != null
							&& ssssCityMerchant1.getParentCityMerchantId().intValue() > 0) {
						ssssCityMerchant2 = ssssCityMerchantDao.selectByPrimaryKey(ssssCityMerchant1.getParentCityMerchantId());
					}
					/*
					 * 1：店铺订单 2：邀请码订单 3：下级4S店订单
					 */
					// 邀请码订单
					// 最终累加的金额
					// 4s店记录
					if (ssssId != null) {
						SsssOrder ssssOrder4s = ssssOrderDao.getOrderByNumberAndSssId(orderNumber, sinfo.getId());
						if (ssssOrder4s != null) {
							float money = getMoney(orderItemMap, salesMap, formatMap, invitationCodeMap, ssssId, null, null, 1);
							updateAfterOrder(ssssOrder4s, money);
						}
					}
					// 4s店上级记录
					if (ssssCityMerchant1 != null) {
						SsssOrder ssssOrder = ssssOrderDao.getOrderByNumberAndMerchantId(orderNumber, ssssCityMerchant1.getId());
						if (ssssOrder != null) {
							float money = getMoney(orderItemMap, salesMap, formatMap, invitationCodeMap, ssssId, ssssCityMerchant1.getId(), null, 2);
							updateAfterOrder(ssssOrder, money);
						}

					}
					// 4s店上上级记录
					if (ssssCityMerchant2 != null) {
						SsssOrder ssssOrder = ssssOrderDao.getOrderByNumberAndMerchantId(orderNumber, ssssCityMerchant2.getId());
						if (ssssOrder != null) {
							float money = getMoney(orderItemMap, salesMap, formatMap, invitationCodeMap, ssssId, ssssCityMerchant1.getId(),
									ssssCityMerchant2.getId(), 3);
							updateAfterOrder(ssssOrder, money);
						}
					}
				}
			}
		}
		return flag;
	}

	private float getMoney(Map<Long, Long> orderItemMap, Map<Long, Float> salesMap, Map<Long, Float> formatMap,
			Map<Long, SsssInvitationCode> invitationCodeMap, Long ssssId, Long merchantId1, Long merchantId2, int type) {
		float money = 0f;
		/**
		 * orderItemMap -- > key:itemId,value:formatId 销售订单详情ID,产品规格ID
		 */
		Set<Long> itemSet = orderItemMap.keySet();
		for (Long long1 : itemSet) {
			Long formatId = orderItemMap.get(long1);
			/**
			 * salesMap--> key:formatId,value:num 产品规格ID,退货数量
			 */
			float num = salesMap.get(formatId);
			/**
			 * formatMap --> key:formatId,value:pric 产品规格ID,原价
			 */
			float pric = formatMap.get(formatId);
			// 拿货价
			float fp = 0f;
			// 邀请码使用对象
			float invitationCodeMoney = 0f;
			// 4s店相关计算
			if (ssssId != null && type == 1) {
				SsssInvitationCode invitationCode = invitationCodeMap.get(long1);
				if (invitationCode != null) {
					invitationCodeMoney = invitationCode.getMoney();
				}
				// 查询拿货价
				SsssMerchantFormatPrice fprice = ssssMerchantFormatPriceDao.getSsssFormatPriceByFormatIdAndssssId(formatId, ssssId);
				if (fprice != null) {
					fp = fprice.getPrice();
				}
				// 金额计算方式: （原价 - 拿货价）*退货数量+邀请码
				money = money + (pric - fp) * num + invitationCodeMoney;
			}
			// 4s店上级相关计算
			if (ssssId != null && merchantId1 != null && type == 2) {
				// 查询拿货价
				// 4s店拿货价
				SsssMerchantFormatPrice fprice = ssssMerchantFormatPriceDao.getSsssFormatPriceByFormatIdAndssssId(formatId, ssssId);
				// 4s上级拿货价
				SsssMerchantFormatPrice fprice1 = ssssMerchantFormatPriceDao.getSsssMerchantFormatPriceByFormatIdAndMerchantId(formatId, merchantId1);
				if (fprice != null) {
					fp = fprice.getPrice();
				}
				float fp1 = 0f;
				if (fprice1 != null) {
					fp1 = fprice1.getPrice();
				}
				money = money + (fp - fp1) * num;
			}
			// 4s店上级相关计算
			if (merchantId1 != null && merchantId2 != null && type == 3) {
				// 查询拿货价
				// 市代价格
				SsssMerchantFormatPrice fprice = ssssMerchantFormatPriceDao.getSsssMerchantFormatPriceByFormatIdAndMerchantId(formatId, merchantId1);
				// 省代价格
				SsssMerchantFormatPrice fprice1 = ssssMerchantFormatPriceDao.getSsssMerchantFormatPriceByFormatIdAndMerchantId(formatId, merchantId2);
				if (fprice != null) {
					fp = fprice.getPrice();
				}
				float fp1 = 0f;
				if (fprice1 != null) {
					fp1 = fprice1.getPrice();
				}
				money = money + (fp - fp1) * num;
			}
		}
		return money;
	}

	private void updateAfterOrder(SsssOrder ssssOrder, float money) {
		// 将金额设置添加移入到可用余额中
		Long ssssId = ssssOrder.getSsssId();
		Long merchantId = ssssOrder.getMerchantId();
		Long userId = null;
		int status = ssssOrder.getStatus().intValue();
		// 设置资金变动相关表信息
		ssssOrder.setProfit(ssssOrder.getProfit() - money);
		if (ssssId != null && ssssId.intValue() > 0) {
			// 说明是4s店,修改余额字段,冻结金额字段
			SsssInfo ssssInfo = ssssInfoDao.selectByPrimaryKey(ssssId);
			userId = ssssInfo.getUserId();
			if (status == 1)// 说明是已生效的记录从余额中减掉
				ssssInfo.setMoney(ssssInfo.getMoney() - money);
			if (status == 0)// 说明是未生效的记录从待转余额中减掉
				ssssInfo.setFreezedMoney(ssssInfo.getFreezedMoney() - money);
			ssssInfoDao.updateByPrimaryKeySelective(ssssInfo);
			money = ssssInfo.getMoney();
		}
		if (merchantId != null && merchantId.intValue() > 0) {
			// 说明是代理商,修改余额字段,冻结金额字段
			SsssCityMerchant merchant = ssssCityMerchantDao.selectByPrimaryKey(merchantId);
			userId = merchant.getUserId();
			if (status == 1)// 说明是已生效的记录从余额中减掉
				merchant.setMoney(merchant.getMoney() - money);
			if (status == 0)// 说明是未生效的记录从待转余额中减掉
				merchant.setFreezedMoney(merchant.getFreezedMoney() - money);
			ssssCityMerchantDao.updateByPrimaryKeySelective(merchant);
			// 添加资金变动表
			money = merchant.getMoney();
		}
		// 添加
		if (status == 1) {
			payService.createBackGoodPay(money, userId, ssssId, merchantId, null);
		}
		// 修改订单状态为正常使用
		ssssOrderDao.updateByPrimaryKeySelective(ssssOrder);
	}
}
