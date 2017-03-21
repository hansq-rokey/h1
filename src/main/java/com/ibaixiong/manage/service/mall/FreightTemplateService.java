package com.ibaixiong.manage.service.mall;

import java.util.List;

import com.ibaixiong.entity.FreightTemplate;


public interface FreightTemplateService {

	int insert(FreightTemplate record);
	
	FreightTemplate selectByPrimaryKey(Long id);
	
	List<FreightTemplate> queryAll();
	
	List<FreightTemplate> queryAll(Integer pageNo);
	
	FreightTemplate selectByName(String name);
	
	int updateByPrimaryKeySelective(FreightTemplate record);
	
	int deleteByPrimaryKey(Long id);
}
