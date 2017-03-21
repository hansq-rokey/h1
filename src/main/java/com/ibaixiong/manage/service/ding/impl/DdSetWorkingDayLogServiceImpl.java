package com.ibaixiong.manage.service.ding.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.InvalidEnum;
import com.ibaixiong.entity.DdSetWorkingDay;
import com.ibaixiong.entity.DdSetWorkingDayLog;
import com.ibaixiong.manage.dao.ding.DdSetWorkingDayDao;
import com.ibaixiong.manage.dao.ding.DdSetWorkingDayLogDao;
import com.ibaixiong.manage.service.ding.DdSetWorkingDayLogService;

@Service
public class DdSetWorkingDayLogServiceImpl implements DdSetWorkingDayLogService {
	@Resource
	DdSetWorkingDayLogDao ddSetWorkingDayLogDao;
	@Resource
	DdSetWorkingDayDao ddSetWorkingDayDao;

	@Override
	@Transactional
	public void insert(DdSetWorkingDayLog wd) {
		wd.setCreateDateTime(new Date());
		wd.setInvalid(InvalidEnum.FALSE.getInvalidValue());
		wd.setUpdateTime(new Date());
		wd.setStatus(Constant.Status.NORMAL.getStatus());
		List<String> dayList = this.countShouldDays(wd.getDays());
		if (dayList == null) {
			throw new RuntimeException();
		}
		wd.setDayCount(dayList.size());
		ddSetWorkingDayLogDao.insertSelective(wd);
		// 保存上班时间
		for (String day : dayList) {
			DdSetWorkingDay ddSetWorkingDay = new DdSetWorkingDay();
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, wd.getYyyy());
			calendar.set(Calendar.MONTH, wd.getMm()-1);
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			ddSetWorkingDay.setWorkDay(calendar.getTime());
			this.insert(ddSetWorkingDay);
		}
	}

	/**
	 * 保存具体上班日期
	 * 
	 * @param wd
	 */
	private void insert(DdSetWorkingDay wd) {
		wd.setCreateDateTime(new Date());
		wd.setUpdateTime(new Date());
		wd.setInvalid(InvalidEnum.FALSE.getInvalidValue());
		wd.setStatus(Constant.Status.NORMAL.getStatus());
		ddSetWorkingDayDao.insertSelective(wd);
	}

	@Override
	public void update(DdSetWorkingDayLog wd) {
		wd.setUpdateTime(new Date());
		List<String> dayList = this.countShouldDays(wd.getDays());
		if (dayList == null) {
			throw new RuntimeException();
		}
		wd.setDayCount(dayList.size());
		ddSetWorkingDayLogDao.updateByPrimaryKeySelective(wd);

		List<DdSetWorkingDay> setWorkingDayList = ddSetWorkingDayDao.shouldSendCountByYearMonth(wd.getYyyy().toString(), wd.getMm().toString());
		this.handleDays(setWorkingDayList, countShouldDays(wd.getDays()), wd.getYyyy(), wd.getMm());
		ddSetWorkingDayLogDao.updateByPrimaryKeySelective(wd);
	}

	@Override
	public DdSetWorkingDayLog shouldSendCountByYearMonth(int year, int month) {
		return ddSetWorkingDayLogDao.shouldSendCountByYearMonth(year, month);
	}

	// 日期转换
	private List<String> countShouldDays(String days) {
		if (StringUtils.isBlank(days))
			return null;
		String[] dayArr = days.split(",");
		List<String> list=new ArrayList<String>();
		for(String d:dayArr){
			list.add(d);
		}
		return list;
	}

	@Override
	public List<DdSetWorkingDayLog> querySetWorkingDayLogsByDefaultYear() {
		Calendar calendar = Calendar.getInstance();
		return this.querysetDayLogsByYear(calendar.get(Calendar.YEAR));
	}

	@Override
	public List<DdSetWorkingDayLog> querysetDayLogsByYear(int year) {

		return ddSetWorkingDayLogDao.querysetDayLogsByYear(year);
	}

	/**
	 * 更新日期选择
	 * @param oldDayList
	 * @param newDayList
	 * @param year
	 * @param month
	 */
	private void handleDays(List<DdSetWorkingDay> oldDayList, List<String> newDayList, int year, int month) {
		List<String> oldDays = new ArrayList<String>();
		List<String> oldDaysCopy = new ArrayList<String>();
		Map<String, DdSetWorkingDay> map=new HashMap<String, DdSetWorkingDay>();
		for (DdSetWorkingDay wd : oldDayList) {
			Date date = wd.getWorkDay();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			oldDays.add(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
			oldDaysCopy.add(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
			map.put(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)), wd);
		}
		oldDays.removeAll(newDayList);
		newDayList.removeAll(oldDaysCopy);
		// 保存上班时间
		for (String day : newDayList) {
			DdSetWorkingDay ddSetWorkingDay = new DdSetWorkingDay();
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month-1);
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			ddSetWorkingDay.setWorkDay(calendar.getTime());
			this.insert(ddSetWorkingDay);
		}
		//删除多余的
		for(String day:oldDays){
			ddSetWorkingDayDao.deleteByPrimaryKey(map.get(day).getId());
		}
	}
}
