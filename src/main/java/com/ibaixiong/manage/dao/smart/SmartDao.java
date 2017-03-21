/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.dao.smart;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.alibaba.dubbo.config.support.Parameter;
import com.ibaixiong.entity.Smart;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年8月5日-下午4:45:34
 */
public interface SmartDao {
	int deleteByPrimaryKey(Integer id);

	int insert(Smart record);

	Smart selectByPrimaryKey(Integer id);

	Smart selectSmartByBxcode(String bxcode);

	Smart selectSmartByBxidAndBxidToken(@Param("bxid") String bxid, @Param("bxidToken") String bxidToken);
	
	Smart getSmart(Integer id);

	int updateById(Smart smart);

	int updateByBxcode(Smart smart);

	int selectSmartNumByOnline(Byte isOnline);

	List<Smart> getListSmart();
	/**
	 * 查询没有升级过并且没有在线的智能设备
	 * @param isOnline
	 * @param type      消息类型
	 * @param startDate 开始时间
	 * @param endDate   结束时间
	 * @return
	 */
	List<Smart> queryNotUpgradeSmartByOnline(@Param("isOnline")Byte isOnline,@Param("type")Short type,
			@Param("startDate") Date startDate,@Param("endDate")Date endDate);

	
	/**
	 * 查询所有在线的智能设备
	 * @param isOnline
	 * @return
	 */
	List<Smart> querySmartByOnline(Byte isOnline);
	
	/**
	 * 查询所有在线的智能设备
	 * @param map
	 * @return
	 */
	List<Smart> querySmartByMap(Map<String, Object> map);
}