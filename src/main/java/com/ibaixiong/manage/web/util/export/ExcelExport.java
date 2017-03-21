package com.ibaixiong.manage.web.util.export;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.sun.media.sound.InvalidFormatException;

/**
 * @author 赵磊
 * @date 2016-2-26
 */
public class ExcelExport {
	
	/**
	 * excel适配器
	 */
	private ExcelAdapter excelAdapter;
	
	private Workbook workbook = null;
	
	private int currentPage = 0;
	
	private int tempPage;
	
	/**
	 * 开始插入数据的页
	 */
	private int beginPage = 0;

	public ExcelExport(ExcelAdapter excelAdapter) {
		this.excelAdapter = excelAdapter;
	}
	public void exportToResponse(HttpServletResponse response,String filename) throws Exception{
		response.setContentType("application/msexcel");
		response.setCharacterEncoding("UTF-8");
		//response.addHeader("Content-Disposition","attachment;filename=\"" + URLEncoder.encode(filename, "UTF-8") + "\"");
		response.addHeader("Content-Disposition","attachment;filename=\"" + new String(filename.getBytes("GB2312"),"ISO-8859-1")+ "\"");
		OutputStream outputStream = response.getOutputStream();
		export(outputStream);
		response.flushBuffer();
	}
	/**
	 * 导出excel 到指定的输出流
	 * @param outputStream
	 * @throws Exception 
	 */
	public void export(OutputStream outputStream) throws Exception{
		if(excelAdapter == null){
			throw new Exception("传入的适配器为null");
		}
		try {
			workbook = WorkbookFactory.create(new FileInputStream(excelAdapter.getExcelTemplatePath()));
			List<ExcelBody> excelBodys = excelAdapter.getExcelBodys();
			int size = excelBodys.size();
			boolean is_paging = false;
			if(size == 1){
				is_paging = true;
				workbook.cloneSheet(0);
				currentPage = 1;
				beginPage = 1;
			}
			insertHeadCells();
			for(ExcelBody excelBody:excelBodys){
				insertBodyCells(excelBody, is_paging);
			}
			if(size == 1){
				workbook.removeSheetAt(0);
			}
			workbook.write(outputStream);
			outputStream.flush();
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(outputStream != null){
				outputStream.close();
			}
		}

		
	}
	private void insertHeadCells(){
		ExcelHead excelHead = excelAdapter.getExcelHead();
		if(excelHead == null){
			return;
		}
		String expBegin  = excelHead.getExpBegin();
		String expEnd  = excelHead.getExpEnd();
		Sheet currentSheet = workbook.getSheetAt(currentPage);
		Map<String,Object> dataMap = excelHead.getDataMap();
		for(int i=currentSheet.getFirstRowNum();i<currentSheet.getLastRowNum();i++){
			Row row = currentSheet.getRow(i);
			for(int j=row.getFirstCellNum();j<row.getLastCellNum();j++){
				Cell cell = row.getCell(j);
				int cellType = cell.getCellType();
				switch (cellType) {
				case Cell.CELL_TYPE_STRING:
					String cellValue = cell.getStringCellValue();
					Iterator<String> iterator = dataMap.keySet().iterator();
					while(iterator.hasNext()){
						String key = iterator.next();
						String value = (String)dataMap.get(key);
						value = value==null?"":value;
						cellValue = cellValue.replace(expBegin+key+expEnd, value);
						cell.setCellValue(cellValue);
					}
					break;

				default:
					break;
				}
				
			}
		}
	}
	/**
	 * @param sheet
	 * @param excelBody
	 * @param is_paging 是否分页
	 */
	private void insertBodyCells(ExcelBody excelBody,boolean is_paging){
		List<Map<String,Object>> dataMaps = excelBody.getDataMaps();
		List<String> columnNames = excelBody.getColumnNames();
		int intRowCount = dataMaps.size();//总的记录条数
		int pageSize = excelBody.getPageSize();
		int intRowNum=0;//记录当前遍历到第几行
		int beginRowNum = excelBody.getBeginRowNum();//excel中的起始行号
		int beginColumnNum = excelBody.getBeginColumnNum();//excel中的起始列号
		if(pageSize > 0 && is_paging){
			for(Map<String,Object> dataMap: dataMaps){
				tempPage = intRowNum/pageSize + beginPage;
				if(currentPage < tempPage){
					currentPage = tempPage;
					workbook.cloneSheet(0);
					String sheetName=workbook.getSheetName(0)+"_"+currentPage;//HSSFWorkbook.ENCODING_UTF_16
					workbook.setSheetName(currentPage,sheetName);
				}
				int rowNum = intRowNum%pageSize + beginRowNum;
				insertRow(columnNames, dataMap, rowNum, beginColumnNum);
				
				intRowNum++;
			}
		}
		else{
			for(Map<String,Object> dataMap: dataMaps){
				int rowNum = intRowNum + beginRowNum;
				insertRow(columnNames, dataMap, rowNum, beginColumnNum);
				intRowNum++;
			}
		}
		
	}
	private void insertRow(List<String> columnNames,Map<String,Object> dataMap,int rowNum,int beginColumnNum){
		Sheet currentSheet = workbook.getSheetAt(currentPage);
		Row row = currentSheet.getRow(rowNum); 
		if(row == null){
			row = currentSheet.createRow(rowNum);
			
		}
		for(int i = 0,col = beginColumnNum;i<columnNames.size();i++,col++){
			Object vo = dataMap.get(columnNames.get(i));
			String value = vo==null?"":vo.toString();
			Cell cell = row.getCell(col);
			if(cell == null){
				cell = row.createCell(col);
			}
			cell.setCellValue(value);
		}
	}
	
}
