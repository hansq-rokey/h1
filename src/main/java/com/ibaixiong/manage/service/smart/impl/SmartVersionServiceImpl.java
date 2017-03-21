package com.ibaixiong.manage.service.smart.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.Constant.SmartVersionStatus;
import com.ibaixiong.constant.Constant.Status;
import com.ibaixiong.entity.SmartVersion;
import com.ibaixiong.manage.dao.smart.SmartVersionDao;
import com.ibaixiong.manage.service.smart.SmartVersionService;

@Service
public class SmartVersionServiceImpl implements SmartVersionService {

	@Autowired
	SmartVersionDao smartVersionDao;

	@Override
	public List<SmartVersion> listSmartVersionsByStatus(Byte status, Integer pageNo) {
		
		PageHelper.startPage(pageNo, 10, true);
		return smartVersionDao.listSmartVersionsByStatus(status);
	}

	@Override
	public List<SmartVersion> listNormallSmartVersions(Integer pageNo) {
		return this.listSmartVersionsByStatus(SmartVersionStatus.NORMAL.getStatus(), pageNo);
	}

	@Override
	public int saveSmartVersion(SmartVersion smartVersion) {
		smartVersion.setCreateDateTime(new Date());
		smartVersion.setUpdateTime(new Date());
		smartVersion.setStatus(Status.NORMAL.getStatus());
		return smartVersionDao.insertSelective(smartVersion);
	}

	@Override
	public SmartVersion saveUpgradeFile(MultipartFile file, SmartVersion smartVersion) {
		String name=file.getOriginalFilename();
		String fileName=name.substring(0, name.lastIndexOf(".")-1);
		String[] versionTime=fileName.split("_");
		String cVersionLast=versionTime[0];
		String[] versionArr=cVersionLast.split("\\.");
		smartVersion.setcVersionLast(cVersionLast);
		smartVersion.setcVersionLast2(versionArr[0]);
		smartVersion.setcVersionLast4(versionArr[1]+"."+versionArr[2]);
		String path="/var/log/smart/"+name;
		File destFile=new File(path);
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		smartVersion.setUpgradeBin("http://smart-log.ibaixiong.com/"+name);
		return smartVersion;
	}

	@Override
	public int updateSmartVersion(SmartVersion smartVersion) {
		
		return smartVersionDao.updateByPrimaryKeySelective(smartVersion);
	}

	@Override
	public SmartVersion getSmartVersion(Long id) {
		
		return smartVersionDao.selectByPrimaryKey(id);
	}
	
}
