package com.ibaixiong.manage.dao.ccm;

import java.util.List;
import java.util.Map;

import com.ibaixiong.entity.CcmQuestion;

public interface CcmQuestionDao {
	void deleteByPrimaryKey(Long id);

	Long insertSelective(CcmQuestion record);

    CcmQuestion selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(CcmQuestion record);
    
    List<CcmQuestion> queryList(Map<String, Object> map);
}