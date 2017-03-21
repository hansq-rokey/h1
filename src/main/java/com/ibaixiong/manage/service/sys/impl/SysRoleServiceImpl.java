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
import com.ibaixiong.entity.SysRole;
import com.ibaixiong.manage.dao.sys.SysRoleDao;
import com.ibaixiong.manage.service.sys.SysRoleService;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年7月2日-下午1:19:30
 */
@Service("sysRoleService")
public class SysRoleServiceImpl implements SysRoleService {

	@Resource
	SysRoleDao sysRoleDao;

	@Override
	public List<SysRole> getAllRoleList() {
		return sysRoleDao.getAllRoleList();
	}

	@Override
	public SysRole getRoseById(Long id) {
		return sysRoleDao.selectByPrimaryKey(id);
	}
	@Override
	public List<SysRole> querySysRoleList(String queryName,Integer pageNo) {
		if(pageNo != null){
			PageHelper page= new PageHelper();
			page.startPage(pageNo, PageConstant.pageSize, true);
		}
		return sysRoleDao.querySysRoleList(queryName);
	}
	@Override
	public void deleteSysRole(Long id) {
		sysRoleDao.deleteByPrimaryKey(id);
		
	}

	@Override
	public void insert(SysRole role) {
		sysRoleDao.insertSelective(role);
	}

	@Override
	public void update(SysRole role) {
		sysRoleDao.updateByPrimaryKeySelective(role);
	}
}
