package com.ibaixiong.manage.dao.smart;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.SmartType;

public interface SmartTypeDao {

    int deleteByPrimaryKey(Long id);

    int insert(SmartType record);

    int insertSelective(SmartType record);

    SmartType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmartType record);

    int updateByPrimaryKey(SmartType record);
    
//	SmartType selectByPrimaryKey(Integer id);

	SmartType selectByBxid4(String bxid4);

	List<SmartType> listSmartTypeByStatus(@Param("status")Byte status);

}