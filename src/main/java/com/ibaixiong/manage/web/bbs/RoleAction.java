/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.SysTagConstant;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.BbsForm;
import com.ibaixiong.entity.BbsPermissions;
import com.ibaixiong.entity.BbsRole;
import com.ibaixiong.entity.BbsRolePermissions;
import com.ibaixiong.entity.CcmType;
import com.ibaixiong.manage.service.bbs.FormService;
import com.ibaixiong.manage.service.bbs.PermissionsService;
import com.ibaixiong.manage.service.bbs.RolePermissionsService;
import com.ibaixiong.manage.service.bbs.RoleService;

/**
 * 角色管理
 * @description
 * @author zhaolei
 * @create 2015年7月23日
 */
@Controller
@RequestMapping("/bbs/role")
public class RoleAction {
	@Resource
	private RoleService roleService;
	@Resource
	private FormService formService;
	@Resource
	private PermissionsService permissionsService;
	@Resource
	private RolePermissionsService rolePermissionsService;
	private String msg = "";
	private int code = 0;
	
	//private String[] privilegeids;
	
	/**
	 * 查询页面List
	 * @author zhaolei
	 * @date 2015年7月23日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryList.html")
	public String queryFormList(
			@RequestParam(value = "roleId", required = false) Long roleId,
			Model model) {
		if(roleId == null || roleId.intValue()==0){
			roleId = 1L;
		}
		model.addAttribute("roleList",roleService.getAll(SysTagConstant.bbs));
		return "bbs/roleList";
	}
	@RequestMapping("/perTree.html")
	public String perTree(
			@RequestParam(value = "roleId", required = false) Long roleId,
			Model model) {
		if(roleId == null || roleId.intValue()==0){
			roleId = 1L;
		}
		model.addAttribute("roleId",roleId);
		return "bbs/perTree";
	}
	@RequestMapping("/perTreeSelect.html")
	public void perTreeSelect(
			@RequestParam(value = "roleId", required = false) Long roleId
			,HttpServletResponse response) {
		if(roleId == null || roleId.intValue()==0){
			roleId = 1L;
		}
		//查询一级模块
		List<BbsForm> formList = formService.getFormByParentId(0L);
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		for (BbsForm bbsForm : formList) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("ID", bbsForm.getId());
			dataMap.put("NAME", bbsForm.getName());
			dataMap.put("CHECKED", null);
			dataMap.put("PARENT", 0);
			List<BbsPermissions> permissinList = permissionsService.getPerByFormId(bbsForm.getId());
			List<Map<String, Object>> children = new ArrayList<Map<String,Object>>();
			for (BbsPermissions bbsPermissions : permissinList) {
				Map<String, Object> dataMap11 = new HashMap<String, Object>();
				dataMap11.put("ID", bbsPermissions.getId()+"_"+bbsForm.getId());
				dataMap11.put("NAME", bbsPermissions.getOperate().getOperatename());
				//判断是否有选中
				//查询角色权限表中是否有这条数据
				BbsRolePermissions rp = rolePermissionsService.getRolePerByPerAndRole(bbsPermissions.getId(), roleId);
				if(rp != null)
					dataMap11.put("CHECKED", true);//设置默认选中
				else
					dataMap11.put("CHECKED", null);
				dataMap11.put("PARENT", bbsForm.getId());
				children.add(dataMap11);
			}
			dataMap.put("children", children);
			dataList.add(dataMap);
		}
		String str = JSON.toJSONString(dataList);
		PrintWriter writer = null;
	    try {
	   	 	writer = response.getWriter();
	        writer.write(str);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        writer.close();
	    }
	}
	
	@RequestMapping("/savePer.html")
	public void savePer(@RequestParam(value = "roleId", required = false) Long roleId,
			@RequestParam(value = "privilegeids", required = false) String [] privilegeids
			,HttpServletResponse response){
		System.out.println("更新角色的权限:"+roleId);
		rolePermissionsService.deleteByRole(roleId);
		if(privilegeids != null){//
			for (String privilege_id:privilegeids) {
				System.out.println(privilege_id);
				//判断中间以"_"隔开的是我们需要的数据
				if(privilege_id.indexOf("_")>=0){
					String perId = privilege_id.split("_")[0];
					BbsRolePermissions rp = new BbsRolePermissions();
					BbsPermissions permission = new BbsPermissions();
					permission.setId(Long.parseLong(perId));
					BbsRole role = new BbsRole();
					role.setId(roleId);
					rp.setRole(role);
					rp.setPermission(permission);
					rp.setStatus(Constant.Status.NORMAL.getStatus());
					rp.setCreateDateTime(new Date());
					rolePermissionsService.save(rp);
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", true);
		String str = JSON.toJSONString(map);
		PrintWriter writer = null;
	    try {
	   	 	writer = response.getWriter();
	        writer.write(str);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        writer.close();
	    }
	}
	/**
	 * 获取类型相关下拉
	 * @author zhaolei
	 * @date 2015年8月18日
	 * @return
	 * {
		"code": 0,								 
	    "message": null, 
	    "result": {
	    	"roles": [
		            {
		                "id": 1,					//ID
		                "name": 管理员,			//人员名称
		            }, 
	                ......
	        	]
	    	}
		}
	 */
	@RequestMapping("/getRolesListAjax.html")
	public void getRolesListAjax(
			Model model,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		//根据角色获取角色下的用户
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapData = new ArrayList<Map<String,Object>>();
		List<BbsRole> dataList = roleService.getAll(null);
		for (BbsRole type : dataList) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", type.getId());
			m.put("name", type.getName());
			mapData.add(m);
		}
		map.put("roles", mapData);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg,map)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
}
