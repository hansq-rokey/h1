package com.ibaixiong.manage.web.bbs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.BbsArticle;
import com.ibaixiong.entity.BbsArticleDetail;
import com.ibaixiong.entity.BbsForm;
import com.ibaixiong.manage.service.bbs.ArticleApplyService;
import com.ibaixiong.manage.service.bbs.ArticleDetailService;
import com.ibaixiong.manage.service.bbs.ArticleService;
import com.ibaixiong.manage.service.bbs.FormService;

/**
 * @description 帖子管理
 * @author zhaolei
 * @create 2015年7月14日
 */
@Controller
@RequestMapping("/bbs/article")
public class ArticleAction {
	
	@Resource
	private ArticleService articleService;
	@Resource
	private ArticleDetailService articleDetailService;
	@Resource
	private ArticleApplyService articleApplyService;
	@Resource
	private FormService formService;
	/**
	 * 社区管理>帖子管理
	 * @author zhaolei
	 * @date 2015年7月15日
	 * @param bbsArticle 查询的对象
	 * @param form
	 * @param sort 
	 * @param tagType
	 * @param isTop
	 * @param queryName
	 * @param activityStatus
	 * @param model
	 * @return
	 */
	@RequestMapping("/getArticleList.html")
	public String getArticleList(
			@RequestParam(value = "fromType", required = false) Integer fromType,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "form", required = false) Long form,
			@RequestParam(value = "sort", required = false) Integer sort,
			@RequestParam(value = "tagType", required = false) Integer tagType,
			@RequestParam(value = "isTop", required = false) Integer isTop,
			@RequestParam(value = "queryName", required = false) String queryName,
			@RequestParam(value = "activityStatus", required = false) Integer activityStatus,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model) {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		List<BbsArticle> dataList = null;
		queryMap.put("formType", fromType);//该参数必有表示查询的页签;1:普通帖子2:活动帖子3:举报帖子4:学院稿件
		if(form != null && form.intValue()>0){
			List<Long> list = new ArrayList<Long>();
			if(form.intValue() == 4||form.intValue()==30||form.intValue()==31)
				list.add(form);
			else{
				//查询form版块下的列表
				List<BbsForm> formList = formService.getFormByParentId(form);
				if(formList != null && formList.size()>0){
					for (BbsForm bbsForm : formList) {
						list.add(bbsForm.getId());
					}
				}else{
					//无子集
					list.add(999999l);//这个帖子级别的版块绝对没有
				}
			}
			queryMap.put("form", list);//板块
		}else{
			queryMap.put("form", null);//板块
		}
		if( sort != null &&  sort.intValue()>0){
			queryMap.put("sort",  sort.intValue()); 
		}else{
			queryMap.put("sort", null); 
		}
		if( tagType != null &&  tagType.intValue()>0){
			queryMap.put("tagType",  tagType.intValue()); 
		}else{
			queryMap.put("tagType", null); 
		}
		if(  isTop != null &&  isTop.intValue()>0){
			queryMap.put("isTop",  isTop.intValue()); 
		}else{
			queryMap.put("isTop", null); 
		}
		if(  StringUtils.isNotBlank(queryName)){
			queryMap.put("queryName",queryName); 
		}else{
			queryMap.put("queryName", null); 
		}
		if( activityStatus != null){
			queryMap.put("activityStatus",  activityStatus.intValue()); 
		}else{
			queryMap.put("activityStatus", null); 
		}
		if(status != null && status.intValue()>0){
			queryMap.put("xyStatus",  status); 
		}else{
			queryMap.put("xyStatus", null); 
		}
		if(pageNo == null){
			pageNo = 1;
		}
		queryMap.put("pageNo", pageNo);
		dataList = articleService.queryArticleList(queryMap);
		PageInfo<BbsArticle> pageInfo=new PageInfo<BbsArticle>(dataList);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("articleList", dataList);
		model.addAttribute("fromType", fromType);
		model.addAttribute("status", status);
		model.addAttribute("form", form);
		model.addAttribute("sort", sort);
		model.addAttribute("tagType", tagType);
		model.addAttribute("isTop", isTop);
		model.addAttribute("queryName", queryName);
		model.addAttribute("activityStatus", activityStatus);
		//1:普通帖子2:活动帖子3:举报帖子4:学院稿件
		if(fromType.intValue()==1)
			return "bbs/articleGeneralList";
		if(fromType.intValue()==2)
			return "bbs/articleActivityList";
		if(fromType.intValue()==3)
			return "bbs/articleReportList";
		if(fromType.intValue()==4)
			return "bbs/articleSchoolList";
		return "";
	} 
	
	/**
	 * 修改排序字段 取消排序字段
	 * @author zhaolei
	 * @date 2015年7月15日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/updateSort.html")
	public void updateSort(
			@ModelAttribute(value = "article") BbsArticle article
			,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		articleService.updateSort(article);
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
	 * 设置标签
	 * @author zhaolei
	 * @date 2015年7月15日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/updateTagType.html")
	public void updateTagType(
			@ModelAttribute(value = "article") BbsArticle article
			,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		articleService.updateTagType(article);
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
	 * 删除
	 * @author zhaolei
	 * @date 2015年7月15日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/delete.html")
	public String delete(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "fromType", required = false) Integer fromType,
			@RequestParam(value = "reason", required = false) Integer reason,
			@RequestParam(value = "reasonStr", required = false) String reasonStr
			,HttpServletResponse response) {
		articleService.delete(id,reason,reasonStr);
		return "redirect:/bbs/article/getArticleList.html?fromType="+fromType;
	}
	/**
	 * 关闭举报
	 * @author zhaolei
	 * @date 2015年9月2日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/close.html")
	public String close(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "fromType", required = false) Integer fromType,
			@RequestParam(value = "closeMemo", required = false) String closeMemo
			,HttpServletResponse response) {
		//输入关闭原因
		BbsArticle article = new BbsArticle();
		article.setId(id);
		article.setCloseMemo(closeMemo);
		article.setIsReport(Byte.parseByte("0"));
		article.setReportcount(0);
		articleService.updateArticle(article);
		return "redirect:/bbs/article/getArticleList.html?fromType="+fromType;
	}
	/**
	 * 录用与不录用
	 * @author zhaolei
	 * @date 2015年9月2日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/setStatus.html")
	public String setStatus(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "fromType", required = false) Integer fromType,
			@RequestParam(value = "status", required = false) Byte status
			,HttpServletResponse response) {
		//输入关闭原因
		BbsArticle article = new BbsArticle();
		article.setId(id);
		article.setStatus(status);
		articleService.updateArticle(article);
		return "redirect:/bbs/article/getArticleList.html?fromType="+fromType;
	}
	/**
	 * 跳详细页
	 * @author zhaolei
	 * @date 2015年7月15日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toArticleView.html")
	public String toArticleView(
			@RequestParam(value = "id", required = false) Long id,
			Model model) {
		BbsArticle article = articleService.getArticleById(id);
		model.addAttribute("article", article);
		return "/bbs/articleView";
	}
	/**
	 * 跳上头条页
	 * @author zhaolei
	 * @date 2015年7月15日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toTopView.html")
	public String toTopView(
			@RequestParam(value = "id", required = false) Long id,
			Model model) {
		BbsArticle article = articleService.getArticleById(id);
		BbsArticleDetail detail = articleDetailService.getArticleDetailByarticleId(id);
		model.addAttribute("article", article);
		model.addAttribute("detail", detail);
		return "/bbs/toTopView";
	}
	/**
	 * 保存头条
	 * @author zhaolei
	 * @date 2015年7月15日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveTopArticle.html")
	public String saveTopArticle(
			@RequestParam(value = "oldArticleId", required = false) Long oldArticleId,
			@RequestParam(value = "article", required = false) BbsArticle article,
			@RequestParam(value = "detail", required = false) BbsArticleDetail detail,
			Model model) {
		BbsArticle oldArticle = articleService.getArticleById(oldArticleId);
		if(article.getId() !=null && article.getId().intValue()>0){
			//说明是修改
			articleService.updateArticle(article);
			articleDetailService.updateArticleDetail(detail);
		}else{
			//新增
			articleService.saveArticle(article);
			articleDetailService.saveArticleDetail(detail);
		}
		model.addAttribute("article", article);
		model.addAttribute("detail", detail);
		return "/bbs/toTopView";
	}
	/**
	 * 
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toActivityArticleView.html")
	public String toActivityArticleView(
			@RequestParam(value = "id", required = false) Long id,
			Model model) {
		model.addAttribute("applyList", articleApplyService.queryApplyListByArticleId(id));
		return "/bbs/toTopView";
	}
	
	/**
	 * 修改单据状态
	 * @author zhaolei
	 * @date 2015年7月15日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/updateStatus.html")
	public void updateStatus(
			@RequestParam(value = "article", required = false) BbsArticle article
			,HttpServletResponse response) {
		String msg = "";
		int code = 0;
		articleService.updateStatus(article);
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
