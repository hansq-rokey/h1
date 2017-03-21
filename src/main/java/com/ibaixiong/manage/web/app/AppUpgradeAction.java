package com.ibaixiong.manage.web.app;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.entity.AppInfo;
import com.ibaixiong.entity.AppInfoUpgradeLog;
import com.ibaixiong.manage.service.app.AppUpgradeService;
import com.ibaixiong.manage.web.util.Response;

@Controller
public class AppUpgradeAction {

	@Resource
	AppUpgradeService appUpgradeService;
	
	/**
	 * 查询app信息列表
	 * @param pageNo
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/app/info/list")
	public String queryAppInfos(@RequestParam(defaultValue="1")Integer pageNo,ModelMap modelMap){
		List<AppInfo> list=appUpgradeService.queryAppInfos(pageNo);
		PageInfo<AppInfo> pageInfo = new PageInfo<AppInfo>(list);
		modelMap.addAttribute("list", list);
		modelMap.addAttribute("pageInfo", pageInfo);
		return "app/appInfo_list";
	}
	/**
	 * 添加app信息
	 * @param appInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/app/info/add")
	public String addAppInfo(@ModelAttribute AppInfo appInfo){
		Response response=new Response();
		appUpgradeService.addAppInfo(appInfo);
		
		return JSON.toJSONString(response);
	}
	
	/**
	 * 查询某个app升级列表
	 * @param pageNo
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/app/log/list")
	public String queryAppInfoUpgradeLogs(@RequestParam(defaultValue="1")Integer pageNo,@RequestParam(defaultValue="xbb")String name,ModelMap modelMap){
		List<AppInfoUpgradeLog> list=appUpgradeService.queryAppInfoUpgradeLogs(name, pageNo);
		PageInfo<AppInfoUpgradeLog> pageInfo=new PageInfo<AppInfoUpgradeLog>(list);
		modelMap.addAttribute("pageInfo", pageInfo);
		List<AppInfo> appInfoList=appUpgradeService.queryAppInfos(pageNo);
		modelMap.addAttribute("appInfoList", appInfoList);
		modelMap.addAttribute("name", name);
		return "app/appInfo_upgradelog_list";
	}
	/**
	 * 添加app升级日志信息
	 * @param appInfo
	 * @return
	 */
	@RequestMapping("/app/log/add")
	public String addAppInfoUpgradeLogs(@ModelAttribute AppInfoUpgradeLog log,MultipartFile file){
		appUpgradeService.addAppInfoUpgradeLog(log,file);
		return "redirect:/app/log/list.html?name="+log.getAppAbbreviation();
	}
}
