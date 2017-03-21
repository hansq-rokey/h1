package com.ibaixiong.manage.dao.crm;

import java.util.List;

import com.ibaixiong.entity.MerchantFirstGoodsMoneyRecord;

public interface MerchantFirstGoodsMoneyRecordDao {
    int deleteByPrimaryKey(Long id);

    int insert(MerchantFirstGoodsMoneyRecord record);

    int insertSelective(MerchantFirstGoodsMoneyRecord record);

    MerchantFirstGoodsMoneyRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantFirstGoodsMoneyRecord record);

    int updateByPrimaryKey(MerchantFirstGoodsMoneyRecord record);
    
	List<MerchantFirstGoodsMoneyRecord> queryListByMerchantId(Long merchantId);
}