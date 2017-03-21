/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.ccm;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.CcmProcess;
import com.ibaixiong.entity.CcmQuestion;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.manage.service.ccm.CcmProcessService;
import com.ibaixiong.manage.service.ccm.QuestionService;
import com.ibaixiong.manage.web.util.CcmConstant;
import com.ibaixiong.manage.web.util.WebUtil;

/**
 * 客诉问题
 * @description
 * @author zhaolei
 * @create 2015年8月18日
 */
@Controller
@RequestMapping("/ccm/question")
public class QuestionAction {
	@Resource
	QuestionService questionService;
	@Resource
	CcmProcessService ccmProcessService;
	/**
	 * 
	 * @author zhaolei
	 * @date 2015年8月18日
	 * @param model
	 * @return
	 */
	@RequestMapping("/toAddOrUpdate")
	public String toAdd(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "queryType", required = false) Integer queryType,
			Model model) {
		if(id != null){
			CcmQuestion question = questionService.getQuestionById(id);
			model.addAttribute("question",question);
		}
		model.addAttribute("queryType",queryType);
		return "/ccm/customerAdd";
	}
	/**
	 * 保存问题对象
	 * @author zhaolei
	 * @date 2015年8月18日
	 * @param question
	 * @param selAdmin 选择的需要指派的一个接受任务的人
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/save.html")
	public String save(
			@ModelAttribute("question") CcmQuestion question,
			@RequestParam(value = "selAdmin", required = false) Long selAdmin,
			@RequestParam(value = "queryType", required = false) Integer queryType,
			HttpServletRequest request,
			//RedirectAttributes attr,
			Model model){
		//设置当前登陆人为指派人
		question.setAdmin(WebUtil.getLoginUser(request));//之派人
		question.setProcessStatus(CcmConstant.Status.UNDERWAR.getStatus());//处理状态为处理中状态
		question.setAssignTime(new Date());//指派时间
		//由客诉录入保存一定是新增
		if(question.getId() == null){
			question.setCcTime(DateUtil.parse(question.getCcTimeStr()));
			question.setRate(Byte.parseByte("0"));//进度默认为0
			question.setCreateDateTime(new Date());
			question.setStatus(Constant.Status.NORMAL.getStatus());
			questionService.insert(question);
		}else{
			//说明走的是立即处理由网上反馈的
			questionService.update(question);
			//attr.addAttribute("id",  question.getId());//重定向页面时添加条件 
		}
		//从选择的指派人中生成记录
		CcmProcess processSel = ccmProcessService.getProcessByQidAndLid(question.getId(), selAdmin);
		if(processSel == null){
			CcmProcess process = new CcmProcess();
			SysAdmin admin = new SysAdmin();
			admin.setId(selAdmin);
			process.setAdmin(admin);
			process.setQuestion(question);
			process.setCreateDateTime(new Date());
			process.setStatus(Constant.Status.NORMAL.getStatus());
			ccmProcessService.insert(process);
		}
		if(queryType != null && queryType.intValue()>0){
			return "redirect:/ccm/question/queryList.html?queryType="+queryType;
		}
		return "redirect:/ccm/question/toAddOrUpdate.html";
	}
	/**
	 * 客诉问题查询
	 * @author zhaolei
	 * @date 2015年8月18日
	 * @param queryType 查询类型
	 * @param questionStatus 下拉单据状态
	 * @param name 称呼等查询条件
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryList.html")
	public String queryList(
			@RequestParam(value = "queryType", required = false) Integer queryType,
			@RequestParam(value = "questionStatus", required = false) String questionStatus,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			HttpServletRequest request,
			Model model){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queryType", queryType);
		if(StringUtils.isNotBlank(questionStatus))
			map.put("questionStatus", questionStatus);
		map.put("name", name);
		if(queryType == 1 || queryType == 4){
			//添加当前登陆人为查询条件
			map.put("adminId", WebUtil.getLoginUser(request).getId());
		}
		if(pageNo == null){
			pageNo = 1;
		}
		map.put("pageNo", pageNo);
		List<CcmQuestion> list = questionService.queryList(map);
		PageInfo<CcmQuestion> pageInfo=new PageInfo<CcmQuestion>(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("dataList", list);
		model.addAttribute("queryType", queryType);
		model.addAttribute("questionStatus", questionStatus);
		model.addAttribute("name", name);
		model.addAttribute("pageNo", pageNo);
		if(queryType.intValue()==1)
			return "/ccm/customerAssignMeList";
		if(queryType.intValue()==2)
			return "/ccm/customerAllList";
		if(queryType.intValue()==3)
			return "/ccm/customerList";
		if(queryType.intValue()==4)
			return "/ccm/customerMeList";
		return "";
	}
	/**
	 * 客诉问题去详情页面
	 * @author zhaolei
	 * @date 2015年8月19日
	 * @param id
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/toQuestionView.html")
	public String toQuestionView(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "queryType", required = false) Integer queryType,
			HttpServletRequest request,
			Model model){
		CcmQuestion question = questionService.getQuestionById(id);
		List<CcmProcess> processList = ccmProcessService.queryProcessByQuestionId(id);
		model.addAttribute("question",question);
		model.addAttribute("processList",processList);
		model.addAttribute("queryType",queryType);
		return "/ccm/questionView";
	}
	/**
	 * 保存处理相关
	 * @author zhaolei
	 * @date 2015年8月19日
	 * @param question
	 * @param process
	 * @param selAdmin
	 * @param request
	 * @param attr
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveQuestionProcess.html")
	public String saveQuestionProcess(
			@ModelAttribute("question") CcmQuestion question,
			//@ModelAttribute("process") CcmProcess process,
			@RequestParam(value = "selAdmin", required = false) Long selAdmin,
			@RequestParam(value = "memo", required = false) String memo,
			@RequestParam(value = "queryType", required = false) Integer queryType,
			HttpServletRequest request,
			Model model){
		// TODO 这里记录下以免忘记了
		//页面的如果选择的100%的处理进度 selAdmin过来要为空 ，并且放入question对象的属性processStatus为已处理 后台不在做控制
		//除了100%进度后面的都必须要选择一下指派人员
		if(question.getRate().intValue()==3){
			//完成度为已完成所以说更改状态为完成
			question.setProcessStatus(CcmConstant.Status.END.getStatus());
		}
		questionService.update(question);
		if(question.getRate().intValue()!=3){//说明任务度不是100%
			if(selAdmin != null){//选择了这个说明未完成进行了人员中转
				//从选择的指派人中生成记录
				CcmProcess processSel = ccmProcessService.getProcessByQidAndLid(question.getId(), selAdmin);
				if(processSel == null){
					CcmProcess processn = new CcmProcess();
					SysAdmin admin = new SysAdmin();
					admin.setId(selAdmin);
					processn.setAdmin(admin);
					processn.setQuestion(question);
					processn.setCreateDateTime(new Date());
					processn.setStatus(Constant.Status.NORMAL.getStatus());
					ccmProcessService.insert(processn);
				}
			}
		}
		//通过当前登陆人ID和问题ID查找处理记录表中的memo字段为空的记录，如果有说明处理的是人家指派的任务，直接修改处理列表的记录
		SysAdmin loginAdmin = WebUtil.getLoginUser(request);//当前登陆人
		CcmProcess processsel = ccmProcessService.getProcessByQidAndLid(question.getId(), loginAdmin.getId());
		//如果没有找到说明是其他人点进去的需要新增一条处理记录
		if(processsel == null){
			CcmProcess processn = new CcmProcess();
			processn.setAdmin(loginAdmin);//处理人是当前登陆人
			processn.setMemo(memo);
			processn.setQuestion(question);
			processn.setCreateDateTime(new Date());
			processn.setStatus(Constant.Status.NORMAL.getStatus());
			ccmProcessService.insert(processn);
		}else{
			processsel.setMemo(memo);
			ccmProcessService.update(processsel);
		}
		return "redirect:/ccm/question/queryList.html?queryType="+queryType;
	}
	/**
	 * 关闭问题处理
	 * @author zhaolei
	 * @date 2015年8月20日
	 * @param response
	 */
	@RequestMapping("/closeQuestion.html")
	public void queryActiveUser(
			@RequestParam(value = "questionId", required = false) Long questionId,
			@RequestParam(value = "memo", required = false) String memo,
			HttpServletRequest request,
			HttpServletResponse response){
		CcmQuestion question = new CcmQuestion();
		question.setId(questionId);
		question.setProcessStatus(CcmConstant.Status.CLOSE.getStatus());
		questionService.update(question);
		//插入问题关闭的记录
		SysAdmin loginAdmin = WebUtil.getLoginUser(request);//当前登陆人
		CcmProcess processn = new CcmProcess();
		processn.setAdmin(loginAdmin);//处理人是当前登陆人
		processn.setMemo(memo);
		processn.setQuestion(question);
		processn.setCreateDateTime(new Date());
		processn.setStatus(Constant.Status.NORMAL.getStatus());
		ccmProcessService.insert(processn);
		String outStr = JSON.toJSONString(ResponseResult.result(0, ""));
		System.out.println(outStr);
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
}
