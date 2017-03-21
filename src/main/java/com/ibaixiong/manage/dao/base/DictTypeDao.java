package com.ibaixiong.manage.dao.base;

import java.util.List;

import com.ibaixiong.manage.service.base.mode.DictType;

public interface DictTypeDao {
    int deleteByPrimaryKey(Long id);

    int insert(DictType record);

    int insertSelective(DictType record);

    DictType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DictType record);

    int updateByPrimaryKey(DictType record);
    
    List<DictType> queryDictTypeList();
    
    DictType getByDictType(String dictType);
}