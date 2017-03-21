package com.ibaixiong.manage.service.crm;

import java.util.List;

import com.ibaixiong.entity.MerchantSaleMoneyRecord;

public interface MerchantSaleMoneyRecordService {
	
	int insertSelective(MerchantSaleMoneyRecord record);
	
	List<MerchantSaleMoneyRecord> queryListByMerchantId(Long merchantId,Integer pageNo);
}
