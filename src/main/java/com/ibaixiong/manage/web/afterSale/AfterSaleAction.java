/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.afterSale;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.core.web.DateEditor;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.entity.User;
import com.ibaixiong.manage.service.afterSale.MallAfterSaleService;
import com.ibaixiong.manage.service.base.DictCodeService;
import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.service.bbs.UserService;
import com.ibaixiong.manage.util.DictTypeEnum;
import com.ibaixiong.manage.web.util.WebUtil;
import com.papabear.commons.entity.Pager;
import com.papabear.order.api.AfterSaleService;
import com.papabear.order.api.OrderService;
import com.papabear.order.entity.MallAfterSalesService;
import com.papabear.order.entity.MallAfterSalesServiceItem;
import com.papabear.order.entity.MallAfterSalesServiceLog;
import com.papabear.order.entity.MallOrderRevicerInformation;
import com.papabear.order.entity.OrderConstant.ServiceBackStatusEnum;
import com.papabear.product.api.CategoryQueryService;
import com.papabear.product.api.ProductQueryService;
import com.papabear.product.entity.MallBasicCategoryModelFormat;
import com.papabear.product.entity.MallProduct;

/**
 * 售后管理
 * @description
 * @author zhaolei
 * @create 2015年8月18日
 */
@Controller
@RequestMapping("/aftetSale")
public class AfterSaleAction {
	@Resource
	MallAfterSaleService mallAfterSaleService;
	@Resource
	private AfterSaleService afterSaleService;
	@Resource
	private UserService userService;
	@Resource
	private OrderService orderService;
	@Resource
	private ProductQueryService productQueryService;
	@Resource
	private CategoryQueryService  categoryQueryService;
	@Resource
	private DictCodeService dictCodeService;
	
	/**
	 * 查询售后款项
	 * @param model
	 * @param pageNo
	 * @return
	 */
	@RequestMapping("/refund/list")
	public String queryAfterSaleByRefund(ModelMap model,
			@RequestParam(value="pageNo",defaultValue="0")Integer pageNo,@RequestParam(value="keywords",required=false)String keywords,
			@RequestParam(value="startTime",required=false)Date startTime,@RequestParam(value="endTime",required=false)Date endTime){
		
		if(startTime != null){
			model.addAttribute("startTime",DateUtil.format(startTime, "yyyy-MM-dd"));
		}
		if(endTime != null){
			model.addAttribute("endTime",DateUtil.format(endTime, "yyyy-MM-dd"));
			endTime=DateUtil.getDateEndTime(endTime);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("pageNo", pageNo);
		List<Byte> statusList = new ArrayList<Byte>();
		statusList.add(ServiceBackStatusEnum.BACK_AGREE.getCode());
		statusList.add(ServiceBackStatusEnum.BACK_REIMBURSED.getCode());
		//查询同意和已退款的订单
		Pager<MallAfterSalesService> pager=afterSaleService.queryMallAfterSalesServices(keywords,statusList, (byte)1, startTime, endTime, pageNo, 10);
		for(MallAfterSalesService service:pager.getList()){
			User user=userService.getUser(service.getUserId());
			if(null==user)continue;
			service.setBxNum(user.getBxNum());
			service.setUserName(user.getUserName());
			service.setEmail(user.getEmail());
			service.setPhone(user.getPhone());
		}
//		List<MallAfterSalesService> list=mallAfterSaleService.queryAfterSalesByRefund(map);
		model.addAttribute("list", pager.getList());
		model.addAttribute("keywords", keywords);
//		PageInfo<MallAfterSalesService> pageInfo = new PageInfo<MallAfterSalesService>(list);
		model.addAttribute("pageInfo", pager);
		return "/aftersale/refundList";
	}
	
	
	@RequestMapping("/refund/detail")
	public String queryAfterSaleByServiceNumber(ModelMap model,@RequestParam(value="id")Long id){
		
//		model.addAttribute("list", mallAfterSaleItemService.queryAfterSalesServiceItemByServiceId(id));
		List<MallAfterSalesServiceItem> list=afterSaleService.queryAfterSalesServiceItems(id);
		for(MallAfterSalesServiceItem item:list){
			MallProduct product=productQueryService.getProduct(item.getProductId());
			MallBasicCategoryModelFormat format=categoryQueryService.getCategoryModelFormat(item.getProductModelFormatId());
			item.setProductTitle(product.getTitle());
			item.setProductModelFormatName(format.getName());
		}
		model.addAttribute("list", list);
		MallAfterSalesService service=afterSaleService.getAfterSalesService(id);
		model.addAttribute("service",service );
		MallOrderRevicerInformation receiver=orderService.getRevicerByUserIdAndOrderNumber(null, service.getServiceNumber());
		model.addAttribute("receiver",receiver );
		List<MallAfterSalesServiceLog> logList= afterSaleService.queryAfterSalesServiceLogs(id);
		for(MallAfterSalesServiceLog log:logList){
			model.addAttribute("status"+log.getStatus(), log);
		}
		return "/aftersale/detail";
	}
	
	@RequestMapping("/refund/operate")
	public void operateServiceOrder(
			@RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request,HttpServletResponse response){
		SysAdmin loginAdmin = WebUtil.getLoginUser(request);// 当前登陆人

		PrintWriter writer = null;
		try {
			//成功更新返回1
 			writer = response.getWriter();
			int flag=mallAfterSaleService.confirmRefund(id,loginAdmin);		
			String outStr = JSON.toJSONString(ResponseResult.result(flag>0?1:0, ""));
            writer.write(outStr);
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
