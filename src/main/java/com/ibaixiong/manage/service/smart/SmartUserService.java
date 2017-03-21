/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.smart;

import com.ibaixiong.entity.SmartUser;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年8月6日-下午12:13:11
 */
public interface SmartUserService {

	/**
	 * 根据bxcode和userId查找
	 * 
	 * @param bxcode
	 * @param userId
	 * @return
	 */
	SmartUser getSmartByBxcodeUserId(String bxcode, Integer userId);

	/**
	 * 删除已绑定用户
	 * 
	 * @param bxid
	 * @param bxcode
	 * @param userId
	 * @param removeUserId
	 * @return
	 */
	boolean removeBindSmartUser(String bxid, String bxcode, Integer userId, Integer removeUserId);

}
