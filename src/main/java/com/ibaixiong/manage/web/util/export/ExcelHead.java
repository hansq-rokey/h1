package com.ibaixiong.manage.web.util.export;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于处理excel文件中公共的部分 例如制单人，时间等
 * @author 赵磊
 * @date 2016-2-26
 */
public class ExcelHead {
	/**
	 * 占位符起始的字符
	 */
	private String expBegin = "${";
	/**
	 * 占位符结束的字符
	 */
	private String expEnd = "}";
	
	/**
	 * 数据map<key,value>
	 */
	private Map<String,Object> dataMap;

	public ExcelHead() {
		dataMap = new HashMap<String, Object>();
	}

	public String getExpBegin() {
		return expBegin;
	}

	public void setExpBegin(String expBegin) {
		this.expBegin = expBegin;
	}

	public String getExpEnd() {
		return expEnd;
	}

	public void setExpEnd(String expEnd) {
		this.expEnd = expEnd;
	}
	
	public void addData(String key,Object value){
		dataMap.put(key, value);
	}

	public Map getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map dataMap) {
		this.dataMap = dataMap;
	}
	
}
