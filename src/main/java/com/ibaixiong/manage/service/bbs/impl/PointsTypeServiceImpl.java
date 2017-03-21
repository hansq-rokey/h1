package com.ibaixiong.manage.service.bbs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.PointsType;
import com.ibaixiong.manage.dao.bbs.PointsTypeDao;
import com.ibaixiong.manage.service.bbs.PointsTypeService;
@Service
public class PointsTypeServiceImpl implements PointsTypeService {
	@Resource
	private PointsTypeDao pointsTypeDao;
	@Override
	public List<PointsType> getAll() {
		return pointsTypeDao.getAll();
	}
	@Override
	public void update(PointsType type) {
		pointsTypeDao.updateByPrimaryKeySelective(type);
	}

}
