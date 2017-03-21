package com.ibaixiong.manage.dao.ding;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.DdOrgUser;

public interface DdOrgUserDao {
    int deleteByPrimaryKey(Long id);

    int insert(DdOrgUser record);

    int insertSelective(DdOrgUser record);

    DdOrgUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DdOrgUser record);

    int updateByPrimaryKey(DdOrgUser record);
    /**
     * 获取对象
     * @param departmentId		部门ID
     * @param userId			用户ID
     * @return
     */
    DdOrgUser getByDepartmentIdAndUserId(@Param("departmentId")String departmentId,@Param("userId")String userId);
    /**
     * 删除
     * @param departmentId	部门ID
     * @return
     */
    int deleteByDepartmentId(@Param("departmentId")String departmentId);
    
    /**
     * 删除
     * @param userId		用户ID
     * @return
     */
    int deleteByUserId(@Param("userId")String userId);
    
    /**
     * 获取列表对象
     * @param userId			用户ID
     * @return
     */
    List<DdOrgUser> getByUserId(@Param("userId")String userId);
    
    /**
     * 根据部门Id获取用户列表
     * @param departmentId
     * @return
     */
    List<DdOrgUser> queryOrgUsersByDepartmentId(@Param("departmentId")String departmentId);
}