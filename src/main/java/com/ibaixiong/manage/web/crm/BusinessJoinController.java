/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.crm;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.entity.MallBusinessJoin;
import com.ibaixiong.manage.service.base.DictCodeService;
import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.service.crm.MallBusinessJoinService;
import com.ibaixiong.manage.util.DictTypeEnum;
import com.ibaixiong.manage.web.util.Response;
import com.ibaixiong.manage.web.util.export.ExcelUtil;

/**
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年7月12日
 * @since  1.0.0
 */
@RequestMapping("/crm/business")
@Controller
public class BusinessJoinController{
	Logger logger=LoggerFactory.getLogger(getClass());
	
	@Resource
	private MallBusinessJoinService businessJoinService;
	@Resource
	private DictCodeService dictCodeService;
	
	@RequestMapping("/list")
	public String  list(@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			@RequestParam(value="status",required=false) Byte status,String daterange,
			ModelMap modelMap){
		Date startTime = null;
		Date endTime = null;
		if(!"".equals(daterange) && daterange!=null){
			String[] strSplit = daterange.split("\\ ");
			startTime = DateUtil.parse(strSplit[0]);
			endTime = DateUtil.getDateEndTime(DateUtil.parse(strSplit[2]));
			modelMap.addAttribute("dateTime",(DateUtil.format(startTime, "yyyy-MM-dd")+" - "+DateUtil.format(endTime, "yyyy-MM-dd")));
		}
//		List<MallBusinessJoin> list= businessJoinService.queryBusinessJoins(status, pageNo, 10);
		List<MallBusinessJoin> list= businessJoinService.queryBusinessList(status,startTime,endTime,pageNo);
		PageInfo<MallBusinessJoin> pageInfo=new PageInfo<MallBusinessJoin>(list);
		List<DictCode> codes = dictCodeService.queryDictCodeList(DictTypeEnum.INVESTMENT_SOURCE_STATUS, null, null);
		modelMap.addAttribute("dictCodes", codes);
		modelMap.addAttribute("pageInfo", pageInfo);
		modelMap.addAttribute("status", status);
		return "crm/business_join_list";
	}
	
	/** 
	* @Description:导出功能
	* @param pageNo
	* @param status : 1,未读；2，已读
	* @param daterange
	* @param modelMap
	* @return String
	* @author hansq
	* @date 2017年3月16日 上午10:25:15   
	*/
	@RequestMapping("/export")
	public void  export(@RequestParam(value="status",required=false) Byte status,String daterange,
			ModelMap modelMap,HttpServletResponse response){
		Date startTime = null;
		Date endTime = null;
		if(!"".equals(daterange) && daterange!=null){
			String[] strSplit = daterange.split("\\ ");
			startTime = DateUtil.parse(strSplit[0]);
			endTime = DateUtil.getDateEndTime(DateUtil.parse(strSplit[2]));
			modelMap.addAttribute("dateTime",(DateUtil.format(startTime, "yyyy-MM-dd")+" - "+DateUtil.format(endTime, "yyyy-MM-dd")));
		}
        String fileName = "招商明细"+System.currentTimeMillis()+".xls"; //文件名 
        String sheetName = "招商明细";//sheet名
        String []title = new String[]{"姓名","联系电话","意向城市","备注","来源","创建时间","广告来源","投资金额","着落页","广告系列"};//标题
//		List<MallBusinessJoin> list= businessJoinService.queryBusinessJoins(status, pageNo, 10);
		List<MallBusinessJoin> list= businessJoinService.queryBusinessList(status,startTime,endTime);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        String[][] values = new String[list.size()][];
        for(int i=0;i<list.size();i++){
            values[i] = new String[title.length];
            //将对象内容转换成string
           MallBusinessJoin obj = list.get(i);
            values[i][0] = obj.getName();
            values[i][1] = obj.getTel();
            values[i][2] = obj.getCities();
            values[i][3] = obj.getRemark();
            values[i][4] = obj.getOrigin()+"";
            values[i][5] = sdf.format(obj.getCreateDateTime());
            values[i][6] = obj.getAdType()+"";
            values[i][7] = obj.getInvestMoney();
            values[i][8] = obj.getPageValue();
            values[i][9] = obj.getAdvertValue();
        }
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, values, null);
        
        //将文件存到指定位置  
        try {  
             this.setResponseHeader(response, fileName);  
             OutputStream os = response.getOutputStream();  
             wb.write(os);  
             os.flush();  
             os.close(); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    
     public void setResponseHeader(HttpServletResponse response, String fileName) {  
         try {  
              try {  
                   fileName = new String(fileName.getBytes(),"ISO8859-1");  
              } catch (UnsupportedEncodingException e) {  
                   // TODO Auto-generated catch block  
                   e.printStackTrace();  
              }  
              response.setContentType("application/octet-stream;charset=ISO8859-1");  
              response.setHeader("Content-Disposition", "attachment;filename="+ fileName);  
              response.addHeader("Pargam", "no-cache");  
              response.addHeader("Cache-Control", "no-cache");  
         } catch (Exception ex) {  
              ex.printStackTrace();  
         }  
    } 
	

	@ResponseBody
	@RequestMapping("/read")
	public String update(@RequestParam Long id){
		businessJoinService.updateRead(id);
		Response response=new Response();
		return JSON.toJSONString(response);
	}
	
	@ResponseBody	
	@RequestMapping("/delete")
	public String delete(@RequestParam Long id){
		businessJoinService.delete(id);
		Response response=new Response();
		return JSON.toJSONString(response);
	}
}
