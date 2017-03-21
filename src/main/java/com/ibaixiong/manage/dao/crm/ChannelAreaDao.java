package com.ibaixiong.manage.dao.crm;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.ChannelArea;

public interface ChannelAreaDao {
	
	int deleteByChannelInfoId(Long pid);
	
	int deleteByChannelInfoIdAndCountyId(@Param("pid")Long pid,@Param("countyId")Long countyId);
	
    int deleteByPrimaryKey(Long id);

    int insert(ChannelArea record);

    int insertSelective(ChannelArea record);

    List<ChannelArea> selectByChannelInfoId(Long pid);
    
    ChannelArea selectByPrimaryKey(Long id);
    
    ChannelArea selectByCountyId(Long countyId);
    
    int updateByPrimaryKeySelective(ChannelArea record);

    int updateByPrimaryKey(ChannelArea record);
}