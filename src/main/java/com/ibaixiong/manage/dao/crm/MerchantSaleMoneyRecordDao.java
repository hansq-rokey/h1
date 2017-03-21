package com.ibaixiong.manage.dao.crm;

import java.util.List;

import com.ibaixiong.entity.MerchantSaleMoneyRecord;

public interface MerchantSaleMoneyRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantSaleMoneyRecord record);

    int insertSelective(MerchantSaleMoneyRecord record);

    MerchantSaleMoneyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantSaleMoneyRecord record);

    int updateByPrimaryKey(MerchantSaleMoneyRecord record);
    
    List<MerchantSaleMoneyRecord> queryListByMerchantId(Long merchantId);
}