package com.ibaixiong.manage.service.crm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.MerchantRebateMoneyRecord;
import com.ibaixiong.manage.dao.crm.MerchantRebateMoneyRecordDao;
import com.ibaixiong.manage.service.crm.MerchantRebateMoneyRecordService;

@Service
public class MerchantRebateMoneyRecordServiceImpl implements MerchantRebateMoneyRecordService {
	@Resource
	MerchantRebateMoneyRecordDao rebateRecordDao;

	@Override
	public int insertSelective(MerchantRebateMoneyRecord record) {
		// TODO Auto-generated method stub
		return rebateRecordDao.insertSelective(record);
	}

	@Override
	public List<MerchantRebateMoneyRecord> queryListByMerchantId(Long merchantId,Integer pageNo) {
		PageHelper page = new PageHelper();
		page.startPage(pageNo, PageConstant.bbspageSize, true);
		return rebateRecordDao.queryListByMerchantId(merchantId);
	}

}
