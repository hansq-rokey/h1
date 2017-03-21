package com.ibaixiong.manage.dao.bbs;

import com.ibaixiong.entity.BbsFormRole;

public interface BbsFormRoleDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(BbsFormRole record);

    BbsFormRole selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsFormRole record);

}