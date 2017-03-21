/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.smart;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.entity.Smart;
import com.ibaixiong.entity.SmartLog;
import com.ibaixiong.entity.SmartTask;
import com.ibaixiong.entity.SmartType;
import com.ibaixiong.entity.SmartUpgradeLog;
import com.ibaixiong.entity.SmartVersion;
import com.ibaixiong.entity.SmartVersionHistory;
import com.ibaixiong.manage.service.base.DictCodeService;
import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.service.smart.SmartService;
import com.ibaixiong.manage.service.smart.SmartTaskService;
import com.ibaixiong.manage.service.smart.SmartTypeService;
import com.ibaixiong.manage.service.smart.SmartUpgradeLogService;
import com.ibaixiong.manage.service.smart.SmartVersionHistoryService;
import com.ibaixiong.manage.service.smart.SmartVersionService;
import com.ibaixiong.manage.util.DictTypeEnum;
import com.papabear.commons.entity.enumentity.Constant.SmartTypeStatus;
import com.papabear.product.api.CategoryQueryService;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年11月6日-下午4:44:06
 */
@Controller
@RequestMapping("/smart")
public class SmartAction {
	@Autowired
	SmartService smartService;
	@Autowired
	SmartUpgradeLogService smartUpgradeLogService;
	@Resource
	private DictCodeService dictCodeService;
	@Resource
	private SmartTaskService smarkTaskService;
	@Autowired
	SmartTypeService smartTypeService;
	@Autowired
	SmartVersionService smartVersionService;
	@Resource
	CategoryQueryService categoryQueryService;
	@Autowired
	SmartVersionHistoryService smartVersionHistoryService;
	
	@RequestMapping("/list.html")
	public String smartList(Model model, HttpServletRequest request, HttpSession session) {
		Integer pageNo = 1;
		String strPageNo = request.getParameter("pageNo");
		if (StringUtils.isNotBlank(strPageNo)) {
			pageNo = Integer.valueOf(strPageNo);
		}
		List<Smart> listSmart = smartService.getListSmart(pageNo);
		Integer onlineNum = smartService.getOnlineSmartNum();
		PageInfo<Smart> pageInfo = new PageInfo<Smart>(listSmart);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("listSmart", listSmart);
		model.addAttribute("onlineNum", onlineNum);
		return "smart/list";
	}
	
	@RequestMapping("/onlinelist.html")
	public String smartOnlineList(@RequestParam(value="pageNo",defaultValue="1")Integer pageNo, 
			@RequestParam(value="sortName",defaultValue="date")String sortName,//date,version
			@RequestParam(value="type",defaultValue="2")Integer type,//1 升序  2 降序
			Model model, HttpServletRequest request, HttpSession session) {
		String keyword = request.getParameter("keywords");
		List<Smart> listSmart = smartService.queryOnlineSmartList(keyword,sortName,type,pageNo);
		PageInfo<Smart> pageInfo = new PageInfo<Smart>(listSmart);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("listSmart", listSmart);
		model.addAttribute("sortName", sortName);
		model.addAttribute("keyword", keyword);
		model.addAttribute("type", type);
		return "smart/list_line";
	}
	
	@ResponseBody
	@RequestMapping("/upgrade")
	public String upgrade(@RequestParam("id")Integer id){
		Smart smart=smartService.getSmartById(id);
		int code=1;
		if(smart!=null){
			code=smartUpgradeLogService.upgradeSmart(smart);
		}
		return JSON.toJSONString(code);
	}
	@ResponseBody
	@RequestMapping("/config")
	public String sendConfig(@RequestParam("id")Integer id){
		Smart smart=smartService.getSmartById(id);
		int code=1;
		if(smart!=null){
			code=smartUpgradeLogService.SendSystemConfigSmart(smart);
		}
		return JSON.toJSONString(code);
	}
	@RequestMapping("/detail.html")
	public String smartDetail(Model model, HttpServletRequest request, HttpSession session) {
		String bxcode = request.getParameter("bxcode");
		Integer pageNo = 1;
		String strPageNo = request.getParameter("pageNo");
		if (StringUtils.isNotBlank(strPageNo)) {
			pageNo = Integer.valueOf(strPageNo);
		}
		if (StringUtils.isNotBlank(bxcode)) {
			List<SmartLog> listSmartLog = smartService.getListSmartLogByBxcode(bxcode, pageNo);
			PageInfo<SmartLog> pageInfo = new PageInfo<SmartLog>(listSmartLog);
			model.addAttribute("pageInfo", pageInfo);
			model.addAttribute("listSmartLog", listSmartLog);
			model.addAttribute("bxcode", bxcode);
		}
		return "smart/detail";
	}
	
