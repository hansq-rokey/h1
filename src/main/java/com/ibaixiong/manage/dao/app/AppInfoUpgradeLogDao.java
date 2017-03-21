package com.ibaixiong.manage.dao.app;

import java.util.List;

import com.ibaixiong.entity.AppInfoUpgradeLog;

public interface AppInfoUpgradeLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(AppInfoUpgradeLog record);

    int insertSelective(AppInfoUpgradeLog record);

    AppInfoUpgradeLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppInfoUpgradeLog record);

    int updateByPrimaryKeyWithBLOBs(AppInfoUpgradeLog record);

    int updateByPrimaryKey(AppInfoUpgradeLog record);
    
    List<AppInfoUpgradeLog> queryAppInfoUpgradeLogs(String name);
}