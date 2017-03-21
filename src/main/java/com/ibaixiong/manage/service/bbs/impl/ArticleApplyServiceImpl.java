package com.ibaixiong.manage.service.bbs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.BbsArticleApply;
import com.ibaixiong.manage.dao.bbs.BbsArticleApplyDao;
import com.ibaixiong.manage.service.bbs.ArticleApplyService;

@Service
public class ArticleApplyServiceImpl implements ArticleApplyService{
	@Resource
	private BbsArticleApplyDao bbsArticleApplyDao;

	@Override
	public List<BbsArticleApply> queryApplyListByArticleId(Long articleId) {
		return bbsArticleApplyDao.queryApplyListByArticleId(articleId);
	}
	
	
}
