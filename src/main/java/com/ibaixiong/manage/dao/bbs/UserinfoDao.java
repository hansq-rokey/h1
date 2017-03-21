package com.ibaixiong.manage.dao.bbs;

import com.ibaixiong.entity.Userinfo;

public interface UserinfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}