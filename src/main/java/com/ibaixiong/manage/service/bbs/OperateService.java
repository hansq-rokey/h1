/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.bbs;

import java.util.List;

import com.ibaixiong.entity.BbsOperate;


/**
 * 操作管理
 * @author zhaolei
 *
 */
public interface OperateService {
	List<BbsOperate> queryAll();
	BbsOperate getOperateById(Long id);
}
