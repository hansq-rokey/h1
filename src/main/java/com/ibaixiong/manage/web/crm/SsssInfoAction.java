/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.crm;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.InvalidEnum;
import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.ProductPic;
import com.ibaixiong.entity.SsssCityMerchant;
import com.ibaixiong.entity.SsssInfo;
import com.ibaixiong.entity.SsssMerchantFormatPrice;
import com.ibaixiong.entity.SsssOrder;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.entity.User;
import com.ibaixiong.manage.service.base.DictCodeService;
import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.service.bbs.UserService;
import com.ibaixiong.manage.service.crm.ChannelAreaService;
import com.ibaixiong.manage.service.crm.SsssCityMerchantService;
import com.ibaixiong.manage.service.crm.SsssInfoService;
import com.ibaixiong.manage.service.crm.SsssMerchantFormatPriceService;
import com.ibaixiong.manage.service.crm.SsssOrderService;
import com.ibaixiong.manage.util.DictTypeEnum;
import com.ibaixiong.manage.web.util.DateEditor;
import com.ibaixiong.manage.web.util.WebUtil;
import com.papabear.order.api.OrderService;
import com.papabear.order.entity.MallOrder;
import com.papabear.order.entity.MallOrderHistory;
import com.papabear.order.entity.MallOrderItem;
import com.papabear.order.entity.MallOrderRevicerInformation;
import com.papabear.product.api.CategoryQueryService;
import com.papabear.product.api.ProductQueryService;
import com.papabear.product.entity.MallBasicCategoryModel;
import com.papabear.product.entity.MallBasicCategoryModelFormat;
import com.papabear.product.entity.MallProductPic;

/**
 * 4s店管理
 * @description
 * @author zhaolei
 * @create 2015年12月30日
 */
