package com.ibaixiong.manage.service.ding.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.InvalidEnum;
import com.ibaixiong.entity.DdSetWorkingDay;
import com.ibaixiong.manage.dao.ding.DdSetWorkingDayDao;
import com.ibaixiong.manage.service.ding.DdSetWorkingDayService;
@Service
public class DdSetWorkingDayServiceImpl implements DdSetWorkingDayService{
	@Resource
	DdSetWorkingDayDao ddSetWorkingDayDao;

	@Override
	public void insert(DdSetWorkingDay wd) {
		wd.setCreateDateTime(new Date());
		wd.setUpdateTime(new Date());
		wd.setInvalid(InvalidEnum.FALSE.getInvalidValue());
		wd.setStatus(Constant.Status.NORMAL.getStatus());
		ddSetWorkingDayDao.insertSelective(wd);
	}

	@Override
	public void deleteByYearMonth(String year, String month) {
		ddSetWorkingDayDao.deleteByYearMonth(year, month);
	}

	@Override
	public List<DdSetWorkingDay> shouldSendCountByYearMonth(String year, String month) {
		return ddSetWorkingDayDao.shouldSendCountByYearMonth(year, month);
	}
}
