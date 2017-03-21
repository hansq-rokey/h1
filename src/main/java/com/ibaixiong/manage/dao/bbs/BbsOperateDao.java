package com.ibaixiong.manage.dao.bbs;

import java.util.List;

import com.ibaixiong.entity.BbsOperate;

public interface BbsOperateDao {
    int deleteByPrimaryKey(Long id);

    int insertSelective(BbsOperate record);

    BbsOperate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BbsOperate record);
    
    List<BbsOperate> queryAll();
}