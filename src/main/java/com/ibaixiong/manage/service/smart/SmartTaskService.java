/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.smart;

import java.util.List;

import com.ibaixiong.entity.SmartTask;

public interface SmartTaskService {
	int insertSelective(SmartTask record);

    SmartTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(SmartTask record);
    
    List<SmartTask> queryList();
}
