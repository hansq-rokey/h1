package com.ibaixiong.manage.dao.smart;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.SmartPlan;

public interface SmartPlanDao {
	int deleteByPrimaryKey(Integer id);

	int insert(SmartPlan record);

	SmartPlan selectByPrimaryKey(Integer id);

	List<SmartPlan> selectListByBxcode(@Param("bxcode")String bxcode, @Param("status")Byte status);

	int updateByPrimaryKeySelective(SmartPlan record);

}