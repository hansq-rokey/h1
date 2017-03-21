/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.sys;

import java.util.List;

import com.ibaixiong.entity.SysOrg;

/**
 * @description
 * @author zhaolei
 */
public interface SysOrgService {
	/**
     * 获取部门列表
     * @author zhaolei
     * @date 2015年7月9日
     * @return
     */
	List<SysOrg> getAllOrgList();

	SysOrg getOrgById(Long id);
	
	Long saveOrg(SysOrg org);
	
	Long updateOrg(SysOrg org);
	
	void delete(Long id);
	
	List<SysOrg> getOrgListPages(Integer pageNo);
}
