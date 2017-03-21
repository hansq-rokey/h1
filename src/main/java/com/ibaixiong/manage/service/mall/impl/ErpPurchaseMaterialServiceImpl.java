package com.ibaixiong.manage.service.mall.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.ErpPurchaseMaterial;
import com.ibaixiong.manage.dao.mall.ErpPurchaseMaterialDao;
import com.ibaixiong.manage.service.mall.ErpPurchaseMaterialService;
@Service
public class ErpPurchaseMaterialServiceImpl implements
		ErpPurchaseMaterialService {
	@Resource
	private ErpPurchaseMaterialDao erpPurchaseMaterialDao;
	@Override
	public List<ErpPurchaseMaterial> getList(Integer pageNo) {
//		PageHelper.startPage(pageNo, PageConstant.pageSize, true);
		return erpPurchaseMaterialDao.getList();
	}

	@Override
	public void insert(ErpPurchaseMaterial order) {
		erpPurchaseMaterialDao.insertSelective(order);
	}

	@Override
	public void update(ErpPurchaseMaterial order) {
		erpPurchaseMaterialDao.updateByPrimaryKeySelective(order);
	}

	@Override
	public void delete(Long id) {
		erpPurchaseMaterialDao.deleteByPrimaryKey(id);
	}
	@Override
	public ErpPurchaseMaterial get(Long id) {
		return erpPurchaseMaterialDao.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.ibaixiong.manage.service.mall.ErpPurchaseMaterialService#queryPurchaseMaterialByFormatId(java.lang.Long)
	 */
	@Override
	public List<ErpPurchaseMaterial> queryPurchaseMaterialByFormatId(Long formatId) {
		// TODO Auto-generated method stub
		return erpPurchaseMaterialDao.queryPurchaseMaterialByFormatId(formatId);
	}
}
