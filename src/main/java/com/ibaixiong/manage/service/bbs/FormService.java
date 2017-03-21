/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.bbs;

import java.util.List;

import com.ibaixiong.entity.BbsForm;

/**
 * 社区模块
 * @author zhaolei
 *
 */
public interface FormService {
	/**
	 * 根据父节点ID返回社区模块列表
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @param parentId
	 * @return
	 */
	List<BbsForm> getFormByParentId(Long parentId);
	/**
	 * 查询所有板块列表
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @return
	 */
	List<BbsForm> queryAll();
	/**
	 * 保存版块
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @param form
	 * @return
	 */
	Long saveForm(BbsForm form);
	/**
	 * 修改版块
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @param form
	 * @return
	 */
	Long updateForm(BbsForm form);
	/**
	 * 获取版块对象
	 * @author zhaolei
	 * @date 2015年9月7日
	 * @param id
	 * @return
	 */
	BbsForm get(Long id);
}
