package com.ibaixiong.manage.dao.ding;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.DdPermissionOrgUser;

public interface DdPermissionOrgUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(DdPermissionOrgUser record);

    int insertSelective(DdPermissionOrgUser record);

    DdPermissionOrgUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DdPermissionOrgUser record);

    int updateByPrimaryKey(DdPermissionOrgUser record);
    
    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    List<DdPermissionOrgUser> queryDdPermissionOrgUserByUserId(String userId);
    /**
     * 删除
     * @param userId		用户ID
     * @param orgId			部门ID
     * @return
     */
    int deleteByOrgIdAndUserId(@Param("userId")String userId, @Param("orgId")String orgId);
}