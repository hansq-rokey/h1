package com.ibaixiong.manage.service.ccm.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.CcmQuestion;
import com.ibaixiong.manage.dao.ccm.CcmQuestionDao;
import com.ibaixiong.manage.service.ccm.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService {
	@Resource
	private CcmQuestionDao ccmQuestionDao;
	@Override
	public void insert(CcmQuestion question) {
		ccmQuestionDao.insertSelective(question);
	}
	@Override
	public List<CcmQuestion> queryList(Map<String, Object> map) {
		PageHelper page= new PageHelper();
		page.startPage(Integer.parseInt(map.get("pageNo").toString()), PageConstant.pageSize, true);
		return ccmQuestionDao.queryList(map);
	}
	@Override
	public CcmQuestion getQuestionById(Long id) {
		return ccmQuestionDao.selectByPrimaryKey(id);
	}
	@Override
	public void update(CcmQuestion question) {
		ccmQuestionDao.updateByPrimaryKeySelective(question);
	}
}
