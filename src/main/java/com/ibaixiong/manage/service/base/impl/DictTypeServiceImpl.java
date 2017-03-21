package com.ibaixiong.manage.service.base.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.manage.dao.base.DictTypeDao;
import com.ibaixiong.manage.service.base.DictTypeService;
import com.ibaixiong.manage.service.base.mode.DictType;

@Service
public class DictTypeServiceImpl implements DictTypeService {

	@Resource
	private DictTypeDao dictTypeDao;

	@Override
	public List<DictType> queryDictTypeList() {
		return dictTypeDao.queryDictTypeList();
	}

	@Override
	public DictType getByDictType(String dictType) {
		return dictTypeDao.getByDictType(dictType);
	}

	@Override
	public void insert(DictType dictType) {
		dictTypeDao.insertSelective(dictType);
	}
}
