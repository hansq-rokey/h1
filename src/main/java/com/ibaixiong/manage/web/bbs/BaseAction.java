package com.ibaixiong.manage.web.bbs;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
import com.ibaixiong.entity.Grade;
import com.ibaixiong.entity.PointsType;
import com.ibaixiong.manage.service.bbs.GradeService;
import com.ibaixiong.manage.service.bbs.PointsTypeService;
/**
 * 基础数据管理
 * @description
 * @author zhaolei
 * @create 2015年7月24日
 */
@Controller
@RequestMapping("/bbs/base")
public class BaseAction {
	@Resource
	private GradeService gradeService;
	@Resource
	private PointsTypeService pointsTypeService;
	
	private String msg = "";
	private int code = 0;
	
	/**
	 * 查询增长过则页面List
	 * @author zhaolei
	 * @date 2015年7月24日
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryPointsTypeList.html")
	public String queryPointsTypeList(
			Model model) {
		model.addAttribute("pointsTypeList",pointsTypeService.getAll());
		return "bbs/pointsTypeList";
	}
	@RequestMapping("/updatePointsType.html")
	public void updateOrg(@ModelAttribute(value = "pointsType") PointsType type,
			Model model,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		pointsTypeService.update(type);
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
	 * 查询等级则页面List
	 * @author zhaolei
	 * @date 2015年7月24日
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryGradeList.html")
	public String queryGradeList(
			Model model) {
		model.addAttribute("gradeTypeList",gradeService.getAll());
		return "bbs/gradeList";
	}
	/**
	 * 新增保存等级
	 * @author zhaolei
	 * @date 2015年7月24日
	 * @param model
	 * @return
	 */
	@RequestMapping("/addGrade.html")
	public String addGrade(
			@ModelAttribute(value = "grade") Grade grade,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request,
			Model model) {
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
			grade.setUrl(fileUrl);
		grade.setStatus(Constant.Status.NORMAL.getStatus());
		gradeService.save(grade);
		return "redirect:/bbs/base/queryGradeList.html";
	}
}
