package com.ibaixiong.manage.service.ding.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.entity.DdWorkLog;
import com.ibaixiong.manage.dao.ding.DdWorkLogDao;
import com.ibaixiong.manage.service.ding.DdWorkLogService;
@Service
public class DdWorkLogServiceImpl implements DdWorkLogService {
	@Resource
	private DdWorkLogDao ddWorkLogDao;
	@Override
	public List<DdWorkLog> getOwnSendList(String userId, Integer pageNo) {
		PageHelper.startPage(pageNo, 10, false);
		return ddWorkLogDao.getListByQueryType(userId,1);
	}
	@Override
	public List<DdWorkLog> getOtherPushSendList(String userId, Integer pageNo) {
		PageHelper.startPage(pageNo, 10, false);
		return ddWorkLogDao.getListByQueryType(userId, 2);
	}
	@Override
	public DdWorkLog getTodayLog(Map<String, Object> map) {
		return ddWorkLogDao.getTodayLog(map);
	}
	@Override
	public void insert(DdWorkLog log) {
		ddWorkLogDao.insertSelective(log);
	}
	
	@Override
	public DdWorkLog get(Long id) {
		return ddWorkLogDao.selectByPrimaryKey(id);
	}
	@Override
	public void update(DdWorkLog log) {
		ddWorkLogDao.updateByPrimaryKeySelective(log);
	}
	@Override
	public List<DdWorkLog> getListByQueryTypeAndYM(String userId,int type,String year,String month){
		return ddWorkLogDao.getListByQueryTypeAndYM(userId, type,year,month);
	}
}
