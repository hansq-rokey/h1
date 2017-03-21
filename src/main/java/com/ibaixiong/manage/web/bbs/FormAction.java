/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.bbs;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.BbsForm;
import com.ibaixiong.entity.BbsOperate;
import com.ibaixiong.entity.BbsPermissions;
import com.ibaixiong.entity.BbsRolePermissions;
import com.ibaixiong.manage.service.bbs.FormService;
import com.ibaixiong.manage.service.bbs.OperateService;
import com.ibaixiong.manage.service.bbs.PermissionsService;
import com.ibaixiong.manage.service.bbs.RolePermissionsService;
import com.ibaixiong.manage.web.util.BbsConstant.DisplayTypeEnum;
import com.ibaixiong.manage.web.util.WebUtil;

/**
 * 板块管理
 * @description
 * @author zhaolei
 * @create 2015年7月21日
 */
@Controller
@RequestMapping("/bbs/form")
public class FormAction {
	@Resource
	private FormService formService;
	@Resource
	private OperateService operateService;
	@Resource
	private PermissionsService permissionsService;
	@Resource
	private RolePermissionsService rolePermissionsService;

	/**
	 * 公共查询版块下级列表
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @return
	 * 
	 * {
		"code": 0,								 
	    "message": null, 
	    "result": {
	    	"form": [
	            {
	                "id": 1,					//ID
	                "name": 社区,					//名字
	                "url": "/articList.html",   //访问的路径
	                "tag": "bbs"
	            }, 
	            ......
	        ]
	    	}
		}
	 */
	@RequestMapping("/queryAjaxFormList.html")
	public void queryFormList(
			@RequestParam(value = "formId", required = false) Long formId,
			HttpServletResponse response){
		if(formId == null)
			formId = 0L;
		//改为ajax请求获取数据
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapData = new ArrayList<Map<String,Object>>();
		List<BbsForm> formList = formService.getFormByParentId(formId);
		for (BbsForm bbsForm : formList) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", bbsForm.getId());
			m.put("name", bbsForm.getName());
			m.put("url", bbsForm.getUrl());
			m.put("tag", bbsForm.getPermissionsTag());
			mapData.add(m);
		}
		map.put("form", mapData);
		String outStr = JSON.toJSONString(ResponseResult.result(0, "",map));
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(outStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	/**
	 * 查询页面List
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @param admin
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/queryFormList.html")
	public String queryFormList(
			@RequestParam(value = "msg", required = false) String msg,
			Model model) throws UnsupportedEncodingException {
		//获取相关数据
		List<BbsForm> dataList = new ArrayList<BbsForm>();
		List<BbsForm> forms1 = formService.getFormByParentId(0L);
		for (BbsForm bbsForm : forms1) {
			List<BbsForm> forms2 = formService.getFormByParentId(bbsForm.getId());
			List<BbsForm> dataList1 = null;
			if(forms2 != null && forms2.size()>0){
				dataList1 = new ArrayList<BbsForm>();
				for (BbsForm bbsForm2 : forms2) {
					List<BbsForm> dataListTemp = formService.getFormByParentId(bbsForm2.getId());
					bbsForm2.setChildList(dataListTemp);
					dataList1.add(bbsForm2);
				}
			}
			bbsForm.setChildList(dataList1);
			dataList.add(bbsForm);
		}
		model.addAttribute("formList",dataList);
		model.addAttribute("displays",DisplayTypeEnum.values());
		model.addAttribute("operateList",operateService.queryAll());
		if(StringUtils.isNotBlank(msg))
			model.addAttribute("msg", msg);
		return "/bbs/formList";
	}
	/**
	 * 保存
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveForm.html") 
	public String saveForm(
			@ModelAttribute(value = "bbsForm") BbsForm form,
			@RequestParam(value = "operates", required = false) String operates,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request,
			Model model) {
		String msg = "";
		int code = 0;
		String fileUrl = "";
		if(file != null){
			String contextpath = request.getContextPath();
			String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextpath +"/upload/";
	        String path = request.getSession().getServletContext().getRealPath("upload");  
	        String fileName = file.getOriginalFilename();  
		    fileName = new Date().getTime()+fileName;  
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }
	        //保存  
	        try {  
	            file.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        fileUrl = returnUrl + fileName;
		}
		if(StringUtils.isNotBlank(fileUrl))
			form.setThumUrl(fileUrl);
		boolean isAdd = true;
		if(form.getId() != null && form.getId().intValue()>0){
			//说明是修改
			//由于现在只修改权限所以该操作先注释如果后期有需要其他则放开该操作
			//formService.updateForm(form);
			isAdd = false;
		}else{
			form.setCreateDateTime(new Date());
			form.setStatus(Constant.Status.NORMAL.getStatus());
			formService.saveForm(form);
		}
		//处理权限表
		if(isAdd){
			if(StringUtils.isNotBlank(operates)){
				//说明是添加版块的操作对应的权限
				String[] ops = operates.split(",");
				insertPerOperates(ops, form);
			}
		}else{
			if(StringUtils.isNotBlank(operates)){
				form = formService.get(form.getId());
				//修改
				//获取当前保存过的权限
				List<BbsPermissions> pers = permissionsService.getPerByFormId(form.getId());
				String[] oldOper = new String[pers.size()];
				for (int i = 0; i < pers.size(); i++) {
					oldOper[i]=pers.get(i).getOperate().getId().toString();
				}
				String[] ops = operates.split(",");
				if(oldOper.length == 0){
					//没有找到设置过的直接新增进去
					insertPerOperates(ops, form);
				}else{
					String[] newIds = getNewIds(oldOper, ops);
					if(newIds != null && newIds.length>0){
							insertPerOperates(newIds, form);
					}
					String[] delIds = getDelIds(oldOper, ops);
					for (String operateId : delIds) {
						if(StringUtils.isNotBlank(operateId)){
							//删除时查询该权限的ID是否在已使用的角色权限绑定表中存在，如果存在需要提示他先进行角色权限绑定表的删除这条处理失败，其他信息保存成功
							BbsPermissions per = permissionsService.getPerByFormIdAndOperateId(form.getId(), Long.parseLong(operateId));
							//查询角色权限表中是否有使用到该权限的记录
							List<BbsRolePermissions> rpList = rolePermissionsService.getRolePermissionsByPerId(per.getId());
							if(rpList.size()>0){
								String operName = per.getOperate().getOperatename();
								msg = msg+operName+"在角色权限中有使用(";
								code = 1;
								String roleNames = "";
								for (BbsRolePermissions bbsRolePermissions : rpList) {
									roleNames = roleNames + bbsRolePermissions.getRole().getName() + ",";
								}
								msg = msg+roleNames+");";
							}else{
								//在角色权限列表中没有找到可以删除
								permissionsService.deletePer(form.getId(), Long.parseLong(operateId));
							}
						}
					}
				}
			}else{
				model.addAttribute("code", 1);//是否有错误提示
				model.addAttribute("msg", "必须至少有一个选中的权限。");//错误提示内容
			}
		}
		model.addAttribute("code", code);//是否有错误提示
		model.addAttribute("msg", msg);//错误提示内容
        return "redirect:/bbs/form/queryFormList.html";
	}
	private void insertPerOperates(String[] news,BbsForm form){
		for (String string : news) {
			if(StringUtils.isNotBlank(string)){
				Long operid = Long.parseLong(string);
				BbsOperate operate = operateService.getOperateById(operid);
				operate.setId(operid);
				BbsPermissions per = new BbsPermissions();
				per.setForm(form);
				per.setOperate(operate);
				per.setPerOper(form.getPermissionsTag()+":"+operate.getOperatetag());
				per.setStatus(Constant.Status.NORMAL.getStatus());
				permissionsService.save(per);
			}
		}
	}
	/**
	 * 获取需要新增的操作ID
	 * @author zhaolei
	 * @date 2015年7月21日
	 * @param oldOper
	 * @param ops
	 * @return
	 */
	private String [] getNewIds(String[] oldOper,String[] ops){
		String str = "";
		for (String newId : ops) {
			boolean isY = true;
			for (String old : oldOper) {
				if(newId.equals(old)){
					isY = false;	
				}
			}
			if(isY){
				//属于新增的ID
				str = str +newId+",";
			}
		}
		if(StringUtils.isNotBlank(str))
			str = str.substring(0, str.length()-1);
		return str.split(",") ;
	}
	/**
	 * 获取需要删除的操作ID
	 * @author zhaolei
	 * @date 2015年7月21日
	 * @param oldOper
	 * @param ops
	 * @return
	 */
	private String[] getDelIds(String[] oldOper,String[] news){
		String str = "";
		for (String old : oldOper) {
			boolean isY = true;
			for (String newid : news) {
				if(old.equals(newid)){
					isY = false;	
				}
			}
			if(isY){
				//属于要删除的ID
				str = str +old+",";
			}
		}
		if(StringUtils.isNotBlank(str))
			str = str.substring(0, str.length()-1);
		return str.split(",") ;
	}
	/*public static void main(String[] args) {
		String [] oldOper = {"1","2","3"};
		String [] ops = {"1","2"};
		System.out.println(getDelIds(oldOper, ops).toString());
		String [] ops1 = {"3","2","4","5"};
		System.out.println(getDelIds(oldOper, ops1).toString());
	}*/
	/**
	 * ajax获取已选中的权限
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAjaxFormPer.html")
	public void getAjaxFormPer(
			@RequestParam(value = "formId", required = false) Long formId
			,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		List<BbsPermissions> dataList = permissionsService.getPerByFormId(formId);
		Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("perList", dataList);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg,dataMap)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	@RequestMapping("/updateForm.html")
	public void updateForm(@ModelAttribute(value = "bbsForm") BbsForm bbsForm,
			Model model,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		formService.updateForm(bbsForm);
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
	@RequestMapping("/saveThumUrl.html")
	public String saveThumUrl(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request,
			Model model){
		String fileUrl = "";
		if(file != null){
			String contextpath = request.getContextPath();
			String returnUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextpath +"/upload/";
	        String path = request.getSession().getServletContext().getRealPath("upload");  
	        String fileName = file.getOriginalFilename();  
	        fileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
	        Random random = new Random();
		    fileName = new Date().getTime()+"_"+WebUtil.getLoginUser(request).getId()+"_"+random.nextInt(1000)+fileName;  
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        //保存  
	        try {  
	            file.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        fileUrl = returnUrl + fileName;
		}
		BbsForm form = new BbsForm();
		form.setId(id);
		form.setThumUrl(fileUrl);
		formService.updateForm(form);
		return "redirect:/bbs/form/queryFormList.html";
	}
}
