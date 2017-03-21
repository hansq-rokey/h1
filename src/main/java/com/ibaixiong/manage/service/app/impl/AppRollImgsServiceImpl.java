package com.ibaixiong.manage.service.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.AppRollImgs;
import com.ibaixiong.manage.dao.app.AppRollImgsDao;
import com.ibaixiong.manage.service.app.AppRollImgsService;
@Service
public class AppRollImgsServiceImpl implements AppRollImgsService {
	@Resource
	AppRollImgsDao appRollImgsDao;
	@Override
	public List<AppRollImgs> getList(int pageNo) {
		PageHelper page= new PageHelper();
		page.startPage(pageNo, PageConstant.pageSize, true);
		return appRollImgsDao.getList();
	}
	
	@Override
	public void insert(AppRollImgs roll) {
		appRollImgsDao.insertSelective(roll);
	}

	@Override
	public void update(AppRollImgs roll) {
		appRollImgsDao.updateByPrimaryKeySelective(roll);
	}
	@Override
	public void del(Long id) {
		appRollImgsDao.deleteByPrimaryKey(id);
	}

	@Override
	public AppRollImgs get(Long id) {
		return appRollImgsDao.selectByPrimaryKey(id);
	}
}
