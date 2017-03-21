package com.ibaixiong.manage.service.crm.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.SsssMerchantFormatPrice;
import com.ibaixiong.manage.dao.crm.SsssMerchantFormatPriceDao;
import com.ibaixiong.manage.service.crm.SsssMerchantFormatPriceService;
@Service
public class SsssMerchantFormatPriceServiceImpl implements
		SsssMerchantFormatPriceService {
	@Resource
	SsssMerchantFormatPriceDao sssMerchantFormatPriceDao;
	@Override
	public SsssMerchantFormatPrice getSsssFormatPriceByFormatIdAndssssId(
			Long formatId, Long ssssId) {
		return sssMerchantFormatPriceDao.getSsssFormatPriceByFormatIdAndssssId(formatId, ssssId);
	}
	@Override
	public SsssMerchantFormatPrice getSsssMerchantFormatPriceByFormatIdAndMerchantId(
			Long formatId, Long merchantId) {
		return sssMerchantFormatPriceDao.getSsssMerchantFormatPriceByFormatIdAndMerchantId(formatId, merchantId);
	}
	@Override
	public void insert(SsssMerchantFormatPrice bean) {
		sssMerchantFormatPriceDao.insertSelective(bean);
	}
	@Override
	public void update(SsssMerchantFormatPrice bean) {
		sssMerchantFormatPriceDao.updateByPrimaryKeySelective(bean);
	}
}
