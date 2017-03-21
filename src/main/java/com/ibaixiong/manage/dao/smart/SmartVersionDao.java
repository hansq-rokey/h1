package com.ibaixiong.manage.dao.smart;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.SmartVersion;

public interface SmartVersionDao {
    int deleteByPrimaryKey(Long id);

    int insert(SmartVersion record);

    int insertSelective(SmartVersion record);

    SmartVersion selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmartVersion record);

    int updateByPrimaryKey(SmartVersion record);
    
    List<SmartVersion> listSmartVersionsByStatus(@Param("status")Byte status);
}