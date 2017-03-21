package com.ibaixiong.manage.service.bbs.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.BbsUserRole;
import com.ibaixiong.manage.dao.bbs.BbsUserRoleDao;
import com.ibaixiong.manage.service.bbs.BbsUserRoleService;
@Service
public class BbsUserRoleSeviceImpl implements BbsUserRoleService {
	@Resource
	private BbsUserRoleDao bbsUserRoleDao;
	@Override
	public void isnert(BbsUserRole userRole) {
		bbsUserRoleDao.insertSelective(userRole);
	}
	@Override
	public void deleteByUserId(Long userId) {
		bbsUserRoleDao.deleteByUserId(userId);
	}
}
