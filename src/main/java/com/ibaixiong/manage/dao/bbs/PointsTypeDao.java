package com.ibaixiong.manage.dao.bbs;

import java.util.List;

import com.ibaixiong.entity.PointsType;

public interface PointsTypeDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(PointsType record);

    PointsType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PointsType record);

    List<PointsType> getAll();
}