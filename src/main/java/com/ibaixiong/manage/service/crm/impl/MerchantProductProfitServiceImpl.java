package com.ibaixiong.manage.service.crm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.MerchantProductProfit;
import com.ibaixiong.manage.dao.crm.MerchantProductProfitDao;
import com.ibaixiong.manage.service.crm.MerchantProductProfitService;

@Service
public class MerchantProductProfitServiceImpl implements MerchantProductProfitService {
	@Resource
	private MerchantProductProfitDao merchantProductProfitDao;
	
	@Override
	public int deleteByMerchantId(Long merchantId) {
		return merchantProductProfitDao.deleteByMerchantId(merchantId);
	}
	@Override
	public void insert(MerchantProductProfit merchantProductProfit) {
		merchantProductProfitDao.insert(merchantProductProfit);
	}
	@Override
	public List<MerchantProductProfit> queryList(Long merchantId) {
		return merchantProductProfitDao.queryList(merchantId);
	}
	
}
