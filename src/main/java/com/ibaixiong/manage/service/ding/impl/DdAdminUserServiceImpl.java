package com.ibaixiong.manage.service.ding.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.DdAdminUser;
import com.ibaixiong.manage.dao.ding.DdAdminUserDao;
import com.ibaixiong.manage.service.ding.DdAdminUserService;
@Service
public class DdAdminUserServiceImpl implements DdAdminUserService {
	@Resource
	private DdAdminUserDao ddAdminUserDao;
	@Override
	public void deleteByAdminId(String adminId) {
		ddAdminUserDao.deleteByAdminId(adminId);
	}
	@Override
	public void insert(DdAdminUser adminUser) {
		ddAdminUserDao.insertSelective(adminUser);
	}
	
}
