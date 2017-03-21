package com.ibaixiong.manage.dao.crm;

import java.util.List;

import com.ibaixiong.entity.MerchantBondMoneyRecord;

public interface MerchantBondMoneyRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantBondMoneyRecord record);

    int insertSelective(MerchantBondMoneyRecord record);

    MerchantBondMoneyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantBondMoneyRecord record);

    int updateByPrimaryKey(MerchantBondMoneyRecord record);
    
    List<MerchantBondMoneyRecord> queryListByMerchantId(Long merchantId);
}