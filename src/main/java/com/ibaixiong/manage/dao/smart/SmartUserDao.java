package com.ibaixiong.manage.dao.smart;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.SmartUser;

public interface SmartUserDao {
	int deleteByPrimaryKey(Integer id);

	int insert(SmartUser record);

	SmartUser selectByPrimaryKey(Integer id);

	SmartUser selectByBxcodeAndUserId(@Param("bxcode") String bxcode, @Param("userId") Integer userId);

	int deleteByBxcodeAndUserId(@Param("bxcode") String bxcode, @Param("userId") Integer userId);

	List<SmartUser> selectListByBxcode(String bxcode);

	List<SmartUser> selectListByUserId(Integer userId);

	int updateByPrimaryKeySelective(SmartUser record);

	int updateIsAdminByBxcode(@Param("bxcode") String bxcode, @Param("isAdmin") Byte isAdmin);

}