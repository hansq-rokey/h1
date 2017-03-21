package com.ibaixiong.manage.dao.smart;

import java.util.List;

import com.ibaixiong.entity.SmartTask;

public interface SmartTaskDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SmartTask record);

    int insertSelective(SmartTask record);

    SmartTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmartTask record);

    int updateByPrimaryKey(SmartTask record);
    
    List<SmartTask> queryList();
}