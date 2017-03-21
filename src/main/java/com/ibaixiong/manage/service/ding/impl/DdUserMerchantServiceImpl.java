package com.ibaixiong.manage.service.ding.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.DdUserMerchant;
import com.ibaixiong.manage.dao.ding.DdUserMerchantDao;
import com.ibaixiong.manage.service.ding.DdUserMerchantService;

@Service
public class DdUserMerchantServiceImpl implements DdUserMerchantService {

	@Resource
	private DdUserMerchantDao userMerchantDao;
	@Override
	public void insert(DdUserMerchant userMerchant) {
		userMerchantDao.insert(userMerchant);
	}

	@Override
	public int deleteByMerchantId(Long merchantId) {
		return userMerchantDao.deleteByMerchantId(merchantId);
	}

	@Override
	public List<DdUserMerchant> queryByMerchantId(Long merchantId) {
		return userMerchantDao.queryByMerchantId(merchantId);
	}

}
