package com.ibaixiong.manage.web.app;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.core.utils.ALiYunUtil;
import com.ibaixiong.entity.AppRollImgs;
import com.ibaixiong.entity.AppRollImgsItem;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.manage.service.app.AppRollImgsItemService;
import com.ibaixiong.manage.service.app.AppRollImgsService;
import com.ibaixiong.manage.web.util.Response;
import com.ibaixiong.manage.web.util.WebUtil;

/**
 * @description app轮播图管理
 * @author zhaolei
 * @create 2015年12月08日
 */
@Controller
@RequestMapping("/app")
public class RollImgsAction {
	@Resource
	private AppRollImgsService appRollImgsService;
	@Resource
	private AppRollImgsItemService appRollImgsItemService;
	/**
	 * 获取轮播列表页面
	 * @author zhaolei
	 * @date 2015年12月8日
	 * @param pageNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/getList.html")
	public String getList(
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model){
		List<AppRollImgs> dataList = appRollImgsService.getList(pageNo);
		PageInfo<AppRollImgs> pageInfo=new PageInfo<AppRollImgs>(dataList);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("list", dataList);
		return "app/rollList";
	}
	/**
	 * 获取轮播图详情页面
	 * @author zhaolei
	 * @date 2015年12月8日
	 * @param rollId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getItemList.html")
	public String getItemList(
			@RequestParam(value = "rollId", required = false) Long rollId,
			Model model){
		List<AppRollImgsItem> dataList = appRollImgsItemService.getListByRollId(rollId);
		model.addAttribute("list", dataList);
		model.addAttribute("roll", appRollImgsService.get(rollId));
		return "app/rollItemList";
	}
	/**
	 * 保存轮播图页面
	 * @author zhaolei
	 * @date 2015年12月8日
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveRoll.html")
	public String saveRoll(
			@ModelAttribute(value = "roll") AppRollImgs roll,
			HttpServletRequest request,
			Model model){
		roll.setAdminId(WebUtil.getLoginUser(request).getId());
		roll.setCreateDateTime(new Date());
		roll.setStatus(Constant.Status.NORMAL.getStatus());
		if(roll.getId()!=null && roll.getId().intValue()>0){
			appRollImgsService.update(roll);
		}else{
			appRollImgsService.insert(roll);
		}
		return "redirect:/app/getList.html";
	}
	@RequestMapping("/delRoll.html")
	public String delRoll(
			@RequestParam(value = "id", required = false) Long id,
			Model model){
		appRollImgsService.del(id);
		return "redirect:/app/getList.html";
	}
	
	/**
	 * 保存轮播图详情页面
	 * @author zhaolei
	 * @date 2015年12月8日
	 * @param rollId
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveRollItem.html")
	public String saveRollItem(
			@ModelAttribute(value = "rollItem") AppRollImgsItem rollItem,
			HttpServletRequest request,
			Model model){
		rollItem.setAdminId(WebUtil.getLoginUser(request).getId());
		rollItem.setCreateDateTime(new Date());
		rollItem.setStatus(Constant.Status.NORMAL.getStatus());
		if(rollItem.getId()!=null && rollItem.getId().intValue()>0){
			appRollImgsItemService.update(rollItem);
		}else{
			appRollImgsItemService.insert(rollItem);
		}
		return "redirect:/app/getItemList.html?rollId="+rollItem.getRollId();
	}
	@RequestMapping("/delRollItem.html")
	public String delRollItem(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "rollId", required = false) Long rollId,
			Model model){
		appRollImgsItemService.del(id);
		return "redirect:/app/getItemList.html?rollId="+rollId;
	}
	/**
	 * 异步上传图片
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upload")
	public String uploadPic(@RequestParam(value = "file") MultipartFile file,HttpServletRequest request) {
		Response respone = new Response();
		Map<String, Object> map = new HashMap<String, Object>();
		if (file == null || file.isEmpty()) {
			respone.setSuccess(Boolean.FALSE);
			respone.setMessage("图片不能为空！");
			return JSON.toJSONString(respone);
		}
		SysAdmin admin = WebUtil.getLoginUser(request);
		String original = file.getOriginalFilename();
		String suffx = original.substring(original.lastIndexOf(".") + 1, original.length());
		String key = ALiYunUtil.createCmsKey(suffx, admin.getId());
		String url = "";
		try {
			ALiYunUtil.uploadFile(key, file);
			url = ALiYunUtil.IMAGE_URL + key;
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("url", url);
		respone.setResult(map);
		respone.setMessage("成功");
		return JSON.toJSONString(respone);
	}
}
