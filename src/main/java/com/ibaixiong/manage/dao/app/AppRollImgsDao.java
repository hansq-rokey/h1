package com.ibaixiong.manage.dao.app;

import java.util.List;

import com.ibaixiong.entity.AppRollImgs;

public interface AppRollImgsDao {
    int deleteByPrimaryKey(Long id);

    int insert(AppRollImgs record);

    int insertSelective(AppRollImgs record);

    AppRollImgs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppRollImgs record);

    int updateByPrimaryKey(AppRollImgs record);
    
    List<AppRollImgs> getList();
}