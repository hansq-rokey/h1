package com.ibaixiong.manage.service.app;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ibaixiong.entity.AppInfo;
import com.ibaixiong.entity.AppInfoUpgradeLog;

public interface AppUpgradeService {

	/**
	 * 添加app名称信息
	 * @param appInfo
	 * @return
	 */
	int addAppInfo(AppInfo appInfo);
	
	List<AppInfo> queryAppInfos(int pageNo);
	
	/**
	 * 添加app升级日志
	 * @param log
	 * @return
	 */
	int addAppInfoUpgradeLog(AppInfoUpgradeLog log,MultipartFile file);
	
	/**
	 * 查询app日志
	 * @param name  app简写，名称第一个字母
	 * @return
	 */
	List<AppInfoUpgradeLog> queryAppInfoUpgradeLogs(String name,int pageNo);
}
