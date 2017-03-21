/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.ccm;

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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.constant.Constant.Status;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.CcmType;
import com.ibaixiong.manage.service.ccm.CcmTypeService;

/**
 * 客诉问题
 * @description
 * @author zhaolei
 * @create 2015年8月18日
 */
@Controller
@RequestMapping("/ccm/type")
public class CcmTypeAction {
	@Resource
	CcmTypeService ccmTypeService;
	/**
	 * 获取类型相关下拉
	 * @author zhaolei
	 * @date 2015年8月18日
	 * @return
	 * {
		"code": 0,								 
	    "message": null, 
	    "result": {
	    	"typs": [
		            {
		                "id": 1,					//ID
		                "name": 客诉类型,			//人员名称
		            }, 
	                ......
	        	]
	    	}
		}
	 */
	@RequestMapping("/getTypeList.html")
	public void getTypeList(
			Model model,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		//根据角色获取角色下的用户
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapData = new ArrayList<Map<String,Object>>();
		List<CcmType> dataList = ccmTypeService.getAll();
		for (CcmType type : dataList) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", type.getId());
			m.put("name", type.getName());
			mapData.add(m);
		}
		map.put("typs", mapData);
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
	/**
	 * 去类型录入页面
	 * @author zhaolei
	 * @date 2015年8月18日
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAdd")
	public String toAdd(Model model) {
		//查询所有的已录入的列表
		List<CcmType> dataList = ccmTypeService.getAll();
		model.addAttribute("typeList",dataList);
		return "/ccm/customerType";
	}
	/**
	 * 保存类型
	 */
	@RequestMapping("/save.html")
	public String save(
			@ModelAttribute("type") CcmType type,
			HttpServletRequest request,
			Model model){
		type.setCreateDateTime(new Date());
		type.setStatus(Status.NORMAL.getStatus());
		ccmTypeService.insert(type);
		return "redirect:/ccm/type/toAdd.html";
	}
}
