/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.manage.dao.sys.SysAdminDao;
import com.ibaixiong.manage.service.sys.SysAdminService;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年7月2日-下午1:19:30
 */
@Service("sysAdminService")
public class SysAdminServiceImpl implements SysAdminService {

	@Resource
	SysAdminDao sysAdminDao;

	public SysAdmin getAdminByLoginName(String loginName) {
		return sysAdminDao.selectByLoginName(loginName);
	}

	public SysAdmin getAdminById(Long id) {
		return sysAdminDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateAdmin(SysAdmin sysAdmin) {
		return sysAdminDao.updateByPrimaryKeySelective(sysAdmin);
	}

	@Override
	public int delectAdminById(Long id) {
		return sysAdminDao.deleteByPrimaryKey(id);
	}

	@Override
	public Long saveAdmin(SysAdmin admin) {
		return sysAdminDao.insertSelective(admin);
	}
	@Override
	public List<SysAdmin> querySysAdminList(Byte status, String queryName,Integer pageNo) {
		if(pageNo != null){
			PageHelper page= new PageHelper();
			page.startPage(pageNo, PageConstant.pageSize, true);
		}
		return sysAdminDao.querySysAdminList(status, queryName);
	}
	@Override
	public List<SysAdmin> queryAdminsByOrgIds(Long orgId) {
		return sysAdminDao.queryAdminsByOrgIds(orgId);
	}
	
}
