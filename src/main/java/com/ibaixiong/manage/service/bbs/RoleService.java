/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.bbs;

import java.util.List;

import com.ibaixiong.entity.BbsRole;

/**
 * 角色模块
 * @author zhaolei
 *
 */
public interface RoleService {
	/**
	 * 获取角色列表
	 * @author zhaolei
	 * @date 2015年7月23日
	 * @return
	 */
	List<BbsRole> getAll(String systag);
	
	List<BbsRole> getRoleListByUserId(Long userId);
}
