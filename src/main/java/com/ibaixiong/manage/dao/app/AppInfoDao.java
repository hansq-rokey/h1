package com.ibaixiong.manage.dao.app;

import java.util.List;

import com.ibaixiong.entity.AppInfo;

public interface AppInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    AppInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppInfo record);

    int updateByPrimaryKey(AppInfo record);
    
    List<AppInfo> queryAppInfos();
}