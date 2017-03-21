/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.bbs;

import com.ibaixiong.entity.BbsArticleDetail;

/**
 * 用户管理
 * @author zhaolei
 *
 */
public interface ArticleDetailService {
	
	BbsArticleDetail getArticleDetailByarticleId(Long id);
	Long saveArticleDetail(BbsArticleDetail detail);
	Long updateArticleDetail(BbsArticleDetail detail);
}
