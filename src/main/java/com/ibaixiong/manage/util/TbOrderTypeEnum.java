package com.ibaixiong.manage.util;

public enum TbOrderTypeEnum {
	/**个人订单*/
    CORDER((byte)1,"个人订单金额变更记录"),
    /**经销商订单*/
    BORDER((byte)2,"经销商订单金额变更记录"),
    /**淘宝订单*/
    TBORDER((byte)3,"淘宝或天猫订单金额变更记录"),
    /**内部员工样品订单*/
    IBAIXIONGORDER((byte)4,"样品订单金额变更记录");
    
    private Byte type;
    private String value;
    
    public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private TbOrderTypeEnum(byte type){
		this.type = type;
	}
	
	private TbOrderTypeEnum(byte type, String value) {
		this.type = type;
		this.value = value;
	}
}
