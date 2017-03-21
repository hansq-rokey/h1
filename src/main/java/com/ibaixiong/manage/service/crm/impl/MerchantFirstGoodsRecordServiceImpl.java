package com.ibaixiong.manage.service.crm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.MerchantFirstGoodsMoneyRecord;
import com.ibaixiong.manage.dao.crm.MerchantFirstGoodsMoneyRecordDao;
import com.ibaixiong.manage.service.crm.MerchantFirstGoodsRecordService;

@Service
public class MerchantFirstGoodsRecordServiceImpl implements MerchantFirstGoodsRecordService {
	@Resource
	MerchantFirstGoodsMoneyRecordDao merchantFirstGoodsMoneyRecordDao;

	@Override
	public int insertSelective(MerchantFirstGoodsMoneyRecord record) {
		// TODO Auto-generated method stub
		return merchantFirstGoodsMoneyRecordDao.insertSelective(record);
	}

	@Override
	public List<MerchantFirstGoodsMoneyRecord> queryListByMerchantId(Long merchantId,Integer pageNo) {
		PageHelper page = new PageHelper();
		page.startPage(pageNo, PageConstant.bbspageSize, true);
		return merchantFirstGoodsMoneyRecordDao.queryListByMerchantId(merchantId);
	}
}
