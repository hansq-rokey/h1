package com.ibaixiong.manage.service.crm;

import java.util.Date;
import java.util.List;

import com.ibaixiong.entity.SsssOrder;

public interface SsssOrderService {
	void ssssOrderProfitConvert(Date date);

	List<SsssOrder> selectByMerchantId(Long id,Integer pageNo);

	List<SsssOrder> selectByInfoId(Long id, Integer pageNo);
	
	void ssssAfterOrderProfit(Date date);
	
	SsssOrder getSsssOrderByOrderNumber(String orderNumber);
}
