package com.ibaixiong.manage.web.status;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.manage.service.base.DictCodeService;
import com.ibaixiong.manage.service.base.DictTypeService;
import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.service.base.mode.DictType;
import com.ibaixiong.manage.web.util.DateEditor;
import com.ibaixiong.manage.web.util.Response;
import com.ibaixiong.manage.web.util.WebUtil;

/**
 * 状态类型字典表数据显示
 */
@Controller
@RequestMapping("/status")
public class DicTypeController {
	@Resource
	private DictCodeService dictCodeService;
	@Resource
	private DictTypeService dictTypeService;
	
	/**
	 * 查询所有状态的类型
	 * @param model
	 * @return
	 */
	@RequestMapping("/list.html")
	public String list(Model model){
		List<DictType> list = dictTypeService.queryDictTypeList();
		model.addAttribute("types", list);
		return "status/statusList";
	}
	
	/**
	 * 查询状态详情
	 * @param model
	 * @param dictType
	 * @return
	 */
	@RequestMapping("/detail.html")
	public String detail(Model model,
			@RequestParam(value="dictType",required=false)String dictType){
		List<DictCode> codeList = dictCodeService.queryDictCodeByDictType(dictType);
		model.addAttribute("codes", codeList);
		model.addAttribute("dictType", dictType);
		return "status/statusDetail";
	}
	
	/**
	 * 新增状态类型
	 * @param model
	 * @param dictType
	 * @return
	 */
	@RequestMapping("/saveType.html")
	public String saveType(Model model,DictType type,HttpServletRequest request){
		type.setCreateDateTime(new Date());
		type.setUpdateTime(new Date());
		SysAdmin admin = WebUtil.getLoginUser(request);
		type.setAdminId(admin.getId());
		type.setStatus((byte)1);
		dictTypeService.insert(type);
		return "redirect:/status/list.html";
	}
	
	/**
	 * 新增状态
	 * @param model
	 * @param dictType
	 * @return
	 */
	@RequestMapping("/saveCode.html")
	public String saveCode(Model model,DictCode code,HttpServletRequest request){
		DictType type = dictTypeService.getByDictType(code.getDictType());
		code.setDictName(type.getDictTypeName());
		code.setCreateDateTime(new Date());
		code.setUpdateTime(new Date());
		SysAdmin admin = WebUtil.getLoginUser(request);
		code.setAdminId(admin.getId());
		code.setStatus((byte)1);
		code.setType((byte)0);
		code.setIsDisplay((byte)1);
		dictCodeService.save(code);
		return "redirect:/status/detail.html?dictType="+code.getDictType();
	}
	
	/**
	 * 查询指定类型的状态
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectType.html")
	public String selectType(Model model,String typeName){
		List<DictCode> list = dictCodeService.queryDictCodeByDictType(typeName);
		Response response = new Response();
		response.setResult(list);
		return JSON.toJSONString(response);
	}
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}
























