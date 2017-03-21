/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.sys;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.Constant.Status;
import com.ibaixiong.core.utils.Md5Util;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.core.utils.WebUtil;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.entity.SysAdminRole;
import com.ibaixiong.entity.SysOrg;
import com.ibaixiong.entity.SysRole;
import com.ibaixiong.manage.service.sys.SysAdminRoleService;
import com.ibaixiong.manage.service.sys.SysAdminService;
import com.ibaixiong.manage.service.sys.SysOrgService;
import com.ibaixiong.manage.service.sys.SysRoleService;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年7月2日-下午5:49:12
 */
@Controller
@RequestMapping("/system/admin")
public class AdminAction {
	@Resource
	SysAdminService adminService;
	@Resource
	SysRoleService roleService;
	@Resource
	SysOrgService sysOrgService;
	@Resource
	SysAdminRoleService sysAdminRoleService;

	// 去添加或修改用户
	@RequestMapping("/toAddOrUpdate")
	public String adduser(Model model,
			@RequestParam(value = "id", required = false) Long id) {
		SysAdmin admin = null;
		if(id != null && id.intValue()>0){
			//说明是修改
			admin = adminService.getAdminById(id);
		}
		model.addAttribute("admin", admin);
		model = getViewModelArgs(model);
		return "/system/admin/add";
	}
	private Model getViewModelArgs(Model model){
		List<SysRole> rolesList = roleService.getAllRoleList();
		List<SysOrg> orgList = sysOrgService.getAllOrgList();
		model.addAttribute("roles", rolesList);
		model.addAttribute("orgs", orgList);
		return model;
	}
	@RequestMapping("/checkLoginName.html")
	public void checkLoginName(
			@RequestParam(value = "loginName", required = false) String loginName,
			Model model,HttpServletResponse response, HttpServletRequest request) {
		//通过跟数据库的密码对比原密码，比对一致后修改
		String msg = "";
		int code = 0;
		SysAdmin dbadmin = adminService.getAdminByLoginName(loginName);
		if(dbadmin != null){
			msg = "登录名已被使用,请更换!";
			code = 1;
		}
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
	@RequestMapping("/save")
	public String saveuser(@ModelAttribute("sysAdmin") SysAdmin admin,
			@RequestParam(value = "roles", required = false) String roles,//页面选中的角色集合
			Model model) {
		if (admin.getId() == null || admin.getId().intValue() == 0) {
			admin.setCreateDateTime(new Date());
			admin.setStatus(Constant.Status.NORMAL.getStatus());
			admin.setUserPwd(Md5Util.encode(admin.getUserPwd()));
			adminService.saveAdmin(admin);
			model.addAttribute("msg", "用户创建成功！");
		}else{
			if(StringUtils.isNotBlank(admin.getUserPwd()))
				admin.setUserPwd(Md5Util.encode(admin.getUserPwd()));
			else
				admin.setUserPwd(null);//这样防止他发生密码修改
			adminService.updateAdmin(admin);
			model.addAttribute("msg", "用户修改成功！");
		}
		insertUserRole(admin, roles);
		model = getViewModelArgs(model);
		return "redirect:/system/admin/getAdminList.html";
	}
	private void insertUserRole(SysAdmin admin,String roles){
		//删除角色
		sysAdminRoleService.deleteAdminRoleByAdmin(admin);
		//添加
		if(StringUtils.isNotBlank(roles)){
			String [] roleIds = roles.split(",");
			for (String string : roleIds) {
				SysAdminRole ar = new SysAdminRole();
				ar.setAdmin(admin);
				SysRole role = new SysRole();
				role.setId(Long.parseLong(string));
				ar.setRole(role);
				sysAdminRoleService.insertAdminRole(ar);
			}
		}
		
	}
	@RequestMapping("/delete")
	public String deleteuser(Model mod, HttpServletRequest request) {
		Long id = WebUtil.getLong(request, "id");
		adminService.delectAdminById(id);
		SysAdmin admin = new SysAdmin();
		admin.setId(id);
		sysAdminRoleService.deleteAdminRoleByAdmin(admin);
		return "redirect:/system/admin/getAdminList.html";

	}
	/**
	 * 系统管理>个人中心页
	 * @author zhaolei
	 * @date 2015年7月8日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAdmin.html")
	public String getAdmin(Model model, HttpServletRequest request) {
		model.addAttribute("admin",com.ibaixiong.manage.web.util.WebUtil.getLoginUser(request));
		return "/system/admin";
	}
	/**
	 * 系统管理>个人中心页>修改手机号码 ajax 提交方式
	 * @author zhaolei
	 * @date 2015年7月8日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/updatePhone.html")
	public void updatePhone(
			@RequestParam(value = "phone", required = false) String phone,
			Model model,HttpServletResponse response, HttpServletRequest request) {
		SysAdmin admin = com.ibaixiong.manage.web.util.WebUtil.getLoginUser(request);
		admin.setPhone(phone);
		adminService.updateAdmin(admin);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(0, "")));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	/**
	 * 系统管理>修改密码 ajax 提交方式
	 * @author zhaolei
	 * @date 2015年7月8日
	 * @param admin
	 * @param oldPwd 旧密码
	 * @param model
	 * @return
	 */
	@RequestMapping("/updatePwd.html")
	public void updatePwd(
			@RequestParam(value = "oldPwd", required = false) String oldPwd,
			@RequestParam(value = "newPwd", required = false) String newPwd,
			Model model,HttpServletResponse response, HttpServletRequest request) {
		SysAdmin admin = com.ibaixiong.manage.web.util.WebUtil.getLoginUser(request);
		//通过跟数据库的密码对比原密码，比对一致后修改
		String msg = "";
		int code = 0;
		if(admin != null){
			//密码比对正确
			if(admin.getUserPwd().equals(Md5Util.encode(oldPwd))){
				admin.setUserPwd(Md5Util.encode(newPwd));
				adminService.updateAdmin(admin);
			}else{
				code = 1;
				msg = "原密码不正确";
			}
		}else{
			code = 2;
			msg = "用户未找到";
		}
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
	 * 系统管理>
	 * @author zhaolei
	 * @date 2015年7月8日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/getAdminList.html")
	public String getAdminList(@ModelAttribute("admin") SysAdmin admin,
			@RequestParam(value = "status", required = false) Byte status,
			@RequestParam(value = "queryName", required = false) String queryName,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model) {
		if(StringUtils.isBlank(queryName)){
			queryName = null;
		}
		if(pageNo == null){
			pageNo = 1;
		}
		List<SysAdmin> list = adminService.querySysAdminList(status, queryName,pageNo);
		PageInfo<SysAdmin> pageInfo=new PageInfo<SysAdmin>(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("adminList", list);
		model.addAttribute("queryName", queryName);
		model.addAttribute("status", status);
		model = getViewModelArgs(model);
		return "/system/adminList";
	}
	
	/**
	 * 系统管理>员工管理  禁用用户与解除禁用 ajax 提交方式
	 * @author zhaolei
	 * @date 2015年7月8日
	 * @param admin
	 * @param oldPwd 旧密码
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateStatus.html")
	public String updateStatus(
			@RequestParam(value = "status", required = false) Byte status,
			@RequestParam(value = "adminId", required = false) Long id,
			Model model,HttpServletResponse response) {
		//通过跟数据库的密码对比原密码，比对一致后修改
		SysAdmin opadmin = adminService.getAdminById(id);
		if(opadmin != null){
			//设置状态
			opadmin.setStatus(status);
			adminService.updateAdmin(opadmin);
		}
		return "redirect:/system/admin/getAdminList.html";
	}
	@RequestMapping("/getAdminById.html")
	public void getAdminById(
			@RequestParam(value = "id", required = false) Long id,
			Model model,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		//根据角色获取角色下的用户
		SysAdmin admin = adminService.getAdminById(id);
		Map<String, Object> mapData = new HashMap<String, Object>();
		mapData.put("loginName", admin.getLoginName());
		mapData.put("userPwd", "");
		mapData.put("userName", admin.getUserName());
		mapData.put("phone", admin.getPhone());
		mapData.put("orgId", admin.getOrg().getId());
		mapData.put("orgName", admin.getOrg().getName());
		//设置角色选中的 
		List<SysAdminRole> adminRoles = sysAdminRoleService.getSysAdminRoleByAdmin(admin);
		String roleids = "";
		for (SysAdminRole sysAdminRole : adminRoles) {
			if(StringUtils.isBlank(roleids)){
				roleids = sysAdminRole.getRole().getId().toString()+",";
			}else{
				roleids = roleids + sysAdminRole.getRole().getId().toString()+",";
			}
		}
		if(StringUtils.isNotBlank(roleids))
			roleids = roleids.substring(0, roleids.length()-1);
		mapData.put("roles", roleids);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg,mapData)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	
	@RequestMapping("/getAdminListByRole.html")
	public void getAdminListByRole(
			@RequestParam(value = "roleId", required = false) Long roleId,
			Model model,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		//根据角色获取角色下的用户
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapData = new ArrayList<Map<String,Object>>();
		List<SysAdminRole> dataList = sysAdminRoleService.getSysAdminRoleByRoleId(roleId);
		for (SysAdminRole adminRole : dataList) {
			Map<String, Object> m = new HashMap<String, Object>();
			SysAdmin admin = adminRole.getAdmin();
			m.put("id", admin.getId());
			m.put("name", admin.getUserName());
			mapData.add(m);
		}
		map.put("admins", mapData);
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
	@RequestMapping("/getAdminListAjax.html")
	public void getAdminListAjax(
			//@RequestParam(value = "roleId", required = false) Long roleId,
			Model model,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		//根据角色获取角色下的用户
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapData = new ArrayList<Map<String,Object>>();
		//List<SysAdminRole> dataList = sysAdminRoleService.getSysAdminRoleByRoleId(roleId);
		List<SysAdmin> dataList = adminService.querySysAdminList(Status.NORMAL.getStatus(), null,null);
		for (SysAdmin admin : dataList) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", admin.getId());
			m.put("name", admin.getUserName());
			mapData.add(m);
		}
		map.put("admins", mapData);
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
