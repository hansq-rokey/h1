package com.ibaixiong.manage.web.ding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.DdSetWorkingDay;
import com.ibaixiong.entity.DdSetWorkingDayLog;
import com.ibaixiong.manage.service.ding.DdSetWorkingDayLogService;
import com.ibaixiong.manage.service.ding.DdSetWorkingDayService;
/**
 * 日期设置
 * baixiong.com Inc.
 * Copyright (c) 1999-2001 All Rights Reserved.
 * 
 * @author yaoweiguo
 * @Email  yaoweiguo@ibaixiong.com
 * @Description TODO
 * @date 2016年2月25日
 *
 */
@Controller
@RequestMapping("/ding")
public class DateSettingController {
	
	private Logger logger=LoggerFactory.getLogger(getClass());
	
	@Resource
	private DdSetWorkingDayLogService ddSetWorkingDayLogService;
	@Resource
	private DdSetWorkingDayService ddSetWorkingDayService;
	
	@RequestMapping(value="/date/set",method=RequestMethod.GET)
	public String dateSet(ModelMap modelMap){
		List<DdSetWorkingDayLog> list=ddSetWorkingDayLogService.querySetWorkingDayLogsByDefaultYear();
		modelMap.addAttribute("list", list);
		return "/ding/date_set";
	}
	
	@ResponseBody
	@RequestMapping(value="/date/save",method=RequestMethod.POST)
	public String save(@RequestParam Integer year,@RequestParam Integer month,@RequestParam String days){
		ResponseResult result=new ResponseResult();
		result.setCode(1);
		result.setMessage("成功");
		DdSetWorkingDayLog wdl=ddSetWorkingDayLogService.shouldSendCountByYearMonth(year, month);
		try {
			if(wdl==null){
				DdSetWorkingDayLog setWorkingDayLog=new DdSetWorkingDayLog();
				setWorkingDayLog.setYyyy(year);
				setWorkingDayLog.setMm(month);
				setWorkingDayLog.setDays(days);
				ddSetWorkingDayLogService.insert(setWorkingDayLog);
			}else{
				wdl.setYyyy(year);
				wdl.setMm(month);
				wdl.setDays(days);
				ddSetWorkingDayLogService.update(wdl);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMessage("保存或更新失败");
		}
		logger.debug("year:{}----month:{}------days:{}",year,month,days);
		return JSON.toJSONString(result);
	}
	
	@ResponseBody
	@RequestMapping("/date/days")
	public String getddSetWorkingDays(@RequestParam Integer year,@RequestParam Integer month){
		ResponseResult result=new ResponseResult();
		Map<String, Object> map=new HashMap<String, Object>();
		List<String> days=new ArrayList<String>();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		result.setCode(1);
		result.setMessage("获取成功");
		try {
			List<DdSetWorkingDay> list= ddSetWorkingDayService.shouldSendCountByYearMonth(year.toString(), month.toString());
			Calendar calendar = Calendar.getInstance();
			for(DdSetWorkingDay day:list){
				calendar.setTime(day.getWorkDay());
				days.add(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
//				days.add(sdf.format(day.getWorkDay()));
			}
			map.put("days", days);
			result.setResult(map);;
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(0);
			result.setMessage("获取失败");
		}
		
		return JSON.toJSONString(result);
	}
}
