package com.ibaixiong.manage.service.crm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.MerchantFirstGoodsMoneyRecord;
import com.ibaixiong.entity.MerchantSaleMoneyRecord;
import com.ibaixiong.manage.dao.crm.MerchantFirstGoodsMoneyRecordDao;
import com.ibaixiong.manage.dao.crm.MerchantSaleMoneyRecordDao;
import com.ibaixiong.manage.service.crm.MerchantFirstGoodsRecordService;
import com.ibaixiong.manage.service.crm.MerchantSaleMoneyRecordService;

@Service
public class MerchantSaleMoneyRecordServiceImpl implements MerchantSaleMoneyRecordService {
	@Resource
	MerchantSaleMoneyRecordDao saleRecordDao;

	@Override
	public int insertSelective(MerchantSaleMoneyRecord record) {
		// TODO Auto-generated method stub
		return saleRecordDao.insertSelective(record);
	}

	@Override
	public List<MerchantSaleMoneyRecord> queryListByMerchantId(Long merchantId,Integer pageNo) {
		PageHelper page = new PageHelper();
		page.startPage(pageNo, PageConstant.bbspageSize, true);
		return saleRecordDao.queryListByMerchantId(merchantId);
	}

}
