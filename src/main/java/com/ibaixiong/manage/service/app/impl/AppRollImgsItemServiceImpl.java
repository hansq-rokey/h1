package com.ibaixiong.manage.service.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.AppRollImgsItem;
import com.ibaixiong.manage.dao.app.AppRollImgsItemDao;
import com.ibaixiong.manage.service.app.AppRollImgsItemService;
@Service
public class AppRollImgsItemServiceImpl implements AppRollImgsItemService {
	@Resource
	AppRollImgsItemDao appRollImgsItemDao;
	@Override
	public List<AppRollImgsItem> getListByRollId(Long rollId) {
		return appRollImgsItemDao.getListByRollId(rollId);
	}

	@Override
	public void update(AppRollImgsItem item) {
		appRollImgsItemDao.updateByPrimaryKey(item);
	}

	@Override
	public void insert(AppRollImgsItem item) {
		appRollImgsItemDao.insertSelective(item);
	}
	@Override
	public void del(Long id) {
		appRollImgsItemDao.deleteByPrimaryKey(id);
	}
}
