package com.ibaixiong.manage.service.mall.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.FreightTemplate;
import com.ibaixiong.manage.dao.mall.FreightTemplateDao;
import com.ibaixiong.manage.service.mall.FreightTemplateService;

@Service
public class FreightTemplateServiceImpl implements FreightTemplateService {

	@Resource
	private FreightTemplateDao freightTemplateDao;
	
	@Override
	public int insert(FreightTemplate record) {
		// TODO Auto-generated method stub
		return freightTemplateDao.insert(record);
	}

	@Override
	public List<FreightTemplate> queryAll(Integer pageNo) {
		// TODO Auto-generated method stub
		PageHelper pageHelper = new PageHelper();//
		pageHelper.startPage(pageNo, PageConstant.pageSize, true);
		return freightTemplateDao.queryAll();
	}
	
	@Override
	public List<FreightTemplate> queryAll() {
		// TODO Auto-generated method stub
		return freightTemplateDao.queryAll();
	}

	@Override
	public FreightTemplate selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return freightTemplateDao.selectByPrimaryKey(id);
	}

	@Override
	public FreightTemplate selectByName(String name) {
		// TODO Auto-generated method stub
		return freightTemplateDao.selectByName(name);
	}

	@Override
	public int updateByPrimaryKeySelective(FreightTemplate record) {
		// TODO Auto-generated method stub
		return freightTemplateDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return freightTemplateDao.deleteByPrimaryKey(id);
	}

}
