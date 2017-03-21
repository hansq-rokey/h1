package com.ibaixiong.manage.web.util.export;

import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * @author 赵磊
 * @date 2016-2-26
 */
public class ExcelBody {
	/**
	 * 表体数据开始的行号，注意：行号从0开始计数  例如：第5行的行号为4
	 */
	private int beginRowNum;
	
	/**
	 * 表体数据开始的列号，注意：列号从0开始计数 例如：第5列的列号为4
	 */
	private int beginColumnNum;
	
	/**
	 * 页大小，0表式不分页
	 */
	private int pageSize = 0;
	
	/**
	 * 列名的集合，对应数据map中的key 注意：列名的顺序要与模版保持一致
	 */
	private List<String> columnNames;
	
	/**
	 * 表体的数据map
	 */
	private List<Map<String,Object>> dataMaps;

	public ExcelBody(int beginRowNum, int beginColumnNum,List<String> columnNames) {
		this.beginRowNum = beginRowNum;
		this.beginColumnNum = beginColumnNum;
		this.columnNames = columnNames;
		dataMaps = new Vector<Map<String,Object>>();
	}
	public void addData(Map<String,Object> dataMap){
		dataMaps.add(dataMap);
	}
	public List<String> getColumnNames() {
		return columnNames;
	}
	public void setColumnNames(Vector<String> columnNames) {
		this.columnNames = columnNames;
	}
	
	public List<Map<String, Object>> getDataMaps() {
		return dataMaps;
	}
	public void setDataMaps(Vector<Map<String, Object>> dataMaps) {
		this.dataMaps = dataMaps;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getBeginRowNum() {
		return beginRowNum;
	}
	public int getBeginColumnNum() {
		return beginColumnNum;
	}
	
}
