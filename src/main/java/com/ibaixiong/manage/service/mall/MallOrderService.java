package com.ibaixiong.manage.service.mall;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ibaixiong.entity.MallOrder;
import com.ibaixiong.entity.util.OrderStatusEnum;

public interface MallOrderService {

	/**
	 * 列出所有订单信息
	 * @param orderStatusEnum  订单状态
	 * @param endDate		         到期时间
	 * @return
	 */
	void listObligation(OrderStatusEnum orderStatusEnum,Date endDate);
	/**
	 * 列出 所有完成的订单
	 * @param orderStatusEnum
	 * @param endDate
	 */
	void completeOrdersList(OrderStatusEnum orderStatusEnum,Date endDate);
	
//	List<MallOrder> queryList(Map<String,Object> map);
//	
//	MallOrder getByOrderNumber(String orderNumber);
//	
//	List<MallOrder> getByParentOrderNumber(String orderNumber);
	
	 /**
     * 查询私人订制的订单
     * @param map
     * @return
     */
//    List<MallOrder> queryCustomMakeList(Map<String, Object> map);
    
    /**
	 * 搜索订单
	 * @param keywords   用户名或订单号
	 * @param startTime  开始时间
	 * @param endTime    结束时间
	 * @param status     业务状态
	 * @return
	 */
//	List<MallOrder> queryByParamters(String keywords,Date startTime,Date endTime,Byte status,int pageNo, int offset);
	/**
	 * 修改支付的优惠金额
	 * @author zhaolei
	 * @date 2015年12月23日
	 * @param orderNumber
	 * @param discountPrice
	 */
//	void updateOrderDiscountPrice(String orderNumber,float discountPrice);
}
