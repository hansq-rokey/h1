package com.ibaixiong.manage.service.crm;

import java.util.List;

import com.ibaixiong.entity.MerchantProduct;

public interface MerchantProductService {
	
	int deleteByMerchantId(Long merchantId);
	
	void insert(MerchantProduct merchantProduct);
	
	List<MerchantProduct> queryList(Long merchantId);
}