	@RequestMapping("/upgrade/detail")
	public String smartUpgradeLog(@RequestParam String bxid,@RequestParam(value="pageNo",defaultValue="1")Integer pageNo, ModelMap modelMap){
		List<SmartUpgradeLog> list=smartUpgradeLogService.querySmartUpgradeLogsByBxid(bxid,pageNo);
		PageInfo<SmartUpgradeLog> pageInfo=new PageInfo<SmartUpgradeLog>(list);
		modelMap.addAttribute("pageInfo", pageInfo);
		return "smart/upgrade_detail";
	}
	
	/**
	 * 查询智能终端状态详情
	 * @param model
	 * @param dictType
	 * @return
	 */
	@RequestMapping("/intelligence/detail.html")
	public String detail(Model model){
		List<DictCode> codeList = dictCodeService.queryDictCodeByDictType(DictTypeEnum.SMART_CONFIG.getDictType());
		model.addAttribute("codes", codeList);
		model.addAttribute("dictType", DictTypeEnum.SMART_CONFIG.getDictType());
		return "smart/statusDetail";
	}
	/**
	 * 修改智能终端状态值
	 * @param model
	 * @param dictType
	 * @return
	 */
	@RequestMapping("/intelligence/update.html")
	public String update(Model model,HttpServletRequest request){
		String editor_id = request.getParameter("editor_id");
		Long id = Long.parseLong(editor_id);
		String dictCodeValue = request.getParameter("dictCodeValue");
		DictCode code = dictCodeService.selectByPrimaryKey(id);
		code.setDictCodeValue(dictCodeValue);
		dictCodeService.update(code);
		return "redirect:/smart/intelligence/detail.html";
	}
	/**
	 * 任务列表
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("/taskList.html")
	public String taskList(Model model, HttpServletRequest request) {
		List<SmartTask> taskList = smarkTaskService.queryList();
		model.addAttribute("tasks", taskList);
		return "smart/smart_task_list";
	}
	
	/**
	 * 智能硬件类型列表
	 * @param modelMap
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/type/list")
	public String listSmartType(ModelMap modelMap,@RequestParam(defaultValue="1") Integer pageNo){
		List<SmartType> list=smartTypeService.listSmartTypeByStatus(SmartTypeStatus.NORMAL.getStatus(), pageNo);
		PageInfo< SmartType> pageInfo=new PageInfo<SmartType>(list);
		modelMap.addAttribute("pageInfo", pageInfo);
		return "smart/type_list";
	}
	
	/**
	 * 智能硬件版本列表
	 * @param modelMap
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/version/list")
	public String listSmartVersion(ModelMap modelMap,@RequestParam(defaultValue="1") Integer pageNo){
		List<SmartVersion> list=smartVersionService.listNormallSmartVersions(pageNo);
		PageInfo<SmartVersion> pageInfo=new PageInfo<SmartVersion>(list);
		modelMap.addAttribute("pageInfo", pageInfo);
		return "smart/version_list";
	}
	
	/**
	 * 智能硬件类型详情
	 * @param id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/type/detail")
	public String getSmartType(@RequestParam Long id,ModelMap modelMap){
		SmartType smartType= smartTypeService.getSmartType(id);
		modelMap.addAttribute("item", smartType);
		
		return "smart/type_detail";
	}
	
	/**
	 * 跳转至智能硬件类型添加页
	 * @return
	 */
	@RequestMapping("/type/toadd")
	public String toAddSmartType(ModelMap model){
		model.addAttribute("categoryList", categoryQueryService.queryBasicCategory(null));
		return "smart/type_add";
	}
	
