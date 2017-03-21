package com.ibaixiong.manage.service.bbs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.BbsRolePermissions;
import com.ibaixiong.manage.dao.bbs.BbsRolePermissionsDao;
import com.ibaixiong.manage.service.bbs.RolePermissionsService;
@Service
public class RolePermissionsServiceImpl implements RolePermissionsService {
	@Resource
	private BbsRolePermissionsDao bbsRolePermissionsDao;
	@Override
	public List<BbsRolePermissions> getRolePermissionsByPerId(Long perId) {
		return bbsRolePermissionsDao.getRolePermissionsByPerId(perId);
	}
	@Override
	public BbsRolePermissions getRolePerByPerAndRole(Long perId, Long roleId) {
		return bbsRolePermissionsDao.getRolePerByPerAndRole(perId, roleId);
	}
	@Override
	public void deleteByRole(Long roleId) {
		bbsRolePermissionsDao.deleteByRole(roleId);
	}
	@Override
	public void save(BbsRolePermissions rp) {
		bbsRolePermissionsDao.insertSelective(rp);
	}
}
