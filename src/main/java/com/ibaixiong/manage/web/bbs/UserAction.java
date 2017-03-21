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

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.BbsUserRole;
import com.ibaixiong.entity.User;
import com.ibaixiong.manage.service.bbs.BbsUserRoleService;
import com.ibaixiong.manage.service.bbs.RoleService;
import com.ibaixiong.manage.service.bbs.UserService;
import com.ibaixiong.manage.web.util.Response;

/**
 * @description
 * @author zhaolei
 * @create 2015年7月12日
 */
@Controller
@RequestMapping("/bbs/user")
public class UserAction {
	@Resource
	UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private BbsUserRoleService bbsUserRoleService;
	private String msg = "";
	private int code = 0;
	/**
	 * 用户管理>用户列表
	 * @author zhaolei
	 * @date 2015年7月12日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/getUserList.html")
	public String getUserList(
			@RequestParam(value = "status", required = false) Byte status,
			@RequestParam(value = "queryName", required = false) String queryName,
			@RequestParam(value = "roleId", required = false) Long roleId,
			@RequestParam(value = "queryType", required = false) Integer queryType,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model) {
		//默认值设置
		if(status == null){
			status = Byte.parseByte("1");
		}
		if(queryType == null){
			queryType = 1;
		}
		if(StringUtils.isBlank(queryName)){
			queryName = null;
		}
		if(pageNo == null){
			pageNo = 1;
		}
		List<User> dataList = userService.queryUserList(status, queryName, roleId,pageNo);
		PageInfo<User> pageInfo=new PageInfo<User>(dataList);
		model.addAttribute("pageInfo",pageInfo);
		dataList = setRoleNames(dataList);
		model.addAttribute("userList",dataList);
		model.addAttribute("queryName",queryName);
		model.addAttribute("status",status);
		model.addAttribute("roleId",roleId);
		model.addAttribute("queryType",queryType);
		//查询角色列表
		model.addAttribute("roleList",roleService.getAll(null));
		if(queryType.intValue() == 1)
			return "/user/userList";
		if(queryType.intValue() == 2)
			return "/user/userDisableList";
		return "";
	}
	private List<User> setRoleNames(List<User> dataList){
		List<User> ul = new ArrayList<User>();
		for (User user : dataList) {
			//查询角色列表按照用户
			List<String> names = userService.queryRoleNames(user.getId());
			if(names != null && names.size()>0){
				String s = names.toString();
				user.setRoleNames(s);
			}
			ul.add(user);
		}
		return ul;
	}
	/**
	 * 用户管理>用户列表  解冻与冻结 ajax 提交方式
	 * @author zhaolei
	 * @date 2015年7月12日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateStatus.html")
	public void updateStatus(
			@ModelAttribute("user") User user
			,HttpServletResponse response) {
		if(user.getStatus().toString().equals("-2")){
			user.setBlockTime(new Date());
		}
		userService.updateStatus(user);
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
	@RequestMapping("/getAjaxUserRole.html")
	public void getAjaxFormPer(
			@RequestParam(value = "userId", required = false) Long userId
			,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("roleList", roleService.getRoleListByUserId(userId));
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
	@RequestMapping("/saveUserRole.html")
	public String saveUserRole(
			@RequestParam(value = "roles", required = false) String roles,
			@RequestParam(value = "userId", required = false) Long userId,
			Model model) {
		//先删除用户角色关联表数据
		bbsUserRoleService.deleteByUserId(userId);
		if(StringUtils.isNotBlank(roles)){
			//说明是添加版块的操作对应的权限
			String[] ops = roles.split(",");
			insertUserRole(ops, userId);
		}
		return "redirect:/bbs/user/getUserList.html";
	}
	private void insertUserRole(String[] ops,Long userId){
		for (String string : ops) {
			BbsUserRole userRole = new BbsUserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(Long.parseLong(string));
			bbsUserRoleService.isnert(userRole);
		}
	}
	@RequestMapping("/updateUser.html")
	public String saveUserAccount(
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "id", required = false) Long id,
			Model model) {
		if(type!=null){
			User user = userService.getUser(id);
			user.setType(Byte.parseByte(type));
			userService.updateType(user);
		}
		return "redirect:/bbs/user/getUserList.html";
	}
	@ResponseBody
	@RequestMapping("/getUser.html")
	public String getUser(@RequestParam(value = "id", required = false) Long id){
		Response response = new Response();
		User user = userService.getUser(id);
		response.setResult(user);
		return JSON.toJSONString(response);
	}
}
