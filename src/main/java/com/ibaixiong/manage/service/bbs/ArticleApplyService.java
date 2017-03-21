/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.bbs;

import java.util.List;

import com.ibaixiong.entity.BbsArticleApply;


/**
 * 报名帖子管理
 * @author zhaolei
 *
 */
public interface ArticleApplyService {
	 List<BbsArticleApply> queryApplyListByArticleId(Long articleId);

}
