package com.ibaixiong.manage.service.ding;

import java.util.List;

import com.ibaixiong.entity.DdSetWorkingDayLog;


public interface DdSetWorkingDayLogService {
	void insert(DdSetWorkingDayLog wd);
	void update(DdSetWorkingDayLog wd);
	DdSetWorkingDayLog shouldSendCountByYearMonth(int year,int month);
	
	/**
	 * 默认查询今年
	 * @return
	 */
	List<DdSetWorkingDayLog> querySetWorkingDayLogsByDefaultYear();
	
	/**
	 * 根据年份查询
	 * @param year
	 * @return
	 */
	List<DdSetWorkingDayLog> querysetDayLogsByYear(int year);
}
