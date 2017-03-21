/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.smart.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibaixiong.constant.Constant.PlanStatus;
import com.ibaixiong.entity.SmartPlan;
import com.ibaixiong.manage.dao.smart.SmartPlanDao;
import com.ibaixiong.manage.service.smart.SmartPlanService;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年8月12日-下午7:51:31
 */
@Service
@Transactional
public class SmartPlanServiceImpl implements SmartPlanService {

	@Autowired
	SmartPlanDao smartPlanDao;

	@Override
	public List<SmartPlan> getSmartPlanListByBxcode(String bxcode, PlanStatus planStatus) {
		return smartPlanDao.selectListByBxcode(bxcode, planStatus.getStatus());
	}

}
