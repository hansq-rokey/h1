package com.ibaixiong.manage.dao.bbs;

import com.ibaixiong.entity.BbsReply;

public interface BbsReplyDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(BbsReply record);

    BbsReply selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsReply record);

}