package com.ibaixiong.manage.dao.smart;

import java.util.List;

import com.ibaixiong.entity.SmartUpgradeLog;

public interface SmartUpgradeLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SmartUpgradeLog record);

    int insertSelective(SmartUpgradeLog record);

    SmartUpgradeLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SmartUpgradeLog record);

    int updateByPrimaryKey(SmartUpgradeLog record);
    
    List<SmartUpgradeLog> querySmartUpgradeLogsByBxid(String bxid);
    
    List<SmartUpgradeLog> queryListByType(Short type);
    
}