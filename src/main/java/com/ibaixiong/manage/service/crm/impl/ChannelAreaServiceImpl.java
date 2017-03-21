package com.ibaixiong.manage.service.crm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.ChannelArea;
import com.ibaixiong.manage.dao.crm.ChannelAreaDao;
import com.ibaixiong.manage.service.crm.ChannelAreaService;

@Service
public class ChannelAreaServiceImpl implements ChannelAreaService {
	@Resource
	ChannelAreaDao channelAreaDao;

	@Override
	public void insertSelective(ChannelArea record) {
		channelAreaDao.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ChannelArea record) {
		return channelAreaDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public ChannelArea selectByCountyId(Long countyId) {
		ChannelArea channelArea = channelAreaDao.selectByCountyId(countyId);
		return channelArea;
	}

	@Override
	public int deleteByChannelInfoId(Long pid) {
		return channelAreaDao.deleteByChannelInfoId(pid);
	}

	@Override
	public List<ChannelArea> selectByChannelInfoId(Long channelInfoId) {
		return channelAreaDao.selectByChannelInfoId(channelInfoId);
	}

	@Override
	public int deleteByChannelInfoIdAndCountyId(Long pid, Long countyId) {
		return channelAreaDao.deleteByChannelInfoIdAndCountyId(pid, countyId);
	}
}
