package com.ibaixiong.manage.service.ccm;

import java.util.List;
import java.util.Map;

import com.ibaixiong.entity.CcmQuestion;

public interface QuestionService {
	void insert(CcmQuestion question);
	List<CcmQuestion> queryList(Map<String, Object> map);
	CcmQuestion getQuestionById(Long id);
	void update(CcmQuestion question);
}
