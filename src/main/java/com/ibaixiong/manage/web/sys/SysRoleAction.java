package com.ibaixiong.manage.web.sys;

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

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.entity.SysModel;
import com.ibaixiong.entity.SysRole;
import com.ibaixiong.manage.service.sys.SysAdminRoleService;
import com.ibaixiong.manage.service.sys.SysAdminService;
import com.ibaixiong.manage.service.sys.SysModelService;
import com.ibaixiong.manage.service.sys.SysRoleService;

/**
 * @description
 * @author zhaolei
 * @create 2015年7月10日
 */
@Controller
@RequestMapping("/system/role")
public class SysRoleAction {
	@Resource
	SysAdminService adminService;
	@Resource
	SysRoleService roleService;
	@Resource
	SysAdminRoleService sysAdminRoleService;
	@Resource
	SysModelService sysModelService;

	/**
	 * 系统管理>员工管理>角色列表
	 * @author zhaolei
	 * @date 2015年7月8日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/getRoleList.html")
	public String getAdminList(@ModelAttribute("admin") SysAdmin admin,
			@RequestParam(value = "queryName", required = false) String queryName,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model) {
		if(StringUtils.isBlank(queryName)){
			queryName = null;
		}
		if(pageNo == null){
			pageNo = 1;
		}
		List<SysRole> list = roleService.querySysRoleList( queryName,pageNo);
		PageInfo<SysRole> pageInfo=new PageInfo<SysRole>(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("roleList",list);
		model.addAttribute("queryName",queryName);
		return "/system/roleList";
	}
	/**
	 * 删除角色
	 * @author zhaolei
	 * @date 2015年8月31日
	 * @param mod
	 * @param response
	 * @param id
	 */
	@RequestMapping("/delete")
	public String deleteRole(Model model,HttpServletResponse response,
			@RequestParam(value = "id", required = false) Long id) {
		int code = 0;
		String msg = null;
		if(sysAdminRoleService.checkRoleIncludeAdmin(id)){
			//说明该角色下有人员存在不允许删除
			code = 1;
			msg = "该角色下存在用户，不允许删除!";
		}else{
			roleService.deleteSysRole(id);
		}
		model.addAttribute("roleList", roleService.querySysRoleList(null,null));
		model.addAttribute("msg", msg);
		return "/system/roleList";
	}
	/**
	 * 去树形展现页面
	 * @author zhaolei
	 * @date 2015年8月31日
	 * @param roleId
	 * @param model
	 * @return
	 */
	@RequestMapping("/perModelTree.html")
	public String perTree(
			@RequestParam(value = "roleId", required = false) Long roleId,
			Model model) {
		model.addAttribute("roleId",roleId);
		return "/system/perModelTree";
	}
	@RequestMapping("/perTreeSelect.html")
	public void perTreeSelect(
			@RequestParam(value = "roleId", required = false) Long roleId
			,HttpServletResponse response) {
		//查询一级模块
		List<SysModel> modelList = sysModelService.getModelsByPid(0l);
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		//查询角色中的权限列表数据
		SysRole role = roleService.getRoseById(roleId);
		for (SysModel model : modelList) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("ID", model.getId());
			dataMap.put("NAME", model.getName());
			if(isCheck(role, model.getId().toString()))
				dataMap.put("CHECKED", true);
			else
				dataMap.put("CHECKED", null);
			dataMap.put("PARENT", 0);
			List<SysModel> childrenList = sysModelService.getModelsByPid(model.getId());
			List<Map<String, Object>> children = new ArrayList<Map<String,Object>>();
			for (SysModel childrenModel : childrenList) {
				Map<String, Object> dataMap11 = new HashMap<String, Object>();
				dataMap11.put("ID", childrenModel.getId());
				dataMap11.put("NAME", childrenModel.getName());
				//判断是否有选中
				//查询角色权限表中是否有这条数据
				if(isCheck(role, childrenModel.getId().toString()))
					dataMap11.put("CHECKED", true);//设置默认选中
				else
					dataMap11.put("CHECKED", null);
				dataMap11.put("PARENT", model.getId());
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
	private boolean isCheck(SysRole role,String modelId){
		boolean b = false;
		if(role != null){
			//比较角色中是否有存在的权限ID如果有下面设置为初始选中状态
			if(StringUtils.isNotBlank(role.getModels())){
				String modelsId = ","+role.getModels()+",";
				if(modelsId.indexOf(","+modelId+",")>=0){
					b = true;
				}
			}
		}
		return b;
	}
	@RequestMapping("/save")
	public void save(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "privilegeids", required = false) String [] privilegeids,
			HttpServletResponse response,
			Model model) {
		//中文ajax乱码处理方式
		int code = 0;
		String msg = "";
		SysRole role = new SysRole();
		//由于从客户端发过来的格式问题重新进行组装
		//privilegeids=1&privilegeids=10&privilegeids=11&privilegeids=12&privilegeids=18
		if(privilegeids !=null && StringUtils.isNotBlank(privilegeids[0])){
			if(privilegeids.length>0){
				String[] ss = privilegeids[0].split("&");
				String[] newss = new String[ss.length];
				int i = 0;
				for (String string : ss) {
					newss[i]=string.split("=")[1];
					i++;
				}
				privilegeids=newss;
			}
			role.setModels(getModelIds(privilegeids));
		}else{
			role.setModels("");
		}
		role.setName(roleName);
		if(id !=null && id.intValue()>0){
			role.setId(id);
			roleService.update(role);
		}else{
			role.setCreateDateTime(new Date());
			role.setStatus(Constant.Status.NORMAL.getStatus());
			roleService.insert(role);
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
	private String getModelIds(String[] ids){
		String str = "";
		if(ids != null && ids.length>0){
			for (String string : ids) {
				str = str+string+",";
			}
			if(StringUtils.isNotBlank(str)){
				str = str.substring(0,str.length()-1);
			}
		}
		return str;
	}
}
