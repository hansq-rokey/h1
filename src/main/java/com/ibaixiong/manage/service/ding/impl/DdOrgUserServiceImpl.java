package com.ibaixiong.manage.service.ding.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.DdOrgUser;
import com.ibaixiong.manage.dao.ding.DdOrgUserDao;
import com.ibaixiong.manage.service.ding.DdOrgUserService;
@Service
public class DdOrgUserServiceImpl implements DdOrgUserService {
	@Resource
	private DdOrgUserDao ddOrgUserDao;
	@Override
	public List<DdOrgUser> getByUserId(String userId) {
		return ddOrgUserDao.getByUserId(userId);
	}

}
