package com.ibaixiong.manage.dao.bbs;

import com.ibaixiong.entity.BbsUserRole;

public interface BbsUserRoleDao {
    int deleteByPrimaryKey(Long id);

    int insert(BbsUserRole record);

    int insertSelective(BbsUserRole record);

    BbsUserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BbsUserRole record);

    int updateByPrimaryKey(BbsUserRole record);
    
    void deleteByUserId(Long userId);
}