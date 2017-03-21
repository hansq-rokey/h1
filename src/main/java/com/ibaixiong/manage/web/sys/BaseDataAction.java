/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.core.utils.WebUtil;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.entity.SysModel;
import com.ibaixiong.entity.SysOrg;
import com.ibaixiong.manage.service.sys.SysAdminRoleService;
import com.ibaixiong.manage.service.sys.SysAdminService;
import com.ibaixiong.manage.service.sys.SysModelService;
import com.ibaixiong.manage.service.sys.SysOrgService;
import com.ibaixiong.manage.service.sys.SysRoleService;

/**
 * @description
 * @author zhaolei
 * @create 2015年7月10日
 */
@Controller
@RequestMapping("/system/baseData")
public class BaseDataAction {
	@Resource
	SysAdminService adminService;
	@Resource
	SysRoleService roleService;
	@Resource
	SysOrgService sysOrgService;
	@Resource
	SysAdminRoleService sysAdminRoleService;
	@Resource
	SysModelService sysModelService;

	@RequestMapping("/org/save")
	public String saveOrg(
			@ModelAttribute(value = "sysorg") SysOrg org,//页面选中的角色集合
			Model model) {
		if(org.getParentOrg() == null){
			SysOrg parentOrg = new SysOrg();
			parentOrg.setId(0L);
			org.setParentOrg(parentOrg);
		}
		org.setCreateDateTime(new Date());
		org.setStatus(Constant.Status.NORMAL.getStatus());
		sysOrgService.saveOrg(org);
		return "redirect:/system/baseData/org/getOrgList.html";
	}
	
	@RequestMapping("/org/delete")
	public String deleteOrg(Model mod, HttpServletRequest request) {
		Long id = WebUtil.getLong(request, "id");
		List<SysAdmin> list = adminService.queryAdminsByOrgIds(id);
		if(list == null || list.size()==0)
			sysOrgService.delete(id);
		else
			mod.addAttribute("msg", "删除失败该部门下有人员存在！");
		mod.addAttribute("orgList", sysOrgService.getAllOrgList());
		return "/system/orgList";
	}
	/**
	 * 系统管理>基础数据>部门设置
	 * @author zhaolei
	 * @date 2015年7月8日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/org/getOrgList.html")
	public String getOrgList(@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model) {
		if(pageNo == null){
			pageNo = 1;
		}
		List<SysOrg> list = sysOrgService.getOrgListPages(pageNo);
		PageInfo<SysOrg> pageInfo=new PageInfo<SysOrg>(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("orgList",list );
		return "/system/orgList";
	}
	@RequestMapping("/org/updateOrg.html")
	public void updateOrg(@ModelAttribute(value = "sysorg") SysOrg org,
			Model model,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		System.out.println(org.getName());
		sysOrgService.updateOrg(org);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	
	/**
	 * 系统管理>基础数据>菜单
	 * @author zhaolei
	 * @date 2015年7月8日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/model/getModelList.html")
	public String getModelList(Model model) {
		List<SysModel> modelList = sysModelService.getModelsByPid(0L);
		List<SysModel> dataList = new ArrayList<SysModel>();
		for (SysModel sysModel : modelList) {
			List<SysModel> childList = sysModelService.getModelsByPid(sysModel.getId());
			sysModel.setChildList(childList);
			dataList.add(sysModel);
		}
		model.addAttribute("modelList", dataList);
		return "/system/modelList";
	}
	@RequestMapping("/model/save")
	public String saveModel(
			@ModelAttribute(value = "sysModel") SysModel sysModel,//页面选中的角色集合
			Model model) {
		sysModel.setCreateDateTime(new Date());
		sysModel.setStatus(Constant.Status.NORMAL.getStatus());
		sysModelService.saveModel(sysModel);
		return  "redirect:/system/baseData/model/getModelList.html";
	}
	@RequestMapping("/model/updateModel.html")
	public void updateModel(@ModelAttribute(value = "sysModel") SysModel sysModel,
			Model model,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		sysModelService.updateModel(sysModel);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
}
