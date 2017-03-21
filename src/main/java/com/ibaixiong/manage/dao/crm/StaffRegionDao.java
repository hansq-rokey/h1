package com.ibaixiong.manage.dao.crm;

import com.ibaixiong.entity.StaffRegion;


public interface StaffRegionDao {
    int deleteByPrimaryKey(Long id);

    int insert(StaffRegion record);

    int insertSelective(StaffRegion record);

    StaffRegion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StaffRegion record);

    int updateByPrimaryKey(StaffRegion record);
}