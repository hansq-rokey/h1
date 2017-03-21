package com.ibaixiong.manage.dao.ding;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.DdAdminUser;

public interface DdAdminUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(DdAdminUser record);

    int insertSelective(DdAdminUser record);

    DdAdminUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DdAdminUser record);

    int updateByPrimaryKey(DdAdminUser record);
    
    List<DdAdminUser> getListByUserId(String userId);
    
    DdAdminUser getByUserIdAndAdminId(@Param("userId")String userId, @Param("adminId")String adminId);
    
    void deleteByAdminId(String adminId);
    
    void deleteByUserId(String userId);
}