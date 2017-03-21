package com.ibaixiong.manage.dao.crm;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.SsssOrder;

public interface SsssOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(SsssOrder record);

    List<SsssOrder> selectByMerchantId(Long merchantId);
    
    int insertSelective(SsssOrder record);

    SsssOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsssOrder record);

    int updateByPrimaryKey(SsssOrder record);
    
    List<SsssOrder> getOrderByNumber(String number);
    
    SsssOrder getSsssByOrderNumber(String orderNumber);
    /**
     * 获取未将冻结金额转成可用余额的数据
     * @author zhaolei
     * @date 2016年1月4日
     * @param status
     * @param endDate
     * @return
     */
    List<SsssOrder> getOrderByFreezedMoney(@Param("status")Byte status,@Param("endDate")Date endDate);
    /**
     * 获取退款订单的数据
     * @author 
     * @date 
     * @param status
     * @param endDate
     * @return
     */
    List<SsssOrder> getOrderByFreezedMoneyList(@Param("status")Byte status,@Param("endDate")Date endDate);
    /**
     * 查询4S店订单
     * @author zhaolei
     * @date 2016年1月8日
     * @param number
     * @param sssId
     * @return
     */
    SsssOrder getOrderByNumberAndSssId(@Param("number")String number,@Param("sssId")Long sssId);
    /**
     * 查询代理商订单
     * @author zhaolei
     * @date 2016年1月8日
     * @param number
     * @param sssId
     * @return
     */
    SsssOrder getOrderByNumberAndMerchantId(@Param("number")String number,@Param("merchantId")Long merchantId);

	/**
	 * 查询4s点详情
	 * @param id
	 * @return
	 */
	List<SsssOrder> selectByInfoId(Long id);
}