@Controller
@RequestMapping("/crm/info")
public class SsssInfoAction {
	@Resource
	private SsssCityMerchantService ssssCityMerchantService;
	@Resource
	private SsssInfoService ssssInfoService;
	@Resource
	private UserService userService;
	@Resource
	private ChannelAreaService channelAreaService;
	@Resource
	private SsssMerchantFormatPriceService ssssMerchantFormatPriceService;
	@Resource
	private CategoryQueryService categoryQueryService;
	@Resource
	private SsssOrderService ssssOrderService;
	@Resource
	private OrderService orderService;
	@Resource
	private DictCodeService dictCodeService;
	@Resource
	ProductQueryService productQueryService;
	/**
	 * 全部页面
	 * @author zhaolei
	 * @date 2015年12月28日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/info.html")
	public String info(Model model,@RequestParam(value="pageNo",defaultValue="1")Integer pageNo) {
		if(pageNo==null){
			pageNo=1;
		}
		List<SsssCityMerchant> cityList = ssssCityMerchantService.getList(pageNo);
		model.addAttribute("dataList",cityList);
		return "crm/cityInfo_new";
	}
	/**
	 * 查询页面(状态status=1)List
	 * @author zhaolei
	 * @date 2015年12月28日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/list.html")
	public String toList(
			@RequestParam(value = "pid", required = false) Long pid,
			String linkTel,String daterange,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model) {
		if("".equals(linkTel)){
			linkTel = null;
		}
		if(pageNo==null){
			pageNo=1;
		}
		Date startTime = null;
		Date endTime = null;
		if(!"".equals(daterange) && daterange!=null){
			String[] strSplit = daterange.split("\\ ");
			startTime = DateUtil.parse(strSplit[0]);
			endTime = DateUtil.getDateEndTime(DateUtil.parse(strSplit[2]));
			model.addAttribute("dateTime",(DateUtil.format(startTime, "yyyy-MM-dd")+" - "+DateUtil.format(endTime, "yyyy-MM-dd")));
		}
		List<SsssInfo> list = new ArrayList<SsssInfo>();
		List<SsssInfo> cityList = ssssInfoService.getListByPid(pid,linkTel,startTime,endTime,pageNo);
		PageInfo<SsssInfo> pageInfo = new PageInfo<SsssInfo>(cityList);
		for(SsssInfo ssssInfo : cityList){
			User user = userService.getUser(ssssInfo.getUserId());
			ssssInfo.setUser(user);
			list.add(ssssInfo);
		}
		model.addAttribute("linkTel", linkTel);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("dataList",list);
		model.addAttribute("pid",pid);
		return "crm/cityInfoList_new";
	}
	/**
	 * 查询页面(状态status=-2)List
	 * @author zhaolei
	 * @date 2015年12月28日
	 * @param admin
	 * @param model
	 * @return
	 */
	/*@RequestMapping("/stopList.html")
	public String toStopList(
			@RequestParam(value = "pid", required = false) Long pid,
			@RequestParam(value = "pageNo", defaultValue="1")Integer pageNo,
			Model model) {
		if(pid==null){
			pid = 0L;
		}
		List<SsssInfo> list = new ArrayList<SsssInfo>();
		List<SsssInfo> cityList = ssssInfoService.getStopListByPid(pid,pageNo);
		PageInfo<SsssInfo> pageInfo = new PageInfo<SsssInfo>(cityList);
		for(SsssInfo ssssInfo : cityList){
			User user = userService.getUser(ssssInfo.getUserId());
			ssssInfo.setUser(user);
			list.add(ssssInfo);
		}
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("dataList",list);
		model.addAttribute("pid",pid);
		return "crm/stopCityInfoList_new";
	}*/
	@RequestMapping("/save.html")
	public String save(@ModelAttribute(value = "info") SsssInfo info,
			HttpServletRequest request){
		info.setStatus(Constant.Status.NORMAL.getStatus());
		if(info.getId()==null){
			SysAdmin admin = WebUtil.getLoginUser(request);
			info.setAdminId(admin.getId());
			ssssInfoService.insert(info,request);
		}else{
			ssssInfoService.update(info);
		}
		return "redirect:/crm/info/list.html?pid="+info.getCityMerchantId();
	}
	/*@RequestMapping("/saveStopInfo.html")
	public String saveStopInfo(@ModelAttribute(value = "info") SsssInfo info,
			HttpServletRequest request){
		info.setStatus(Constant.Status.DISABLE.getStatus());
		if(info.getId()==null){
			SysAdmin admin = WebUtil.getLoginUser(request);
			info.setAdminId(admin.getId());
			ssssInfoService.insert(info,request);
		}else{
			ssssInfoService.update(info);
		}
		return "redirect:/crm/info/stopList.html?pid="+info.getCityMerchantId();
	}*/
	/**
	 * 判断当前新增的4S店是否合法
	 * @author zhaolei
	 * @date 2016年1月4日
	 * @param tel
	 * @param request
	 * @param response
	 */
	@RequestMapping("/check")
	public void check(
			@RequestParam(value = "tel", required = false) String tel,
			HttpServletRequest request,HttpServletResponse response){
		int code = 0;
		String msg = "";
		PrintWriter writer = null;
		try {
			User user = userService.getUserByPhone(tel);
			if(user != null){
				SsssInfo ssssInfo = ssssInfoService.getByUserId(user.getId());
				if(ssssInfo!=null && ssssInfo.getId().intValue()>0){
					//说明已经存在该4S的人员不允许添加了
					code = 1;
					msg = "在系统中已存在该手机号码用户为4S店的信息不允许重复添加。";
				}
			}
 			writer = response.getWriter();
			String outStr = JSON.toJSONString(ResponseResult.result(code, msg));
            writer.write(outStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	
	/**
	 * 禁用4s店
	 * @author zhaolei
	 * @date 2016年1月4日
	 * @param tel
	 * @param request
	 * @param response
	 */
	/*@RequestMapping("/stop")
	public void stop(
			@RequestParam(value = "id", required = false) Long id,Byte status,
			HttpServletRequest request,HttpServletResponse response){
		int code = 0;
		String msg = "";
		PrintWriter writer = null;
		try {
			SsssInfo cityInfo = ssssInfoService.getById(id);
			cityInfo.setStatus(status);
			ssssInfoService.update(cityInfo);
			msg = "禁用成功";
 			writer = response.getWriter();
			String outStr = JSON.toJSONString(ResponseResult.result(code, msg));
            writer.write(outStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	*/
	/**
	 * 启用4s店
	 * @author zhaolei
	 * @date 2016年1月4日
	 * @param tel
	 * @param request
	 * @param response
	 */
	@RequestMapping("/start")
	public void start(
			@RequestParam(value = "id", required = false) Long id,Byte status,
			HttpServletRequest request,HttpServletResponse response){
		int code = 0;
		String msg = "";
		PrintWriter writer = null;
		try {
			SsssInfo cityInfo = ssssInfoService.getById(id);
			cityInfo.setStatus(status);
			ssssInfoService.update(cityInfo);
			msg = "启用成功";
 			writer = response.getWriter();
			String outStr = JSON.toJSONString(ResponseResult.result(code, msg));
            writer.write(outStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	
	/**
	 * 4s店详情
	 */
	@RequestMapping("/orderDetails.html")
	public String orderDetails(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,Model model){
		SsssInfo cityInfo = ssssInfoService.getById(id);
		List<SsssOrder> dataList = ssssOrderService.selectByInfoId(id,pageNo);
		PageInfo<SsssOrder> pageInfo = new PageInfo<SsssOrder>(dataList);
		model.addAttribute("pageInfo",pageInfo);
		List<MallOrder> mallOrderList = new ArrayList<MallOrder>();
		for(SsssOrder ssssOrder : dataList){
			MallOrder mallOrder = orderService.getMallOrder(ssssOrder.getOrderNumber());
			mallOrderList.add(mallOrder);
		}
		model.addAttribute("dataList", mallOrderList);
		model.addAttribute("city", cityInfo);
		return "/crm/orderInfoList_new";
	}
	
	/**
	 * 4s店订单详情
	 */
	@RequestMapping("/orderDetail.html")
	public String orderDetail(String orderNumber,Model model){
		MallOrder order=orderService.getMallOrder(orderNumber);
		List<MallOrderItem> itemList = orderService.queryOrderItems(order.getUserId(), orderNumber);
		for(MallOrderItem mallOrderItem:itemList){
			mallOrderItem.setOrderItemExtend(
					orderService.getOrderItemExtend(order.getUserId(), mallOrderItem.getId()));
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("productId", mallOrderItem.getProductId());
			map.put("formatId", mallOrderItem.getProductModelFormatId());
			List<MallProductPic> pics = productQueryService.queryProductPicByFormatId(map);
			mallOrderItem.setPics(pics);
		}
		MallOrderRevicerInformation orderInformation = 
					orderService.getRevicerByUserIdAndOrderNumber(order.getUserId(), orderNumber);
		List<MallOrderHistory> listHistory = orderService.queryHistoryByOrderNumber(orderNumber);
		List<DictCode> dictCodes = null;
		if (order.getIsCustomMade() != null && order.getIsCustomMade().intValue() == 1) {
			dictCodes = dictCodeService.queryDictCodeList(DictTypeEnum.ORDER_STATUS);
		} else {
			dictCodes = dictCodeService.queryDictCodeList(DictTypeEnum.ORDER_STATUS, (byte) 0);
		}
		model.addAttribute("orderStatusList", dictCodes);
		for (DictCode code : dictCodes) {
			for (MallOrderHistory history : listHistory) {
				if (code.getDictCodeValue().trim().equals(history.getStatus().toString())) {
					code.setFlow(true);
					code.setOrderHistory(history);
				}
			}
		}
		model.addAttribute("dataList", itemList);
		model.addAttribute("orderInfomation", orderInformation);
		return "/crm/orderInfoDetail_new";
	}
	
	
	/**
	 * 设置代理商与产品规格的拿货价
	 * @author zhaolei
	 * @date 2016年1月11日
	 * @param id 代理商或4s店的ID
	 * @param pageNo
	 * @param keywords
	 * @param startTime
	 * @param endTime
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/setModelFormatMonyelist.html")
	public String setModelFormatMoneylist(
			@RequestParam(value = "ssssid", required = false) Long ssssId,//代理商或4s店的ID
			@RequestParam(value="pageNo",defaultValue="1") Integer pageNo,
			@RequestParam(value="keywords",required=false)String keywords,
			@RequestParam(value="startTime",required=false)Date startTime,
			@RequestParam(value="endTime",required=false)Date endTime,
			@RequestParam(value="error",required=false)String error, //错误提示码
			HttpServletRequest request,ModelMap model){
		if(org.apache.commons.lang.StringUtils.isNotBlank(keywords)){
			model.addAttribute("keywords",keywords);
		}
		if(startTime != null){
			model.addAttribute("startTime",DateUtil.format(startTime, "yyyy-MM-dd"));
		}
		if(endTime != null){
			model.addAttribute("endTime",DateUtil.format(endTime, "yyyy-MM-dd"));
		}
		if(pageNo == null){
			pageNo = 1;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("pageNo", pageNo);
		List<MallBasicCategoryModel> list = categoryQueryService.queryAllCategoryModel(map);
		PageInfo<MallBasicCategoryModel> pageInfo=new PageInfo<MallBasicCategoryModel>(list);
		//设置查询设置过的价格值
		list = setPrice(list,ssssId);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("list",list);
		model.addAttribute("flag", 1);
		model.addAttribute("ssssid", ssssId);
		model.addAttribute("error", error);
		return "crm/modelFormatMoneyInfoList";
	}
	private List<MallBasicCategoryModel> setPrice(List<MallBasicCategoryModel> list,Long ssssId){
		List<MallBasicCategoryModel> resList = new ArrayList<MallBasicCategoryModel>();
		for (MallBasicCategoryModel mallBasicCategoryModel : list) {
			List<MallBasicCategoryModelFormat> listTemp = mallBasicCategoryModel.getFormats();
			List<MallBasicCategoryModelFormat> listTempRes = new ArrayList<MallBasicCategoryModelFormat>();
			for (MallBasicCategoryModelFormat mallBasicCategoryModelFormat : listTemp) {
				//查询价格
				SsssMerchantFormatPrice ssssMerchantFormatPrice = ssssMerchantFormatPriceService.getSsssFormatPriceByFormatIdAndssssId(mallBasicCategoryModelFormat.getId(), ssssId);
				if(ssssMerchantFormatPrice != null){
					mallBasicCategoryModelFormat.setCrmMoney(ssssMerchantFormatPrice.getPrice());
					mallBasicCategoryModelFormat.setPriceId(ssssMerchantFormatPrice.getId());
				}else{
					mallBasicCategoryModelFormat.setCrmMoney(0f);
					mallBasicCategoryModelFormat.setPriceId(0l);
				}
				listTempRes.add(mallBasicCategoryModelFormat);
			}
			mallBasicCategoryModel.setFormats(listTempRes);
			resList.add(mallBasicCategoryModel);
		}
		return resList;
	}
	@RequestMapping("/savePrice.html")
	public String savePrice(@ModelAttribute(value = "info") SsssMerchantFormatPrice ssssMerchantFormatPrice,
			HttpServletRequest request){
		//检查一下设置的价格是否合理
		//获取产品价格
		String error = "0";
		MallBasicCategoryModelFormat basicCategoryModelFormat = categoryQueryService.getCategoryModelFormat(ssssMerchantFormatPrice.getFormatId());
		if(basicCategoryModelFormat != null){
			float price = ssssMerchantFormatPrice.getPrice();//输入的价格
			if(price<basicCategoryModelFormat.getPrice()){
				//判断设置的价格是否大于上级所设置的价格
				SsssInfo ssssInfo = ssssInfoService.get(ssssMerchantFormatPrice.getSsssId());
				Long cityMerchantId = ssssInfo.getCityMerchantId();
				SsssMerchantFormatPrice parentssssMerchantFormatPrice = ssssMerchantFormatPriceService.getSsssMerchantFormatPriceByFormatIdAndMerchantId(ssssMerchantFormatPrice.getFormatId(), cityMerchantId);
				if(parentssssMerchantFormatPrice != null){
					if(price>parentssssMerchantFormatPrice.getPrice()){//输入的价格合法
						if(ssssMerchantFormatPrice.getId()==null || ssssMerchantFormatPrice.getId().intValue() == 0){
							SysAdmin admin = WebUtil.getLoginUser(request);
							ssssMerchantFormatPrice.setCreatorId(admin.getId());
							ssssMerchantFormatPrice.setCreateDateTime(new Date());
							ssssMerchantFormatPrice.setInvalid(InvalidEnum.FALSE.getInvalidValue());
							ssssMerchantFormatPrice.setStatus(Constant.Status.NORMAL.getStatus());
							ssssMerchantFormatPriceService.insert(ssssMerchantFormatPrice);
						}else{
							ssssMerchantFormatPriceService.update(ssssMerchantFormatPrice);
						}
					}else{
						error = "4";//设置价格过小，要设置超过市代价格
					}
				}else{
					error = "3";//市代未设置价格，需要先设置市代价格
				}
			}else{
				error = "2";//设置价格过大超过了官网设置的价格
			}
		}else{
			error = "1";//产品未找到
		}
		return "redirect:/crm/info/setModelFormatMonyelist.html?error="+error+"&ssssid="+ssssMerchantFormatPrice.getSsssId();
	}
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}

