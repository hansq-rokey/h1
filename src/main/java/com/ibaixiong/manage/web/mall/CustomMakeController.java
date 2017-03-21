/*
 * baixiong.com Inc.
 * Copyright (c) 1999-2001 All Rights Reserved.
 * 
 */
package com.ibaixiong.manage.web.mall;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.core.utils.ALiYunUtil;
import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.core.web.DateEditor;
import com.ibaixiong.entity.MallCustomPic;
import com.ibaixiong.entity.User;
import com.ibaixiong.manage.service.base.DictCodeService;
import com.ibaixiong.manage.service.bbs.UserService;
import com.ibaixiong.manage.service.mall.MallCustomPicService;
import com.ibaixiong.manage.service.mall.MallOrderService;
import com.ibaixiong.manage.util.DictTypeEnum;
import com.ibaixiong.manage.web.util.WebUtil;
import com.papabear.commons.entity.Pager;
import com.papabear.order.api.OrderService;
import com.papabear.order.entity.MallOrder;
/**
 * 私人订制
 * @author yaoweiguo
 * @Email  yaoweiguo@ibaixiong.com
 * @Description TODO
 * @date 2015年11月26日
 *
 */
@Controller
@RequestMapping("/mall/custom")
public class CustomMakeController {

	@Resource
	private MallCustomPicService  customPicService;
	@Resource
	private MallOrderService mallOrderService;
	@Resource
	private DictCodeService dictCodeService;
	@Resource
	private OrderService orderService;
	@Resource
	private UserService userService;
	/**
	 * 私人订制列表
	 * @return
	 */
	@RequestMapping("/list")
	public String customMakeList(@RequestParam(value = "payType", required = false) Integer payType,
			@RequestParam(value = "queryStr", required = false) String queryStr,
			@RequestParam(value="startTime",required=false)Date startTime,
			@RequestParam(value="endTime",required=false)Date endTime,
			@RequestParam(value="pageNo",defaultValue="0")Integer pageNo,
			HttpServletRequest request,
			Model model){
		if(startTime != null){
			model.addAttribute("startTime",DateUtil.format(startTime, "yyyy-MM-dd"));
		}
		if(endTime != null){
			model.addAttribute("endTime",DateUtil.format(endTime, "yyyy-MM-dd"));
			endTime=DateUtil.getDateEndTime(endTime);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("payType", payType);
		map.put("queryStr", queryStr);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("pageNo", pageNo);
		Pager<MallOrder> pager=orderService.queryCustomerOrder(queryStr, startTime, endTime, payType, (byte)1, pageNo, 10);
		for(MallOrder order:pager.getList()){
			User user=userService.getUser(order.getUserId());
			if(null==user)continue;
			order.setOrderItems(orderService.queryOrderItems(null, order.getOrderNumber()));
			order.setPhone(user.getPhone());
			order.setUserName(user.getUserName());
		}
//		List<MallOrder> list=mallOrderService.queryCustomMakeList(map);
		model.addAttribute("list", pager.getList());
		
//		PageInfo<MallOrder> pageInfo = new PageInfo<MallOrder>(list);
		model.addAttribute("pageInfo", pager);
		model.addAttribute("payType", payType);
		model.addAttribute("queryStr", queryStr);
		model.addAttribute("payTypeList", dictCodeService.queryDictCodeByDictType(DictTypeEnum.PAY_TYPE.getDictType()));
		return "/mall/custom_make_list";
	}
	
	@RequestMapping("/product/list")
	public String customProductList(@RequestParam String orderNumber,Model model){
		List<MallCustomPic> pics=customPicService.queryMallCustomPicByOrderNumber(orderNumber);
		model.addAttribute("pics", pics);
		return "/mall/custom_make_picture_list";
	}
	/**
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping("/download")
	public void download(@RequestParam Long id,HttpServletResponse response){
//		List<MallCustomPic> pics=customPicService.queryMallCustomPicByOrderNumber(orderNumber);
		MallCustomPic pic=customPicService.getmCustomPic(id);
		//1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("multipart/form-data");  
        String key=pic.getPath();
        response.setHeader("Content-Disposition", "attachment;fileName="+pic.getOrderNumber()+"_"+pic.getType()+"."+pic.getPicSuffx()+"");  
		ServletOutputStream out=null;
		InputStream inputStream=null;
		try {
			inputStream = ALiYunUtil.downloadFile(ALiYunUtil.BUCKET_NAME,key);
            //3.通过response获取ServletOutputStream对象(out)  
			out = response.getOutputStream();
            int b = 0;  
            byte[] buffer = new byte[1024];  
            while ((b = inputStream.read(buffer))!= -1){  
                out.write(buffer,0,b);
            }  
            inputStream.close();  
            out.close();  
            out.flush();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally{
        	if(out!=null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	if(inputStream!=null){
        		try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        }
	}
	
	/**
	 * 异步上传图片
	 * @return
	 */
	@RequestMapping("/upload")
	public void upload(@RequestParam(value = "file") MultipartFile file,@RequestParam String orderNumber,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map=new HashMap<String, Object>();
		Long adminId=WebUtil.getLoginUser(request).getId();
		BufferedImage image = null;
		try {
			image = ImageIO.read(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		int height = image.getHeight();
		int width = image.getWidth();

		MallCustomPic pic = new MallCustomPic();
		pic.setWidth(width);
		pic.setHeigth((float) height);
		int code=1;
		if(file==null||file.isEmpty()){
			code=0;
		}else{
			customPicService.upload(file,orderNumber,pic,adminId);
		}
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, "",map)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}
