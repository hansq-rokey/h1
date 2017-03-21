package com.ibaixiong.manage.dao.bbs;

import com.ibaixiong.entity.BbsComment;

public interface BbsCommentDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(BbsComment record);

    BbsComment selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsComment record);

}