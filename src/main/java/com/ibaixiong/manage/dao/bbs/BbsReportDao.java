package com.ibaixiong.manage.dao.bbs;

import com.ibaixiong.entity.BbsReport;

public interface BbsReportDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(BbsReport record);

    BbsReport selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsReport record);

}