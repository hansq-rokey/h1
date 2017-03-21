package com.ibaixiong.manage.dao.bbs;

import com.ibaixiong.entity.BbsArticleDetail;

public interface BbsArticleDetailDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(BbsArticleDetail record);

    BbsArticleDetail selectByPrimaryKey(Long id);
    
    BbsArticleDetail selectByArticleKey(Long id);

    Long updateByPrimaryKeySelective(BbsArticleDetail record);

    Long updateByPrimaryKeyWithBLOBs(BbsArticleDetail record);
}