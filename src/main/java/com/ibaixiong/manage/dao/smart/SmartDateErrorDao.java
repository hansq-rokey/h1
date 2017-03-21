package com.ibaixiong.manage.dao.smart;

import java.util.List;

import com.ibaixiong.entity.SmartDateError;


public interface SmartDateErrorDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SmartDateError record);

    int insertSelective(SmartDateError record);

    SmartDateError selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmartDateError record);

    int updateByPrimaryKey(SmartDateError record);
    
    List<SmartDateError> queryDateErrors(Byte status);
}