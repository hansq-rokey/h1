package com.ibaixiong.manage.dao.crm;

import java.util.List;

import com.ibaixiong.entity.MerchantRebateMoneyRecord;

public interface MerchantRebateMoneyRecordDao {
	
    int deleteByPrimaryKey(Long id);

    int insert(MerchantRebateMoneyRecord record);

    int insertSelective(MerchantRebateMoneyRecord record);

    MerchantRebateMoneyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantRebateMoneyRecord record);

    int updateByPrimaryKey(MerchantRebateMoneyRecord record);
    
    List<MerchantRebateMoneyRecord> queryListByMerchantId(Long merchantId);
}