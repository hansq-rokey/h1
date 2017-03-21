/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.bbs;

import java.util.List;
import java.util.Map;

import com.ibaixiong.entity.BbsArticle;

/**
 * 用户管理
 * @author zhaolei
 *
 */
public interface ArticleService {
	/**
	 * 查询用户列表
	 * @author zhaolei
	 * @date 2015年7月12日
	 * @param status
	 * @param queryName
	 * @param roleId
	 * @return
	 */
	List<BbsArticle> queryArticleList(Map<String, Object> map);
	
	/**
	 * 修改排序字段
	 * @author zhaolei
	 * @date 2015年7月15日
	 * @param article
	 */
	void updateSort(BbsArticle article);
	/**
	 * 修改设置标签
	 * @author zhaolei
	 * @date 2015年7月15日
	 * @param article
	 */
	void updateTagType(BbsArticle article);
	void delete(Long id,Integer reason,String reasonStr);
	BbsArticle getArticleById(Long id);
	Long saveArticle(BbsArticle article);
	Long updateArticle(BbsArticle article);
	
	/**
	 * 修改单据状态
	 * @author zhaolei
	 * @date 2015年7月15日
	 * @param article
	 */
	void updateStatus(BbsArticle article);
}
