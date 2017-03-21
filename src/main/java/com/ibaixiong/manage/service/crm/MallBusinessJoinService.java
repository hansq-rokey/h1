/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.crm;

import java.util.Date;
import java.util.List;

import com.ibaixiong.entity.MallBusinessJoin;

/**
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年7月12日
 * @since  1.0.0
 */
public interface MallBusinessJoinService {

	/**
	 * 添加
	 * @author yaoweiguo
	 * @date 2016年8月16日
	 * @param businessJoin
	 */
	void add(MallBusinessJoin businessJoin);
	
	/**
	 * 查询招商列表
	 * @author yaoweiguo
	 * @date 2016年8月16日
	 * @param status
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<MallBusinessJoin> queryBusinessJoins(Byte status,int pageNo,int pageSize);
	
	/**
	 * 标记已读
	 * @author yaoweiguo
	 * @date 2016年8月16日
	 * @param id
	 * @return
	 */
	int updateRead(Long id);
	/**
	 * 删除
	 * @author yaoweiguo
	 * @date 2016年8月16日
	 * @param id
	 * @return
	 */
	int delete (Long id);

	List<MallBusinessJoin> queryBusinessList(Byte status, Date startTime,
			Date endTime, Integer pageNo);

	List<MallBusinessJoin> queryBusinessList(Byte status, Date startTime,
			Date endTime);
}
