package com.ibaixiong.manage.dao.crm;

import java.util.List;

import com.ibaixiong.entity.MerchantCouponMoneyRecord;

public interface MerchantCouponMoneyRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantCouponMoneyRecord record);

    int insertSelective(MerchantCouponMoneyRecord record);

    MerchantCouponMoneyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantCouponMoneyRecord record);

    int updateByPrimaryKey(MerchantCouponMoneyRecord record);
    
    List<MerchantCouponMoneyRecord> queryListByMerchantId(Long merchantId);
}