	/**
	 * 智能类型数据保存
	 * @param smartType				智能类型对象
	 * @param logoFile				logo文件
	 * @param readyYesImgFile		状态就绪提示图
	 * @param readyNoImgFile		状态未就绪提示图
	 * @param resetImgFile			重置提示图
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/type/save")
	public String saveSmartType(@ModelAttribute SmartType smartType,
			@RequestParam("logoFile") MultipartFile logoFile,
			@RequestParam("readyYesImgFile") MultipartFile readyYesImgFile,
			@RequestParam("readyNoImgFile") MultipartFile readyNoImgFile,
			@RequestParam("resetImgFile")MultipartFile resetImgFile,
			ModelMap modelMap){
		
		boolean flag=true;
		if(logoFile==null||logoFile.isEmpty()){
			flag=false;
		}
		if(readyYesImgFile==null||readyYesImgFile.isEmpty()){
			flag=false;	
		}
		if(readyNoImgFile==null||readyNoImgFile.isEmpty()){
			flag=false;
		}
		if(resetImgFile==null||resetImgFile.isEmpty()){
			flag=false;
		}

		if(!flag){
			modelMap.addAttribute("categoryList", categoryQueryService.queryBasicCategory(null));
			return "smart/type_add";
		}
		String logoPath=smartTypeService.uploadImg(logoFile, smartType.getBxid4(),"logo");
		smartType.setLogoImg(logoPath);
		String readyYesPath=smartTypeService.uploadImg(readyYesImgFile, smartType.getBxid4(),"yes");
		String readyNoPath=smartTypeService.uploadImg(readyNoImgFile, smartType.getBxid4(),"no");
		String resetPath=smartTypeService.uploadImg(resetImgFile, smartType.getBxid4(),"reset");
		smartType.setReadyNoImg(readyNoPath);
		smartType.setReadyYesImg(readyYesPath);
		smartType.setResetImg(resetPath);
		smartTypeService.saveSmartType(smartType);
		return "redirect:/smart/type/list.html";
	}
	/**
	 * 保存版本数据
	 * @param smartVersion
	 * @param file
	 * @return
	 */
	@RequestMapping("/version/save")
	public String saveSmartVersion(@ModelAttribute SmartVersion smartVersion ,MultipartFile file){
		
		if(file==null||file.isEmpty()||file.getSize()==0){
			return "redirect:/smart/version/list.html";
		}
		
		smartVersion=smartVersionService.saveUpgradeFile(file, smartVersion);
		smartVersionService.saveSmartVersion(smartVersion);
		smartVersionHistoryService.saveSmartVersionHistory(smartVersion);
		return "redirect:/smart/version/list.html";
	}
	
	/**
	 * 升级包上传
	 * @param file
	 * @param id
	 * @return
	 */
	@RequestMapping("/version/upgrade")
	public String uploadUpgradeFile(@RequestParam MultipartFile file,@RequestParam Long id){
		SmartVersion sv=smartVersionService.getSmartVersion(id);
		if(sv==null){
			return "redirect:/smart/version/list.html";
		}
		smartVersionHistoryService.saveSmartVersionHistory(sv);
		
		SmartVersion smartVersion=new SmartVersion();
		smartVersion.setId(id);
		smartVersion=smartVersionService.saveUpgradeFile(file, smartVersion);
		smartVersionService.updateSmartVersion(smartVersion);
		
		
		return "redirect:/smart/version/list.html";
	}
	
	/*
	 * 查询历史版本记录
	 */
	@RequestMapping("/version/history/list")
	public String listSmartVersionHistoryByVersionId(@RequestParam Long id,
			@RequestParam(defaultValue="1") Integer pageNo,
			ModelMap modelMap){
		List<SmartVersionHistory> list= smartVersionHistoryService.listSmartVersionHistories(id, pageNo);
		PageInfo<SmartVersionHistory> pageInfo=new PageInfo<SmartVersionHistory>(list);
		modelMap.addAttribute("pageInfo", pageInfo);
		modelMap.addAttribute("id", id);
		return "smart/version_history_list";
	}
}
