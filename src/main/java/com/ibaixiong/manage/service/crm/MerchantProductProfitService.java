package com.ibaixiong.manage.service.crm;

import java.util.List;

import com.ibaixiong.entity.MerchantProductProfit;

public interface MerchantProductProfitService {
	
	int deleteByMerchantId(Long merchantId);
	
	void insert(MerchantProductProfit profit);
	
	List<MerchantProductProfit> queryList(Long merchantId);
}
