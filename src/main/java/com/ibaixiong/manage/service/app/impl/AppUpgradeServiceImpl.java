package com.ibaixiong.manage.service.app.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.AppInfo;
import com.ibaixiong.entity.AppInfoUpgradeLog;
import com.ibaixiong.manage.dao.app.AppInfoDao;
import com.ibaixiong.manage.dao.app.AppInfoUpgradeLogDao;
import com.ibaixiong.manage.service.app.AppUpgradeService;

@Service
public class AppUpgradeServiceImpl implements AppUpgradeService {

	@Resource
	AppInfoDao appInfoDao;
	@Resource
	AppInfoUpgradeLogDao appInfoUpgradeLogDao;
	private static final String PATH="/mnt/app_file/";
	@Override
	public int addAppInfo(AppInfo appInfo) {
		appInfo.setCreateTime(new Date());
		appInfo.setStatus((byte)1);
		return appInfoDao.insertSelective(appInfo);
	}

	@Override
	public List<AppInfo> queryAppInfos(int pageNo) {
		PageHelper page = new PageHelper();
		page.startPage(pageNo, PageConstant.bbspageSize, true);
		return appInfoDao.queryAppInfos();
	}

	@Override
	public int addAppInfoUpgradeLog(AppInfoUpgradeLog log,MultipartFile file) {
		AppInfo info=appInfoDao.selectByPrimaryKey(log.getAppInfoId());
		if(info==null){
			return 0;
		}
		log.setAppAbbreviation(info.getAppAbbreviation());
		log.setAppChineseName(info.getAppName());
		log.setCreateTime(new Date());
		log.setAppSize(file.getSize());
		log.setAppDownloadName(info.getAppEnglishName()+"_"+log.getAppVersionName()+"_"+log.getAppVersionCode()+".apk");
		log.setInstallUrl("http://download.ibaixiong.com/"+info.getAppEnglishName()+"/");
		File destDir=new File(PATH+info.getAppEnglishName()+File.separator+log.getAppDownloadName());
		File destDirNewest=new File(PATH+info.getAppEnglishName()+File.separator+info.getAppEnglishName()+".apk");	
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), destDir);
			FileUtils.copyInputStreamToFile(file.getInputStream(), destDirNewest);
//			FileUtils.copyDirectory(destDir, destDirNewest, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appInfoUpgradeLogDao.insertSelective(log);
	}

	@Override
	public List<AppInfoUpgradeLog> queryAppInfoUpgradeLogs(String name,int pageNo) {
		PageHelper.startPage(pageNo, 10, "id desc");
		return appInfoUpgradeLogDao.queryAppInfoUpgradeLogs(name);
	}

}
