package com.ibaixiong.manage.dao.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.BbsRole;


public interface BbsRoleDao {
	BbsRole selectByPrimaryKey(Long id);
	List<BbsRole> getAll(@Param("sysTag") String sysTag);
	List<BbsRole> getRoleListByUserId(Long userId);
}