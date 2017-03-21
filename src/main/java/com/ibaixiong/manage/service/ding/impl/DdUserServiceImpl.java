package com.ibaixiong.manage.service.ding.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.constant.InvalidEnum;
import com.ibaixiong.entity.DdOrgUser;
import com.ibaixiong.entity.DdUser;
import com.ibaixiong.manage.dao.ding.DdUserDao;
import com.ibaixiong.manage.service.ding.DdUserService;

@Service
public class DdUserServiceImpl implements DdUserService {

	@Resource
	private DdUserDao ddUserDao;
	
	@Override
	public List<DdUser> queryDdUsersByDepartmentId(String departmentId) {
		return ddUserDao.queryDdUsersByDepartmentId(departmentId, InvalidEnum.FALSE.getInvalidValue());
	}
	@Override
	public List<DdUser> queryDbUserByAdminId(String adminId) {
		return ddUserDao.queryDbUserByAdminId(adminId);
	}
	@Override
	public List<DdUser> queryAll() {
		return ddUserDao.queryAll();
	}
	@Override
	public DdUser getByUserId(String userId) {
		return ddUserDao.selectByPrimaryKey(userId);
	}
}
