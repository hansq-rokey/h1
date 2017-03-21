package com.ibaixiong.manage.service.crm;

import java.util.List;

import com.ibaixiong.entity.MerchantCouponMoneyRecord;

public interface MerchantCouponMoneyRecordService {
	
	int insert(MerchantCouponMoneyRecord record);
	
	List<MerchantCouponMoneyRecord> queryListByMerchantId(Long merchantId,Integer pageNo);
}
