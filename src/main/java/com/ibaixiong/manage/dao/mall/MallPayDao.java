package com.ibaixiong.manage.dao.mall;

import com.ibaixiong.entity.MallPay;

public interface MallPayDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallPay record);

    int insertSelective(MallPay record);

    MallPay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallPay record);

    int updateByPrimaryKey(MallPay record);
    
    /**
     * 
     * @param orderNumber
     * @return
     */
    MallPay selectByOrderNumber(String orderNumber);
}