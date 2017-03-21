package com.ibaixiong.manage.service.bbs.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.BbsArticle;
import com.ibaixiong.manage.dao.bbs.BbsArticleDao;
import com.ibaixiong.manage.service.bbs.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	@Resource
	private BbsArticleDao bbsArticleDao;
	
	@Override
	public List<BbsArticle> queryArticleList(Map<String, Object> map) {
		//分为四个查询
		//普通帖子
		if(map.get("pageNo") != null){
			PageHelper page= new PageHelper();
			page.startPage(Integer.parseInt(map.get("pageNo").toString()), PageConstant.pageSize, true);
		}
		if(map.get("formType").toString().equals("1")){
			return bbsArticleDao.queryArticleList1(map);
		}
		//活动帖子
		if(map.get("formType").toString().equals("2")){
			return bbsArticleDao.queryArticleList2(map);
		}
		//举报帖子
		if(map.get("formType").toString().equals("3")){
			return bbsArticleDao.queryArticleList3(map);
		}
		//学院稿件
		if(map.get("formType").toString().equals("4")){
			return bbsArticleDao.queryArticleList4(map);
		}
		return null;
	}
	@Override
	public void updateSort(BbsArticle article) {
		bbsArticleDao.updateByPrimaryKeySelective(article);
	}
	@Override
	public void updateTagType(BbsArticle article) {
		bbsArticleDao.updateTagType(article);
	}
	@Transactional
	@Override
	public void delete(Long id,Integer reason,String reasonStr) {
		bbsArticleDao.deleteByPrimaryKey(id);
		BbsArticle article = new BbsArticle();
		article.setId(id);
		if(StringUtils.isBlank(reasonStr))
			article.setDelMemo(getDelMemo(reason));
		else
			article.setDelMemo(reasonStr);
		bbsArticleDao.updateByPrimaryKeySelective(article);
	}
	private String getDelMemo(Integer reason){
		String str = "";
		if(reason.intValue() == 1){
			str = "广告删除";
		}
		if(reason.intValue() == 2){
			str = "恶意灌水";
		}
		if(reason.intValue() == 3){
			str = "重复发帖";
		}
		if(reason.intValue() == 4){
			str = "谩骂";
		}
		if(reason.intValue() == 5){
			str = "违规信息";
		}
		return str;
	}
	@Override
	public BbsArticle getArticleById(Long id) {
		return bbsArticleDao.selectByPrimaryKey(id);
	}
	@Override
	public Long saveArticle(BbsArticle article) {
		return bbsArticleDao.insertSelective(article);
	}
	@Override
	public Long updateArticle(BbsArticle article) {
		return bbsArticleDao.updateByPrimaryKeySelective(article);
	}
	@Override
	public void updateStatus(BbsArticle article) {
		bbsArticleDao.updateStatus(article);
	}
}
