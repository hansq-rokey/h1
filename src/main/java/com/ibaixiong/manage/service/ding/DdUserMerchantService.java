package com.ibaixiong.manage.service.ding;

import java.util.List;

import com.ibaixiong.entity.DdUserMerchant;

public interface DdUserMerchantService {

	void insert(DdUserMerchant userMerchant);
	
	int deleteByMerchantId(Long merchantId);
	
	List<DdUserMerchant> queryByMerchantId(Long merchantId);
}
