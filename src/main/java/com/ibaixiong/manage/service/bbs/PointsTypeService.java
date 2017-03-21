package com.ibaixiong.manage.service.bbs;

import java.util.List;

import com.ibaixiong.entity.PointsType;

public interface PointsTypeService {
	List<PointsType> getAll();
	void update(PointsType type);
}
