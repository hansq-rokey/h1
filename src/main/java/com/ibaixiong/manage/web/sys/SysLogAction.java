/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.entity.SysLog;
import com.ibaixiong.manage.service.sys.SysLogService;

/**
 * @description
 * @author zhaolei
 * @create 2015年7月10日
 */
@Controller
@RequestMapping("/system/log")
public class SysLogAction {
	@Resource
	SysLogService sysLogService;

	private String msg = "";
	private int code = 0;
	/**
	 * 系统管理>操作日志
	 * @author zhaolei
	 * @date 2015年7月8日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/getLogList.html")
	public String getLogList(Model model,
			@RequestParam(value = "queryName", required = false) String queryName,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "isown", required = false) String isown,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			HttpSession session) {
		if(StringUtils.isBlank(queryName))
			queryName = null;
		if(StringUtils.isBlank(startDate))
			startDate = null;
		else
			startDate = startDate +" 00:00:00";
		if(StringUtils.isBlank(endDate))
			endDate = null;
		else
			endDate = endDate +" 23:59:59";
		Long adminId = null;
		if(StringUtils.isNotBlank(isown) && isown.equals("on")){
			SysAdmin admin =  (SysAdmin)session.getAttribute("admin");
			adminId = admin.getId();
		}
		if(pageNo == null){
			pageNo = 1;
		}
		List<SysLog> list = sysLogService.querySysLogList(queryName, startDate, endDate, adminId,pageNo);
		PageInfo<SysLog> pageInfo=new PageInfo<SysLog>(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("logList", list);
		model.addAttribute("queryName", queryName);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("isown", isown);
		return "/system/logList";
	}
	
}
