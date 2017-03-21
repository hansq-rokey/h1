package com.ibaixiong.manage.service.mall.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.FreightTemplate;
import com.ibaixiong.entity.FreightTemplateValue;
import com.ibaixiong.manage.dao.mall.FreightTemplateValueDao;
import com.ibaixiong.manage.service.mall.FreightTemplateValueService;

@Service
public class FreightTemplateValueServiceImpl implements FreightTemplateValueService {

	@Resource
	private FreightTemplateValueDao freightTemplateValueDao;
	
	@Override
	public List<FreightTemplateValue> queryByTemplateId(Long templateId) {
		// TODO Auto-generated method stub
		return freightTemplateValueDao.queryByTemplateId(templateId);
	}

	@Override 
	public int insert(FreightTemplateValue record) {
		// TODO Auto-generated method stub
		return freightTemplateValueDao.insert(record);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return freightTemplateValueDao.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(FreightTemplateValue record) {
		// TODO Auto-generated method stub
		return freightTemplateValueDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByTemplateId(Long templateId) {
		// TODO Auto-generated method stub
		return freightTemplateValueDao.deleteByTemplateId(templateId);
	}

}
