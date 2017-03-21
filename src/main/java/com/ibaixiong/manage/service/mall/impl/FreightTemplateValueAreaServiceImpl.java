package com.ibaixiong.manage.service.mall.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.FreightTemplateValueArea;
import com.ibaixiong.manage.dao.mall.FreightTemplateValueAreaDao;
import com.ibaixiong.manage.service.mall.FreightTemplateValueAreaService;

@Service
public class FreightTemplateValueAreaServiceImpl implements FreightTemplateValueAreaService {

	@Resource
	private FreightTemplateValueAreaDao freightTemplateValueAreaDao;

	@Override
	public List<FreightTemplateValueArea> queryByTemplateValueId(
			Long templateValueId) {
		// TODO Auto-generated method stub
		return freightTemplateValueAreaDao.queryByTemplateValueId(templateValueId);
	}

	@Override
	public int insert(FreightTemplateValueArea record) {
		// TODO Auto-generated method stub
		return freightTemplateValueAreaDao.insert(record);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return freightTemplateValueAreaDao.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByTemplateValueId(Long templateValueId, Long cityId) {
		// TODO Auto-generated method stub
		return freightTemplateValueAreaDao.deleteByTemplateValueId(templateValueId, cityId);
	}

	@Override
	public FreightTemplateValueArea selectByIds(Long templateId, Long provinceId) {
		// TODO Auto-generated method stub
		return freightTemplateValueAreaDao.selectByIds(templateId, provinceId);
	}

	@Override
	public int deleteByTemplateId(Long id) {
		// TODO Auto-generated method stub
		return freightTemplateValueAreaDao.deleteByTemplateId(id);
	}
}
