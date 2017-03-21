package com.ibaixiong.manage.service.ding;

import java.util.List;

import com.ibaixiong.entity.DdPermissionOrgUser;

public interface DdPermissionOrgUserService {

	 /**
     * 查询用户权限
     * @param userId
     * @return
     */
    List<DdPermissionOrgUser> queryDdPermissionOrgUserByUserId(String userId);
    
    /**
     * 保存
     * @param userId
     * @param orgId
     * @return
     */
    int save(String userId,String orgId);
    /**
     * 删除
     * @param userId
     * @param orgId
     * @return
     */
    int delete(String userId,String orgId);
}
