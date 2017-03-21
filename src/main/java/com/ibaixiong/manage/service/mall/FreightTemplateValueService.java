package com.ibaixiong.manage.service.mall;

import java.util.List;

import com.ibaixiong.entity.FreightTemplateValue;


public interface FreightTemplateValueService {

	List<FreightTemplateValue> queryByTemplateId(Long templateId);
	
	int insert(FreightTemplateValue record);
	
	int deleteByPrimaryKey(Long id);
	
	int updateByPrimaryKeySelective(FreightTemplateValue record);
	
	int deleteByTemplateId(Long templateId);
}
