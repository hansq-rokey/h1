package com.ibaixiong.manage.service.smart;

import java.util.Date;
import java.util.List;

import com.ibaixiong.entity.Smart;
import com.ibaixiong.entity.SmartUpgradeLog;


public interface SmartUpgradeLogService {

	/**
	 * 在线的智能温控器进行升级；
	 * 之前已经升级过的将不再升级
	 */
	void autoUpgradeSmart(Date startDate, Date endDate);
	/**
	 * 统一发送配置文件
	 */
	void SendSystemConfigSmart(Date startDate,Date endDate);
	/**
	 * 单个智能设备升级
	 * @param smart
	 * @return
	 */
	int upgradeSmart(Smart smart);
	
	/**
	 * 单个发送配置文件
	 * @param smart
	 * @return
	 */
	int SendSystemConfigSmart(Smart smart);
	
	/**
	 * 升级日志列表查询
	 * @param bxid
	 * @return
	 */
	List<SmartUpgradeLog> querySmartUpgradeLogsByBxid(String bxid,Integer pageNo);
	
	/**
	 * 根据type查询日志列表
	 * @param type
	 * @return
	 */
	List<SmartUpgradeLog> queryListByType(Short type);
	
	/**
	 * 更新日志
	 * @param log
	 * @return
	 */
	int updateSmartUpgradeLog(SmartUpgradeLog log);
}
