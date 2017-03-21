package com.ibaixiong.manage.service.smart.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.entity.SmartVersion;
import com.ibaixiong.entity.SmartVersionHistory;
import com.ibaixiong.manage.dao.smart.SmartVersionHistoryDao;
import com.ibaixiong.manage.service.smart.SmartVersionHistoryService;

@Service
public class SmartVersionHistoryServiceImpl implements SmartVersionHistoryService{

	@Autowired
	SmartVersionHistoryDao smartVersionHistoryDao;
	
	@Override
	public List<SmartVersionHistory> listSmartVersionHistories(Long versionId, int pageNo) {
		PageHelper.startPage(pageNo, 10, true);
		return smartVersionHistoryDao.listSmartVersionHistories(versionId);
	}

	@Override
	public int saveSmartVersionHistory(SmartVersion smartVersion) {
		SmartVersionHistory history=new SmartVersionHistory();
		history.setBxid4(smartVersion.getBxid4());
		history.setCreateDateTime(smartVersion.getCreateDateTime());
		history.setcVersionLast(smartVersion.getcVersionLast());
		history.setcVersionLast2(smartVersion.getcVersionLast2());
		history.setcVersionLast4(smartVersion.getcVersionLast4());
		history.setStatus(smartVersion.getStatus());
		history.setUpdateTime(smartVersion.getUpdateTime());
		history.setUpgradeBin(smartVersion.getUpgradeBin());
		history.setVersionId(smartVersion.getId());
		
		return smartVersionHistoryDao.insertSelective(history);
	}

}
