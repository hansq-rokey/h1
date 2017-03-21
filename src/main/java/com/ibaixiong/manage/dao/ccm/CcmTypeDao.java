package com.ibaixiong.manage.dao.ccm;

import java.util.List;

import com.ibaixiong.entity.CcmType;

public interface CcmTypeDao {
    void deleteByPrimaryKey(Long id);

    Long insertSelective(CcmType record);

    CcmType selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(CcmType record);
    
    List<CcmType> getAll();
}