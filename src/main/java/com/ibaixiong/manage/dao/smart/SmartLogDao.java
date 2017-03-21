package com.ibaixiong.manage.dao.smart;

import java.util.List;

import com.ibaixiong.entity.SmartLog;

public interface SmartLogDao {
	int deleteByPrimaryKey(Integer id);

	int insert(SmartLog record);

	int insertSelective(SmartLog record);

	SmartLog selectByPrimaryKey(Integer id);

	List<SmartLog> selectByBxcode(String bxcode);

	int updateByPrimaryKeySelective(SmartLog record);

	int updateByPrimaryKey(SmartLog record);
}