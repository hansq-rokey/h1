package com.ibaixiong.manage.dao.crm;

import java.util.List;

import com.ibaixiong.entity.MerchantProductProfit;

public interface MerchantProductProfitDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MerchantProductProfit record);

    int insertSelective(MerchantProductProfit record);

    MerchantProductProfit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MerchantProductProfit record);

    int updateByPrimaryKey(MerchantProductProfit record);
    
    int deleteByMerchantId(Long merchantId);
    
    List<MerchantProductProfit> queryList(Long merchantId);
}