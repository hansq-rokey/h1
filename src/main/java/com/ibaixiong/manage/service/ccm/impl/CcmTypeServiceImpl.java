package com.ibaixiong.manage.service.ccm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.CcmType;
import com.ibaixiong.manage.dao.ccm.CcmTypeDao;
import com.ibaixiong.manage.service.ccm.CcmTypeService;
@Service
public class CcmTypeServiceImpl implements CcmTypeService {
	@Resource
	private CcmTypeDao ccmTypeDao;
	@Override
	public List<CcmType> getAll() {
		return ccmTypeDao.getAll();
	}
	@Override
	public void insert(CcmType type) {
		ccmTypeDao.insertSelective(type);
	}
}
