package com.ibaixiong.manage.web.util.export;

import java.util.List;
import java.util.Vector;

/**
 * excel 数据导出 适配器
 * @author 赵磊
 * @date 2016-2-26
 */
public class ExcelAdapter {
	/**
	 * excel文件的表头
	 */
	private ExcelHead excelHead;
	
	/**
	 * excel文件的表体
	 */
	private List<ExcelBody> excelBodys;

	/**
	 * excel模版文件的绝对路径
	 */
	private String excelTemplatePath;
	
	public ExcelAdapter(String excelTemplatePath) {
		this.excelTemplatePath = excelTemplatePath;
		excelBodys = new Vector<ExcelBody>();
	}

	public ExcelHead getExcelHead() {
		return excelHead;
	}

	public void setExcelHead(ExcelHead excelHead) {
		this.excelHead = excelHead;
	}
	
	public void addExcelBody(ExcelBody excelBody){
		excelBodys.add(excelBody);
	}

	public List<ExcelBody> getExcelBodys() {
		return excelBodys;
	}

	public void setExcelBodys(List<ExcelBody> excelBodys) {
		this.excelBodys = excelBodys;
	}

	public String getExcelTemplatePath() {
		return excelTemplatePath;
	}

	public void setExcelTemplatePath(String excelTemplatePath) {
		this.excelTemplatePath = excelTemplatePath;
	}
	
}
