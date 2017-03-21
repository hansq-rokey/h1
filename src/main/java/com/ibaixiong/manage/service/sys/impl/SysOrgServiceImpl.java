package com.ibaixiong.manage.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.SysOrg;
import com.ibaixiong.manage.dao.sys.SysOrgDao;
import com.ibaixiong.manage.service.sys.SysOrgService;
@Service
public class SysOrgServiceImpl implements SysOrgService {
	@Resource
	private SysOrgDao sysOrgDao;
	@Override
	public List<SysOrg> getAllOrgList() {
		return sysOrgDao.getAllOrgList();
	}

	@Override
	public SysOrg getOrgById(Long id) {
		return sysOrgDao.selectByPrimaryKey(id);
	}

	@Override
	public Long saveOrg(SysOrg org) {
		return sysOrgDao.insertSelective(org);
	}

	@Override
	public void delete(Long id) {
		sysOrgDao.deleteByPrimaryKey(id);
	}

	@Override
	public Long updateOrg(SysOrg org) {
		return sysOrgDao.updateByPrimaryKeySelective(org);
	}
	@Override
	public List<SysOrg> getOrgListPages(Integer pageNo) {
		PageHelper page= new PageHelper();
		page.startPage(pageNo, PageConstant.pageSize, true);
		return sysOrgDao.getAllOrgList();
	}
}
