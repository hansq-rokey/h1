package com.ibaixiong.manage.dao.bbs;

import java.util.List;

import com.ibaixiong.entity.BbsForm;

public interface BbsFormDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(BbsForm record);

    BbsForm selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsForm record);
    List<BbsForm> getFormByParentId(Long parentId);
    List<BbsForm> queryAll();
}