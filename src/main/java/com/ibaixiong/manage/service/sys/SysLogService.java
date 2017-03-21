/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.sys;

import java.util.List;

import com.ibaixiong.entity.SysLog;

/**
 * @description
 * @author zhaolei	
 * @create 2015年7月10日 
 */
public interface SysLogService {
	/**
	 * 查询日志列表
	 * @author zhaolei
	 * @date 2015年7月10日
	 * @param queryName 员工名称
	 * @param startDate 起时间
	 * @param endDate 终止时间
	 * @param adminId 是否只看本人
	 * @return
	 */
	List<SysLog> querySysLogList(String queryName,String startDate,String endDate,Long adminId,Integer pageNo);
}
