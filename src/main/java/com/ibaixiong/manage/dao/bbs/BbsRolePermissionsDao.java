package com.ibaixiong.manage.dao.bbs;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.BbsRolePermissions;

public interface BbsRolePermissionsDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(BbsRolePermissions record);

    BbsRolePermissions selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BbsRolePermissions record);
    
    List<BbsRolePermissions> getRolePermissionsByPerId(Long id);
    
    BbsRolePermissions getRolePerByPerAndRole(@Param("perId") Long perId,@Param("roleId") Long roleId);
    
	void deleteByRole(Long roleId);
}