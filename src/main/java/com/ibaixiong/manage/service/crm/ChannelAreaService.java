package com.ibaixiong.manage.service.crm;

import java.util.List;

import com.ibaixiong.entity.ChannelArea;

public interface ChannelAreaService {
	
	void insertSelective(ChannelArea record);
	
	int updateByPrimaryKeySelective(ChannelArea record);
	
	ChannelArea selectByCountyId(Long countyId);
	
	List<ChannelArea> selectByChannelInfoId(Long channelInfoId);
	
	int deleteByChannelInfoId(Long pid);
	
	int deleteByChannelInfoIdAndCountyId(Long pid,Long countyId);
}
