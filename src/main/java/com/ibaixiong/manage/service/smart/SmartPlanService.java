/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.smart;

import java.util.List;

import com.ibaixiong.constant.Constant.PlanStatus;
import com.ibaixiong.entity.SmartPlan;



/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年8月6日-下午12:13:11
 */
public interface SmartPlanService {
	List<SmartPlan> getSmartPlanListByBxcode(String bxcode, PlanStatus planStatus);
}
