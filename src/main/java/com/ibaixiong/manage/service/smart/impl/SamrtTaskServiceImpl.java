package com.ibaixiong.manage.service.smart.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.SmartTask;
import com.ibaixiong.manage.dao.smart.SmartTaskDao;
import com.ibaixiong.manage.service.smart.SmartTaskService;
@Service
public class SamrtTaskServiceImpl implements SmartTaskService {

	@Resource
	private SmartTaskDao smartTaskDao;
	@Override
	public int insertSelective(SmartTask record) {
		// TODO Auto-generated method stub
		return smartTaskDao.insertSelective(record);
	}

	@Override
	public SmartTask selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return smartTaskDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(SmartTask record) {
		// TODO Auto-generated method stub
		return smartTaskDao.updateByPrimaryKey(record);
	}

	@Override
	public List<SmartTask> queryList() {
		// TODO Auto-generated method stub
		return smartTaskDao.queryList();
	}

}
