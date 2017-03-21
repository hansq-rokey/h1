package com.ibaixiong.manage.service.bbs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.BbsRole;
import com.ibaixiong.manage.dao.bbs.BbsRoleDao;
import com.ibaixiong.manage.service.bbs.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Resource
	private BbsRoleDao bbsRoleDao;
	@Override
	public List<BbsRole> getAll(String systag) {
		return bbsRoleDao.getAll(systag);
	}
	@Override
	public List<BbsRole> getRoleListByUserId(Long userId) {
		return bbsRoleDao.getRoleListByUserId(userId);
	}
}
