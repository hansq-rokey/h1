/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.bbs;

import java.util.List;

import com.ibaixiong.entity.User;

/**
 * 用户管理
 * @author zhaolei
 *
 */
public interface UserService {
	/**
	 * 查询用户列表
	 * @author zhaolei
	 * @date 2015年7月12日
	 * @param status
	 * @param queryName
	 * @param roleId
	 * @return
	 */
	List<User> queryUserList(Byte status,String queryName,Long roleId,Integer pageNo);
	/**
	 * 更改冻结状态
	 * @author zhaolei
	 * @date 2015年7月12日
	 * @param status
	 * @param id
	 * @param blockMemo
	 */
	void updateStatus(User user);
	
	/**
     * 根据用户id查询角色名称
     * @param userId
     * @return
     */
    List<String> queryRoleNames(Long userId);
    /**
     * 查询该手机号注册了的用户
     * @author zhaolei
     * @date 2016年1月4日
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);
    
    User getUser(Long userId);
    
    /**
	 * 更改用户类型
	 * @param user
	 */
	void updateType(User user);
}
