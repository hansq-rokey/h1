package com.ibaixiong.manage.web.sys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.SysRole;
import com.ibaixiong.manage.service.sys.SysRoleService;

/**
 * @description
 * @author zhaolei
 * @create 2015年7月10日
 */
@Controller
@RequestMapping("/system/model")
public class SysModelAction {
	@Resource
	SysRoleService roleService;

	private String msg = "";
	private int code = 0;
	@RequestMapping("/getModelTree")
	public void getModelTree(Model mod,HttpServletResponse response,
			@RequestParam(value = "roleId", required = false) Long roleId) {
		SysRole role = null;
		if(roleId != null && roleId.intValue()>0){
			role = roleService.getRoseById(roleId);
		}
		// TODO 树形菜单的制作
		//树形菜单获取，当有角色传进来的时候说明是要设置树形菜单是否选中操作后续做
		
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
