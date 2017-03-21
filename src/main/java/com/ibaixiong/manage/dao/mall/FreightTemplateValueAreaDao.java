package com.ibaixiong.manage.dao.mall;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.FreightTemplateValueArea;

public interface FreightTemplateValueAreaDao {
    int deleteByPrimaryKey(Long id);

    int insert(FreightTemplateValueArea record);

    int insertSelective(FreightTemplateValueArea record);

    FreightTemplateValueArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FreightTemplateValueArea record);

    int updateByPrimaryKey(FreightTemplateValueArea record);
    
    List<FreightTemplateValueArea> queryByTemplateValueId(Long templateValueId);
    
    int deleteByTemplateValueId(@Param("templateValueId")Long templateValueId,@Param("cityId")Long cityId);
    
    FreightTemplateValueArea selectByIds(@Param("templateId")Long templateId,@Param("provinceId")Long provinceId);
    
    int deleteByTemplateId(Long templateId);
    
}