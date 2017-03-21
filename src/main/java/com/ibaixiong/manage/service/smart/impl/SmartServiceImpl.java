/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.smart.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.Smart;
import com.ibaixiong.entity.SmartLog;
import com.ibaixiong.manage.dao.smart.SmartDao;
import com.ibaixiong.manage.dao.smart.SmartLogDao;
import com.ibaixiong.manage.dao.smart.SmartPlanDao;
import com.ibaixiong.manage.dao.smart.SmartTypeDao;
import com.ibaixiong.manage.dao.smart.SmartUserDao;
import com.ibaixiong.manage.service.smart.SmartService;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年8月6日-下午1:29:38
 */
@Service
@Transactional
public class SmartServiceImpl implements SmartService {
	@Autowired
	SmartDao smartDao;
	@Autowired
	SmartUserDao smartUserDao;
	@Autowired
	SmartTypeDao smartTypeDao;
	@Autowired
	SmartPlanDao smartPlanDao;
	@Autowired
	SmartLogDao smartLogDao;

	/**
	 * 根据bxcode更新在线状态
	 * 
	 * @param bxcode
	 * @param online
	 * @return
	 */
	@Override
	public int updateOnLineByBxcode(String bxcode, Byte online) {
		Smart smart = new Smart();
		smart.setBxcode(bxcode);
		smart.setIsOnline(online);
		return smartDao.updateByBxcode(smart);
	}

	/**
	 * 根据ID更新在线状态
	 * 
	 * @param id
	 * @param online
	 * @return
	 */
	@Override
	public int updateOnLineById(int id, Byte online) {
		Smart smart = new Smart();
		smart.setId(id);
		smart.setIsOnline(online);
		return smartDao.updateById(smart);
	}

	@Override
	public Smart getSmartByBxidAndBxidToken(String bxid, String bxidToken) {
		return smartDao.selectSmartByBxidAndBxidToken(bxid, bxidToken);
	}

	@Override
	public Smart getSmartByBxcode(String bxcode) {
		return smartDao.selectSmartByBxcode(bxcode);
	}

	@Override
	public int updateNickByBxcode(String bxcode, String nick) {
		Smart smart = new Smart();
		smart.setBxcode(bxcode);
		smart.setNick(nick);
		return smartDao.updateByBxcode(smart);
	}

	/**
	 * 取得在线终端数量
	 * 
	 * @return
	 */
	@Override
	public int getOnlineSmartNum() {
		return smartDao.selectSmartNumByOnline(Constant.SmartOnline.BOOT.getOnline());
	}

	/**
	 * 取得不在线终端数量
	 * 
	 * @return
	 */
	@Override
	public int getNotOnlineSmartNum() {
		return smartDao.selectSmartNumByOnline(Constant.SmartOnline.SHUTDOWN.getOnline());
	}

	@Override
	public List<Smart> getListSmart(Integer pageNo) {
		PageHelper.startPage(pageNo, PageConstant.pageSize, true);
		return smartDao.getListSmart();
	}

	@Override
	public List<SmartLog> getListSmartLogByBxcode(String bxcode, Integer pageNo) {
		PageHelper.startPage(pageNo, PageConstant.pageSize, true);
		return smartLogDao.selectByBxcode(bxcode);
	}

	@Override
	public List<Smart> queryOnlineSmartList(String keywords,String sortName,Integer sortType,Integer pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder sb=new StringBuilder();
		if("date".equals(sortName)){
			sb.append("id ");
		}else{
			sb.append("c_version ");
		}
		if(sortType==1){
			sb.append(" asc");
		}else{
			sb.append(" desc");
		}
		map.put("isOnline", Constant.SmartOnline.BOOT.getOnline());
		map.put("keywords", keywords);
		PageHelper.startPage(pageNo, PageConstant.pageSize, sb.toString());
		return smartDao.querySmartByMap(map);
	}
	
//	@Override
//	public List<Smart> queryNotUpgradeOnlineSmartList(Integer pageNo) {
//		return smartDao.queryNotUpgradeSmartByOnline(Constant.SmartOnline.BOOT.getOnline());
//	}

	@Override
	public Smart getSmartById(Integer id) {

		return smartDao.getSmart(id);
	}

}
