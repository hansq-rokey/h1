package com.ibaixiong.manage.dao.mall;

import java.util.List;

import com.ibaixiong.entity.FreightTemplateValue;

public interface FreightTemplateValueDao {
    int deleteByPrimaryKey(Long id);

    int insert(FreightTemplateValue record);

    int insertSelective(FreightTemplateValue record);

    FreightTemplateValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FreightTemplateValue record);

    int updateByPrimaryKey(FreightTemplateValue record);
    
    List<FreightTemplateValue> queryByTemplateId(Long templateId);
    
    int deleteByTemplateId(Long templateId);
}