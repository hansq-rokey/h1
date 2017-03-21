package com.ibaixiong.manage.service.crm.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.SsssGetcash;
import com.ibaixiong.manage.dao.crm.SsssGetcashDao;
import com.ibaixiong.manage.service.crm.SsssGetcashService;
@Service
public class SsssGetcashServiceImpl implements SsssGetcashService {
	@Resource
	SsssGetcashDao ssssGetcashDao;
	@Override
	public List<SsssGetcash> getList(Map<String, Object> map) {
		return ssssGetcashDao.getList(map);
	}
	@Override
	public void update(SsssGetcash cash) {
		ssssGetcashDao.updateByPrimaryKeySelective(cash);
	}
	
	@Override
	public SsssGetcash get(Long id) {
		return ssssGetcashDao.selectByPrimaryKey(id);
	}
}
