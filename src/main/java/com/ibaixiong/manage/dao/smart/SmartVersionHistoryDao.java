package com.ibaixiong.manage.dao.smart;

import java.util.List;

import com.ibaixiong.entity.SmartVersionHistory;

public interface SmartVersionHistoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(SmartVersionHistory record);

    int insertSelective(SmartVersionHistory record);

    SmartVersionHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmartVersionHistory record);

    int updateByPrimaryKey(SmartVersionHistory record);
    
    List<SmartVersionHistory> listSmartVersionHistories(Long versionId);
}