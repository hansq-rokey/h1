package com.ibaixiong.manage.service.mall.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.constant.Constant;
import com.ibaixiong.entity.MerchantCouponMoneyRecord;
import com.ibaixiong.entity.SsssCityMerchant;
import com.ibaixiong.entity.SsssInfo;
import com.ibaixiong.entity.SsssInvitationCode;
import com.ibaixiong.entity.SsssOrder;
import com.ibaixiong.entity.User;
import com.ibaixiong.entity.util.OrderStatusEnum;
import com.ibaixiong.manage.dao.crm.SsssCityMerchantDao;
import com.ibaixiong.manage.dao.crm.SsssInfoDao;
import com.ibaixiong.manage.dao.crm.SsssInvitationCodeDao;
import com.ibaixiong.manage.dao.crm.SsssOrderDao;
import com.ibaixiong.manage.dao.drp.DrpAccountDao;
import com.ibaixiong.manage.service.bbs.UserService;
import com.ibaixiong.manage.service.crm.MerchantCouponMoneyRecordService;
import com.ibaixiong.manage.service.mall.MallOrderService;
import com.papabear.commons.utils.StringUtils;
import com.papabear.dis.api.DisCUDService;
import com.papabear.dis.api.DisQueryService;
import com.papabear.dis.entity.DisBase;
import com.papabear.dis.entity.DisClaim;
import com.papabear.dis.entity.DisClaimLog;
import com.papabear.order.api.OrderService;
import com.papabear.order.entity.MallOrder;
import com.papabear.order.entity.MallOrderItem;
import com.papabear.order.entity.OrderConstant.OrderOperateTye;
import com.papabear.order.entity.OrderConstant.OrderStatus;
import com.papabear.pay.api.PayService;
import com.papabear.pay.entity.PayHistoryStatus;

@Service
public class MallOrderServiceImpl implements MallOrderService {

	@Resource
	private SsssOrderDao ssssOrderDao;
	@Resource
	private SsssInvitationCodeDao ssssInvitationCodeDao;
	@Resource
	private PayService payService;
	@Resource
	private SsssInfoDao ssssInfoDao;
	@Resource
	private OrderService orderService;
	@Resource
	private DisQueryService disQueryService;
	@Resource
	private DisCUDService disCUDService;
	@Resource
	private UserService userService;
	@Resource
	private DrpAccountDao accountDao;
	@Resource
	private SsssCityMerchantDao ssssCityMerchantDao;
	@Resource
	private MerchantCouponMoneyRecordService couponRecordService;
	@Override
	public void listObligation(OrderStatusEnum orderStatusEnum, Date endDate) {
		System.out.println("---------关闭订单定时任务接口-----------");
		List<MallOrder> list = orderService.queryTaskOrders(orderStatusEnum.getCode(), endDate);
		for (MallOrder order : list) {
			order.setStatus(OrderStatusEnum.CLOSED.getCode());
			order.setUpdateTime(new Date());
			int flag = orderService.updateOrderStatus(OrderStatus.CLOSED.getStatus(), order.getOrderNumber());
			SsssCityMerchant cityMerchant = ssssCityMerchantDao.getByUserId(order.getUserId());
			//添加优惠券记录
			MerchantCouponMoneyRecord record = new MerchantCouponMoneyRecord();
			record.setMerchantId(cityMerchant.getId());
			record.setMerchantLevel(cityMerchant.getLevel());
			record.setRemark("关闭订单返还");
			record.setStatus((byte)0);
			record.setAddUnfreezeCouponMoney(order.getEnableCouponMoney());
			record.setBeforeFreezeCouponMoney(cityMerchant.getFreezeCoupon());
			record.setAfterFreezeCouponMoney(cityMerchant.getFreezeCoupon());
			record.setBeforeUnfreezeCouponMoney(cityMerchant.getUnfreezeCoupon());
			record.setAfterUnfreezeCouponMoney(
					(cityMerchant.getUnfreezeCoupon()==null?0:cityMerchant.getUnfreezeCoupon())+(order.getEnableCouponMoney()==null?0:order.getEnableCouponMoney()));
			record.setOrderNumber(order.getOrderNumber());
			record.setCreateDateTime(new Date());
			if(order.getEnableCouponMoney()!=null && order.getEnableCouponMoney()!=0){
				couponRecordService.insert(record);
			}
			//关闭订单的同时，该订单使用的优惠券返回给代理商
			cityMerchant.setUnfreezeCoupon(
					(cityMerchant.getUnfreezeCoupon()==null?0:cityMerchant.getUnfreezeCoupon())+(order.getEnableCouponMoney()==null?0:order.getEnableCouponMoney()));
			ssssCityMerchantDao.updateByPrimaryKeySelective(cityMerchant);
			if (flag == 1) {
				//优惠券的判断
				List<DisClaimLog> claimLogs=disQueryService.queryDisClaimLogs(order.getOrderNumber());
				if(claimLogs!=null && claimLogs.size()>0){
					disCUDService.cancelDisClaim(claimLogs.get(0).getClaimId());
				}
				
				orderService.addOrderHistory(order.getOrderNumber(), null, "127.0.0.1", OrderStatus.CLOSED.getStatus(), OrderOperateTye.SYSTEM.getOperateType());
				// 使用邀请码的情况下
//				if (order.getIsUseInvitecode() != null && order.getIsUseInvitecode().intValue() == 1) {
//					// 1.修改订单表mallorder是否使用邀请码状态
//					orderService.updateOrderIsUseInviteCode((byte) 0, order.getOrderNumber());
//					// mallOrderDao.updateByPrimaryKeySelective(order);
//					// 1.1修改子表状态
//					List<MallOrderItem> listItem = orderService.queryOrderItems(null, order.getOrderNumber());
//					if (listItem != null && listItem.size() > 0) {
//						for (MallOrderItem mallOrderItem : listItem) {
//							mallOrderItem.setIsUseInvitecode((byte) 0);
//							orderService.updateOrderItem(mallOrderItem);
//						}
//					}
//					// 2.修改ssssOrder表中的订单状态
//					List<SsssOrder> listSO = ssssOrderDao.getOrderByNumber(order.getOrderNumber());
//					if (listSO != null && listSO.size() > 0) {
//						for (SsssOrder ssssOrder : listSO) {
//							ssssOrder.setStatus(Constant.Status.DELETE.getStatus());// 订单关闭说明他不生效
//							ssssOrderDao.updateByPrimaryKeySelective(ssssOrder);
//							SsssCityMerchant city = ssssCityMerchantDao.selectByPrimaryKey(ssssOrder.getMerchantId());
//							city.setFreezedMoney(city.getFreezedMoney()-ssssOrder.getProfit());
//							ssssCityMerchantDao.updateByPrimaryKeySelective(city);
//						}
//					}
//					// 3.修改邀请码表信息返回金额到4S店中
//					List<SsssInvitationCode> listSIC = ssssInvitationCodeDao.getListByOrderNumber(order.getOrderNumber());
//					if (listSIC != null && listSIC.size() > 0) {
//						for (SsssInvitationCode ssssInvitationCode : listSIC) {
//							// 1.状态为正在使用冻结中，当发生了订单失效时同时更改这个字段
//							if (ssssInvitationCode.getStatus().intValue() == 2) {
//								// 判断邀请码是否已过有效时间
//								if (ssssInvitationCode.getValidTime().getTime() < new Date().getTime()) {
//									ssssInvitationCode.setStatus((byte) -1);// 改为失效
//									ssssInvitationCodeDao.updateByPrimaryKeySelective(ssssInvitationCode);
//									// 4.修改4S店的余额
//									if (ssssInvitationCode.getSsssId() != null && ssssInvitationCode.getSsssId().intValue() > 0) {
//										SsssInfo ssssInfo = ssssInfoDao.selectByPrimaryKey(ssssInvitationCode.getSsssId());
//										String remark = "邀请码(" + ssssInvitationCode.getId() + ")失效，金额返回4S店(" + ssssInfo.getId() + ")";
//										payService.createInvitationCodeDisableBackPay(ssssInvitationCode.getMoney(), ssssInfo.getUserId(), ssssInfo.getId(),
//												null, remark);
//									}
//								} else {
//									// 未过有效期,回归状态
//									// ssssInvitationCode.setOrderItemId(null);
//									ssssInvitationCode.setStatus((byte) 1);
//									ssssInvitationCodeDao.reset(ssssInvitationCode);
//								}
//							}
//						}
//					}
//				} else {
					// 查询在SSSSorder中是否有订单相关数据
					List<SsssOrder> listSO = ssssOrderDao.getOrderByNumber(order.getOrderNumber());
					if (listSO != null && listSO.size() > 0) {
						for (SsssOrder ssssOrder : listSO) {
							ssssOrder.setStatus(Constant.Status.DELETE.getStatus());
							ssssOrderDao.updateByPrimaryKeySelective(ssssOrder);
							SsssCityMerchant city = ssssCityMerchantDao.selectByPrimaryKey(ssssOrder.getMerchantId());
							city.setFreezedMoney(city.getFreezedMoney()-ssssOrder.getProfit());
							ssssCityMerchantDao.updateByPrimaryKeySelective(city);
						}
					}
//				}

			}
		}
	}

