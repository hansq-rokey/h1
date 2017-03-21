package com.ibaixiong.manage.service.mall;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.FreightTemplateValue;
import com.ibaixiong.entity.FreightTemplateValueArea;


public interface FreightTemplateValueAreaService {

	List<FreightTemplateValueArea> queryByTemplateValueId(Long templateValueId);
	
	int insert(FreightTemplateValueArea record);
	
	int deleteByPrimaryKey(Long id);
	
	int deleteByTemplateValueId(Long templateValueId,Long cityId);
	
	FreightTemplateValueArea selectByIds(Long templateId,Long cityId);

	int deleteByTemplateId(Long id);
}
