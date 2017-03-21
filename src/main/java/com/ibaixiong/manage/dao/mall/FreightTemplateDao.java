package com.ibaixiong.manage.dao.mall;

import java.util.List;

import com.ibaixiong.entity.FreightTemplate;

public interface FreightTemplateDao {
    int deleteByPrimaryKey(Long id);

    int insert(FreightTemplate record);

    int insertSelective(FreightTemplate record);

    FreightTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FreightTemplate record);

    int updateByPrimaryKey(FreightTemplate record);
    
    List<FreightTemplate> queryAll();
    
    FreightTemplate selectByName(String name);
}