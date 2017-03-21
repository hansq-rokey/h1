package com.ibaixiong.manage.service.crm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.MerchantCouponMoneyRecord;
import com.ibaixiong.manage.dao.crm.MerchantCouponMoneyRecordDao;
import com.ibaixiong.manage.service.crm.MerchantCouponMoneyRecordService;
@Service
public class MerchantCouponMoneyRecordServiceImpl implements MerchantCouponMoneyRecordService {
	
	@Resource
	private MerchantCouponMoneyRecordDao merchantCouponMoneyRecordDao;
	
	@Override
	public int insert(MerchantCouponMoneyRecord record) {
		// TODO Auto-generated method stub
		return merchantCouponMoneyRecordDao.insert(record);
	}

	@Override
	public List<MerchantCouponMoneyRecord> queryListByMerchantId(Long merchantId,Integer pageNo) {
		// TODO Auto-generated method stub
		PageHelper page = new PageHelper();
		page.startPage(pageNo, PageConstant.bbspageSize, true);
		return merchantCouponMoneyRecordDao.queryListByMerchantId(merchantId);
	}

}
