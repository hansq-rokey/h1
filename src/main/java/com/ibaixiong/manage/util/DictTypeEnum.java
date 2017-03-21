package com.ibaixiong.manage.util;

/*字典表类型*/
public enum DictTypeEnum {
	/*产品图片类型*/
	PRODUCT_PIC_TYPE("PRODUCT_PIC_TYPE"),
	/*支付方式*/
	PAY_TYPE("PAY_TYPE"),
	/*订单状态*/
	ORDER_STATUS("ORDER_STATUS"),
	/*送货时间*/
	DELIVER_TIME("DELIVER_TIME"),
	/*购物车产品状态*/
	CAR_STATUS("CAR_STATUS"),
	/*退货状态*/
	BACK_STATUS("BACK_STATUS"),
	/*换货状态*/
	EXCHANGE_STATUS("EXCHANGE_STATUS"),
	/*维修状态*/
	REPAIR_STATUS("REPAIR_STATUS"),
	/*售后类型*/
	SERVICE_TYPE("SERVICE_TYPE"),
	/*支付状态*/
	PAY_STATUS("PAY_STATUS"),
	/*产品状态*/
	PRODUCT_STATUS("PRODUCT_STATUS"),
	/*智能硬件时间戳错误发送联系人列表*/
	SMART_ERROR_SENDED_CONTACTS("SMART_ERROR_SENDED_CONTACTS"),
	/*智能终端状态*/
	SMART_CONFIG("SMART_CONFIG"),
	/*金额变更类型*/
	BALANCE_CHANGE_STATUS("BALANCE_CHANGE_STATUS"),
	/*货到付款短信发送人*/
	COD_SENDED_MESSAGE_CONTACTS("COD_SENDED_MESSAGE_CONTACTS"),
	/*招商来源*/
	INVESTMENT_SOURCE_STATUS("INVESTMENT_SOURCE_STATUS"),
	/*代理商类型*/
	CITY_MERCHANT_TYPE("CITY_MERCHANT_TYPE");
	
	private String dictType;

	private DictTypeEnum(String dictType) {
		this.dictType = dictType;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}
	
	
	
}
