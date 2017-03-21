/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.bbs;

import java.util.List;

import com.ibaixiong.entity.BbsRolePermissions;


/**
 * 角色权限中间表
 * @author zhaolei
 *
 */
public interface RolePermissionsService {
	List<BbsRolePermissions> getRolePermissionsByPerId(Long perId);
	/**
	 * 根据权限ID和角色ID查询中间表信息
	 * @author zhaolei
	 * @date 2015年7月24日
	 * @param perId
	 * @return
	 */
	BbsRolePermissions getRolePerByPerAndRole(Long perId,Long roleId);
	void save(BbsRolePermissions rp);
	void deleteByRole(Long roleId);
}
