package com.ibaixiong.manage.service.crm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.MerchantProduct;
import com.ibaixiong.manage.dao.crm.MerchantProductDao;
import com.ibaixiong.manage.service.crm.MerchantProductService;


@Service
public class MerchantProductServiceImpl implements MerchantProductService {
	@Resource
	private MerchantProductDao merchantProductDao;
	
	@Override
	public int deleteByMerchantId(Long merchantId) {
		return merchantProductDao.deleteByMerchantId(merchantId);
	}
	@Override
	public void insert(MerchantProduct merchantProduct) {
		merchantProductDao.insert(merchantProduct);
	}
	@Override
	public List<MerchantProduct> queryList(Long merchantId) {
		return merchantProductDao.queryList(merchantId);
	}
	
}
