/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.smart;

import java.util.List;

import com.ibaixiong.entity.Smart;
import com.ibaixiong.entity.SmartLog;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年8月6日-下午12:13:11
 */
public interface SmartService {

	/**
	 * 根据ID更新在线状态
	 * 
	 * @param id
	 * @param online
	 * @return
	 */
	int updateOnLineById(int id, Byte online);

	/**
	 * 根据bxcode更新在线状态
	 * 
	 * @param id
	 * @param online
	 * @return
	 */
	int updateOnLineByBxcode(String bxcode, Byte online);

	/**
	 * 智能终端重命名
	 * 
	 * @param bxcode
	 * @param nick
	 * @return
	 */
	int updateNickByBxcode(String bxcode, String nick);

	/**
	 * 根据bxid和bxidToken取得Smart
	 * 
	 * @param bxid
	 * @param bxidToken
	 * @return
	 */
	Smart getSmartByBxidAndBxidToken(String bxid, String bxidToken);

	/**
	 * 根据bxcode取得终端
	 * 
	 * @param bxid
	 * @return
	 */
	Smart getSmartByBxcode(String bxcode);
	
	Smart getSmartById(Integer id);

	int getOnlineSmartNum();

	int getNotOnlineSmartNum();

	List<Smart> getListSmart(Integer pageNo);

	List<SmartLog> getListSmartLogByBxcode(String bxcode, Integer pageNo);
	
	/**
	 * 查询没有升级过的并在线的设备列表
	 * @param pageNo
	 * @return
	 */
//	List<Smart> queryNotUpgradeOnlineSmartList(Integer pageNo);
	
	/**
	 * 查询在线并正常的智能温控器
	 * @param pageNo
	 * @return
	 */
	List<Smart> queryOnlineSmartList(String keywords,String sortName,Integer sortType,Integer pageNo);

}
