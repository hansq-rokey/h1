package com.ibaixiong.manage.dao.bbs;

import java.util.List;
import java.util.Map;

import com.ibaixiong.entity.BbsArticle;

public interface BbsArticleDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(BbsArticle record);

    BbsArticle selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsArticle record);
    
    Long updateSort(BbsArticle record);
    
    Long updateTagType(BbsArticle article);
    
    List<BbsArticle> queryArticleList1(Map<String, Object> map); 
    
    List<BbsArticle> queryArticleList2(Map<String, Object> map); 
    
    List<BbsArticle> queryArticleList3(Map<String, Object> map); 
    
    List<BbsArticle> queryArticleList4(Map<String, Object> map); 
    
    Long updateStatus(BbsArticle record);
}