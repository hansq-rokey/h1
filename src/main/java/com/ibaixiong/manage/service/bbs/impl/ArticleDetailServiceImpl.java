package com.ibaixiong.manage.service.bbs.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.BbsArticleDetail;
import com.ibaixiong.manage.dao.bbs.BbsArticleDetailDao;
import com.ibaixiong.manage.service.bbs.ArticleDetailService;

@Service
public class ArticleDetailServiceImpl implements ArticleDetailService{
	@Resource
	private BbsArticleDetailDao bbsArticleDetailDao;

	@Override
	public BbsArticleDetail getArticleDetailByarticleId(Long id) {
		return bbsArticleDetailDao.selectByArticleKey(id);
	}
	@Override
	public Long saveArticleDetail(BbsArticleDetail detail) {
		return bbsArticleDetailDao.insertSelective(detail);
	}
	@Override
	public Long updateArticleDetail(BbsArticleDetail detail) {
		return bbsArticleDetailDao.updateByPrimaryKeySelective(detail);
	}
	
}
