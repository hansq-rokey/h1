package com.ibaixiong.manage.service.ding.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.constant.Constant.Status;
import com.ibaixiong.constant.InvalidEnum;
import com.ibaixiong.entity.DdPermissionOrgUser;
import com.ibaixiong.manage.dao.ding.DdPermissionOrgUserDao;
import com.ibaixiong.manage.service.ding.DdPermissionOrgUserService;

@Service
public class DdPermissionOrgUserServiceImpl implements DdPermissionOrgUserService {

	@Resource
	private DdPermissionOrgUserDao ddPermissionOrgUserDao;
	
	@Override
	public List<DdPermissionOrgUser> queryDdPermissionOrgUserByUserId(String userId) {
		
		return ddPermissionOrgUserDao.queryDdPermissionOrgUserByUserId(userId);
	}

	@Override
	public int save(String userId, String orgId) {
		DdPermissionOrgUser permiss=new DdPermissionOrgUser();
		permiss.setCreateDateTime(new Date());
		permiss.setInvalid(InvalidEnum.FALSE.getInvalidValue());
		permiss.setOrgId(orgId);
		permiss.setStatus(Status.NORMAL.getStatus());
		permiss.setUpdateTime(new Date());
		permiss.setUserId(userId);
		
		
		return ddPermissionOrgUserDao.insertSelective(permiss);
	}

	@Override
	public int delete(String userId, String orgId) {
		
		return ddPermissionOrgUserDao.deleteByOrgIdAndUserId(userId, orgId);
	}

}
