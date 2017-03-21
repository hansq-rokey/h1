package com.ibaixiong.manage.service.crm;

import java.util.List;

import com.ibaixiong.entity.MerchantProxyArea;

public interface MerchantProxyAreaService {
	
	void insertArea(MerchantProxyArea record);
	
	int updateArea(MerchantProxyArea record);
	
	List<MerchantProxyArea> queryByMerchantId(Long merchantId);
	
	MerchantProxyArea selectByAreaCode(Long areaCode);
	
	int deleteArea(Long merchantId,Long areaCode);
	
	int deleteAreaByCityId(Long merchantId);
}
