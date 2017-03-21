package com.ibaixiong.manage.dao.base;

import java.util.List;
import java.util.Map;

import com.ibaixiong.manage.service.base.mode.DictCode;


public interface DictCodeDao {
    int deleteByPrimaryKey(Long id);

    int insert(DictCode record);

    int insertSelective(DictCode record);

    DictCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DictCode record);

    int updateByPrimaryKey(DictCode record);
    
    List<DictCode> queryDictCodeByDictType(String dictType);
    
    List<DictCode> queryDictCodeList(Map<String, Object> map);
}