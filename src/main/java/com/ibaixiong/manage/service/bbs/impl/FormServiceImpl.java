package com.ibaixiong.manage.service.bbs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.BbsForm;
import com.ibaixiong.manage.dao.bbs.BbsFormDao;
import com.ibaixiong.manage.service.bbs.FormService;

@Service
public class FormServiceImpl implements FormService{
	@Resource
	private BbsFormDao bbsFormDao;

	@Override
	public List<BbsForm> getFormByParentId(Long parentId) {
		return bbsFormDao.getFormByParentId(parentId);
	}

	@Override
	public List<BbsForm> queryAll() {
		return bbsFormDao.queryAll();
	}
	@Override
	public Long saveForm(BbsForm form) {
		return bbsFormDao.insertSelective(form);
	}
	@Override
	public Long updateForm(BbsForm form) {
		return bbsFormDao.updateByPrimaryKeySelective(form);
	}
	@Override
	public BbsForm get(Long id) {
		return bbsFormDao.selectByPrimaryKey(id);
	}
}
