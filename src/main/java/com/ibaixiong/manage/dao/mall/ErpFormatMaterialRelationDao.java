package com.ibaixiong.manage.dao.mall;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.ErpFormatMaterialRelation;

public interface ErpFormatMaterialRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(ErpFormatMaterialRelation record);

    int insertSelective(ErpFormatMaterialRelation record);

    ErpFormatMaterialRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ErpFormatMaterialRelation record);

    int updateByPrimaryKey(ErpFormatMaterialRelation record);
    
    List<ErpFormatMaterialRelation> queryErpFormatMaterialRelations(@Param("formatId")Long formatId);
    
    int deleteByFormatIdAndMaterialId(@Param("formatId")Long formatId,@Param("materialId")Long materialId);
    
    
    ErpFormatMaterialRelation getErpFormatMaterialRelation(@Param("formatId")Long formatId,@Param("materialId")Long materialId);
}