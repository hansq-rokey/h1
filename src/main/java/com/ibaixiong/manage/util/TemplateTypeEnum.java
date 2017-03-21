package com.ibaixiong.manage.util;

public enum TemplateTypeEnum {
	/**全国包邮*/
    FREE((byte)10,"全国包邮"),
    /**自定义*/
    DEFINED((byte)20,"自定义"),
    /**按件数计算*/
    PIECE((byte)1,"按件数计算"),
    /**按重量计算*/
    WEIGHT((byte)2,"按重量计算");
    
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

	private TemplateTypeEnum(byte type){
		this.type = type;
	}
	
	private TemplateTypeEnum(byte type, String value) {
		this.type = type;
		this.value = value;
	}
}
