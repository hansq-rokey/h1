package com.ibaixiong.manage.service.mall.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.MallPay;
import com.ibaixiong.manage.dao.mall.MallPayDao;
import com.ibaixiong.manage.service.mall.MallPayService;

@Service
public class MallPayServiceImpl implements MallPayService{

	@Resource
	private MallPayDao mallPayDao;
	
	@Override
	public MallPay getMallPay(String orderNumber) {
		// TODO Auto-generated method stub
		return mallPayDao.selectByOrderNumber(orderNumber);
	}

}