	@Override
	public void completeOrdersList(OrderStatusEnum orderStatusEnum, Date endDate) {
		List<MallOrder> list = orderService.queryTaskOrders(orderStatusEnum.getCode(), endDate);

		for (MallOrder order : list) {
			order.setStatus(OrderStatusEnum.COMPLETED.getCode());
			order.setUpdateTime(new Date());
			// int flag=mallOrderDao.updateByPrimaryKeySelective(order);
			int flag = orderService.updateOrderStatus(OrderStatus.COMPLETED.getStatus(), order.getOrderNumber());
			if (flag == 1) {
				orderService.addOrderHistory(order.getOrderNumber(), null, "127.0.0.1", OrderStatus.COMPLETED.getStatus(),
						OrderOperateTye.SYSTEM.getOperateType());
			}
			
			//分销系统 ---家之良品
			// 查询优惠券使用情况
			List<DisClaimLog> claimLogs = disQueryService.queryDisClaimLogs(order.getOrderNumber());
			if (claimLogs.size() == 0) return;
			User user=userService.getUser(order.getUserId());
			if(user==null)return ;
			
			DisClaim disClaim = disQueryService.getDisClaim(claimLogs.get(0).getClaimId(), user.getPhone());
			if (disClaim == null || disClaim.getUserId() == null) return;
			
			DisBase disBase=disQueryService.getBaseByModelId(disClaim.getCategoryModelId());
			if(disBase==null)return ;
			
//			payService.createPay(orderNumber, disBase.getProfitMoney(), disBase.getProfitMoney(), user.getId(), PayType.DRP_REBATE, "分销系统返利", null, null);
			payService.updatePayHistoryStatusByPayNumber(order.getPayNumber(), PayHistoryStatus.FINISH);
			com.ibaixiong.entity.DrpAccount account = accountDao.getDrpAccountByUserId(user.getId());
			if(account==null)return ;
			account.setCouldTakeCash(account.getCouldTakeCash()+disBase.getProfitMoney());

			accountDao.updateByPrimaryKey(account);
		}
	}

}
