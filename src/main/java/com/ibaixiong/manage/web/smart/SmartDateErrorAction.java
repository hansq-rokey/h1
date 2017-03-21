/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.smart;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.ibaixiong.entity.SmartDateError;
import com.ibaixiong.manage.service.smart.SmartDateErrorService;

/**
 * 智能设备时间问题处理
 * 
 * 项目名称：bes-manage-service 类名称：SmartDateErrorAction 类描述： 创建人：ywg 创建时间：2016年11月7日
 * 下午4:21:26 修改人：ywg 修改时间：2016年11月7日 下午4:21:26 修改备注：
 * 
 * @version
 *
 */
@Controller
@RequestMapping("/smart")
public class SmartDateErrorAction {
	@Autowired
	SmartDateErrorService smartDateErrorService;

	@RequestMapping("/date/list.html")
	public String smartList(Model model, HttpServletRequest request, @RequestParam(defaultValue = "2") Byte status,
			@RequestParam(defaultValue = "1") Integer pageNo, HttpSession session) {
		List<SmartDateError> listSmart = smartDateErrorService.querySmartDateErrors(status, pageNo);
		PageInfo<SmartDateError> pageInfo = new PageInfo<SmartDateError>(listSmart);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("listSmart", listSmart);
		model.addAttribute("status", status);
		return "smart/smart_date_error_list";
	}

	@RequestMapping("/date/update.html")
	public String updateStatus(@RequestParam Integer id, @RequestParam Byte status) {
		SmartDateError error = new SmartDateError();
		error.setId(id);
		error.setStatus(status);
		smartDateErrorService.updateSmartDateError(error);
		return "redirect:/smart/date/list.html";
	}
}
