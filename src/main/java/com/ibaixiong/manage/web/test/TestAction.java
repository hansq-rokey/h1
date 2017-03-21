/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.test;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ibaixiong.entity.util.OrderStatusEnum;
import com.ibaixiong.manage.service.crm.SsssInvitationCodeService;
import com.ibaixiong.manage.service.crm.SsssOrderService;
import com.ibaixiong.manage.service.mall.MallOrderService;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年7月2日-下午5:49:12
 */
@Controller
@RequestMapping("/test")
public class TestAction {
	@Resource
	private MallOrderService mallOrderService;
	@Resource
	private SsssOrderService ssssOrderService;
	@Resource
	private SsssInvitationCodeService ssssInvitationCodeService;
	// 去添加或修改用户
		@RequestMapping("/to0")
		public String to0(Model model) {
			return "/test/index";
		}
	// 去添加或修改用户
	@RequestMapping("/to1")
	public String to1(Model model) {
		//订单完成
		mallOrderService.completeOrdersList(OrderStatusEnum.SIPPING, new Date());
		return "/test/index";
	}
	@RequestMapping("/to2")
	public String to2(Model model) {
		//4s店计算冻结金额转到余额任务调度
		ssssOrderService.ssssOrderProfitConvert(new Date());
		return "/test/index";
	}
	@RequestMapping("/to3")
	public String to3(Model model) {
		//邀请码未使用收回
		ssssInvitationCodeService.reset(new Date());
		return "/test/index";
	}
}
