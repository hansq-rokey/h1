/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.smart.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibaixiong.entity.SmartUser;
import com.ibaixiong.manage.dao.bbs.UserDao;
import com.ibaixiong.manage.dao.smart.SmartUserDao;
import com.ibaixiong.manage.service.smart.SmartPlanService;
import com.ibaixiong.manage.service.smart.SmartService;
import com.ibaixiong.manage.service.smart.SmartUserService;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年8月12日-下午3:39:38
 */
@Service
@Transactional
public class SmartUserServiceImpl implements SmartUserService {
	@Autowired
	SmartUserDao smartUserDao;
	@Autowired
	SmartService smartService;
	@Autowired
	SmartPlanService smartPlanService;
	@Autowired
	UserDao userDao;

	@Override
	public boolean removeBindSmartUser(String bxid, String bxcode, Integer userId, Integer removeUserId) {
		// 判断该用户是否为自己解绑
		if (userId.equals(removeUserId)) {
			smartUserDao.deleteByBxcodeAndUserId(bxcode, userId);
		} else {

			smartUserDao.deleteByBxcodeAndUserId(bxcode, removeUserId);
		}
		return true;
	}

	@Override
	public SmartUser getSmartByBxcodeUserId(String bxcode, Integer userId) {
		return smartUserDao.selectByBxcodeAndUserId(bxcode, userId);
	}
}
