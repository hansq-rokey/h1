package com.ibaixiong.manage.dao.bbs;

import java.util.List;

import com.ibaixiong.entity.BbsArticleApply;

public interface BbsArticleApplyDao {
    List<BbsArticleApply> queryApplyListByArticleId(Long articleId); 
}