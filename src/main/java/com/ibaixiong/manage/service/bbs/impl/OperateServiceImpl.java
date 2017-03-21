package com.ibaixiong.manage.service.bbs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.BbsOperate;
import com.ibaixiong.manage.dao.bbs.BbsOperateDao;
import com.ibaixiong.manage.service.bbs.OperateService;

@Service
public class OperateServiceImpl implements OperateService{
	@Resource
	private BbsOperateDao bbsOperateDao;

	@Override
	public List<BbsOperate> queryAll() {
		return bbsOperateDao.queryAll();
	}

	@Override
	public BbsOperate getOperateById(Long id) {
		return bbsOperateDao.selectByPrimaryKey(id);
	}

	
}
