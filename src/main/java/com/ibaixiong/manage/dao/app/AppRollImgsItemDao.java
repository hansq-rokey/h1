package com.ibaixiong.manage.dao.app;

import java.util.List;

import com.ibaixiong.entity.AppRollImgsItem;

public interface AppRollImgsItemDao {
    int deleteByPrimaryKey(Long id);

    int insert(AppRollImgsItem record);

    int insertSelective(AppRollImgsItem record);

    AppRollImgsItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppRollImgsItem record);

    int updateByPrimaryKey(AppRollImgsItem record);
    
    List<AppRollImgsItem> getListByRollId(Long rollId);
}