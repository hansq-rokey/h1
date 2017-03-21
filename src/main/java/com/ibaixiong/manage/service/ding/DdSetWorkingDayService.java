package com.ibaixiong.manage.service.ding;

import java.util.List;

import com.ibaixiong.entity.DdSetWorkingDay;


public interface DdSetWorkingDayService {
	void insert(DdSetWorkingDay wd);
	void deleteByYearMonth(String year,String month);
	List<DdSetWorkingDay> shouldSendCountByYearMonth(String year,String month);
}
