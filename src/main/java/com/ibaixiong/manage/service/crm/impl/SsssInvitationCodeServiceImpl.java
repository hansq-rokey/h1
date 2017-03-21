package com.ibaixiong.manage.service.crm.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.SsssInfo;
import com.ibaixiong.entity.SsssInvitationCode;
import com.ibaixiong.manage.dao.crm.SsssInfoDao;
import com.ibaixiong.manage.dao.crm.SsssInvitationCodeDao;
import com.ibaixiong.manage.service.crm.SsssInvitationCodeService;
import com.papabear.order.api.OrderService;
import com.papabear.order.entity.MallOrder;
import com.papabear.pay.api.PayService;

/**
 * 邀请码
 * 
 * @author yaoweiguo
 * @Email yaoweiguo@ibaixiong.com
 * @Description TODO
 * @date 2015年12月29日
 *
 */
@Service
public class SsssInvitationCodeServiceImpl implements SsssInvitationCodeService {

	@Resource
	private SsssInvitationCodeDao invitationCodeDao;
	@Resource
	private OrderService orderService;
	// private MallOrderDao mallOrderDao;
	@Resource
	private SsssInfoDao ssssInfoDao;
	@Resource
	private PayService payService;

	@Override
	public List<SsssInvitationCode> queryInviteCodeByMobileAndProductId(String phone, List<Long> idList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phone", phone);
		map.put("ids", idList);
		return invitationCodeDao.queryInviteCodeByMobileAndProductId(map);
	}

	@Override
	public void reset(Date date) {
		List<SsssInvitationCode> list = invitationCodeDao.getListByReset(date);
		for (SsssInvitationCode ssssInvitationCode : list) {
			if (ssssInvitationCode.getStatus().intValue() == 1) {
				// 状态为已支付未使用
				// MallOrder mallOrder =
				// mallOrderDao.getByOrderNumber(ssssInvitationCode.getOrderNumber());
				MallOrder mallOrder = orderService.getMallOrder(ssssInvitationCode.getOrderNumber());
				if (mallOrder != null) {
					if (mallOrder.getStatus().intValue() == 10) {// 订单是未付款的
						Calendar now = Calendar.getInstance();
						now.add(Calendar.HOUR, -12);
						// 说明是过期的订单，如果未过期邀请码不发生改变
						if (mallOrder.getCreateDateTime().getTime() < now.getTime().getTime()) {
							// 这时邀请码的钱可以返回了
							setInvitationCode(ssssInvitationCode);
						}
					} else if (mallOrder.getStatus().intValue() == 60) {// 说明订单已关闭了
						setInvitationCode(ssssInvitationCode);
					}
				} else {
					// 邀请码没有跟订单绑定直接返回
					setInvitationCode(ssssInvitationCode);
				}
			}
		}
	}

	// TODO pay...
	private void setInvitationCode(SsssInvitationCode ssssInvitationCode) {
		ssssInvitationCode.setStatus((byte) -1);// 改为失效
		invitationCodeDao.updateByPrimaryKeySelective(ssssInvitationCode);
		// 4.修改4S店的余额
		if (ssssInvitationCode.getSsssId() != null && ssssInvitationCode.getSsssId().intValue() > 0) {
			SsssInfo ssssInfo = ssssInfoDao.selectByPrimaryKey(ssssInvitationCode.getSsssId());
			String remark = "邀请码(" + ssssInvitationCode.getId() + ")失效，金额返回4S店(" + ssssInfo.getId() + ")";
			payService.createInvitationCodeDisableBackPay(ssssInvitationCode.getMoney(), ssssInfo.getUserId(), ssssInfo.getId(), null, remark);
		}
	}
}
