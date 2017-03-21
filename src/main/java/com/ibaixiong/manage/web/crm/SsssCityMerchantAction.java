/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.web.crm;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.InvalidEnum;
import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.core.utils.Md5Util;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.ChannelArea;
import com.ibaixiong.entity.DdOrg;
import com.ibaixiong.entity.DdUser;
import com.ibaixiong.entity.DdUserMerchant;
import com.ibaixiong.entity.MallCustomPic;
import com.ibaixiong.entity.MerchantBondMoneyRecord;
import com.ibaixiong.entity.MerchantCouponMoneyRecord;
import com.ibaixiong.entity.MerchantFirstGoodsMoneyRecord;
import com.ibaixiong.entity.MerchantProduct;
import com.ibaixiong.entity.MerchantProductProfit;
import com.ibaixiong.entity.MerchantProxyArea;
import com.ibaixiong.entity.MerchantRebateMoneyRecord;
import com.ibaixiong.entity.MerchantSaleMoneyRecord;
import com.ibaixiong.entity.SsssCityMerchant;
import com.ibaixiong.entity.SsssInfo;
import com.ibaixiong.entity.SsssMerchantFormatPrice;
import com.ibaixiong.entity.SsssOrder;
import com.ibaixiong.entity.StaffRegion;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.entity.User;
import com.ibaixiong.entity.util.BalanceChangeStatusEnum;
import com.ibaixiong.manage.service.base.DictCodeService;
import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.service.bbs.UserService;
import com.ibaixiong.manage.service.crm.ChannelAreaService;
import com.ibaixiong.manage.service.crm.MerchantBondMoneyRecordService;
import com.ibaixiong.manage.service.crm.MerchantCouponMoneyRecordService;
import com.ibaixiong.manage.service.crm.MerchantFirstGoodsRecordService;
import com.ibaixiong.manage.service.crm.MerchantProductProfitService;
import com.ibaixiong.manage.service.crm.MerchantProductService;
import com.ibaixiong.manage.service.crm.MerchantProxyAreaService;
import com.ibaixiong.manage.service.crm.MerchantRebateMoneyRecordService;
import com.ibaixiong.manage.service.crm.MerchantSaleMoneyRecordService;
import com.ibaixiong.manage.service.crm.SsssCityMerchantService;
import com.ibaixiong.manage.service.crm.SsssInfoService;
import com.ibaixiong.manage.service.crm.SsssMerchantFormatPriceService;
import com.ibaixiong.manage.service.crm.SsssOrderService;
import com.ibaixiong.manage.service.crm.StaffRegionService;
import com.ibaixiong.manage.service.ding.DdUserMerchantService;
import com.ibaixiong.manage.service.ding.DdUserService;
import com.ibaixiong.manage.service.ding.DepartmentService;
import com.ibaixiong.manage.service.sys.SysAdminRoleService;
import com.ibaixiong.manage.service.sys.SysAdminService;
import com.ibaixiong.manage.util.DictTypeEnum;
import com.ibaixiong.manage.web.util.DateEditor;
import com.ibaixiong.manage.web.util.Response;
import com.ibaixiong.manage.web.util.WebUtil;
import com.papabear.commons.entity.Pager;
import com.papabear.commons.entity.enumentity.Constant.Status;
import com.papabear.commons.utils.StringUtils;
import com.papabear.order.api.OrderService;
import com.papabear.order.entity.MallOrder;
import com.papabear.order.entity.MallOrderHistory;
import com.papabear.order.entity.MallOrderItem;
import com.papabear.order.entity.MallOrderRevicerInformation;
import com.papabear.pay.api.PayService;
import com.papabear.pay.entity.PayHistory;
import com.papabear.product.api.CategoryQueryService;
import com.papabear.product.api.ProductQueryService;
import com.papabear.product.entity.MallBasicCategoryModel;
import com.papabear.product.entity.MallBasicCategoryModelFormat;
import com.papabear.product.entity.MallProduct;
import com.papabear.product.entity.MallProductPic;

/**
 * 承运商管理
 * @description
 * @author zhaolei
 * @create 2015年12月28日
 */
@Controller
@RequestMapping("/crm/cityMerchant")
public class SsssCityMerchantAction {
	@Resource
	private SsssCityMerchantService ssssCityMerchantService;
	@Resource
	private UserService userService;
	@Resource
	private SsssMerchantFormatPriceService ssssMerchantFormatPriceService;
	@Resource
	private SsssInfoService ssssInfoService;
	@Resource
	private CategoryQueryService categoryQueryService;
//	@Resource
//	private ChannelAreaService channelAreaService;
	@Resource
	private StaffRegionService staffRegionService;
	@Resource
	private SsssOrderService ssssOrderService;
	@Resource
	private OrderService orderService;
	@Resource
	private DictCodeService dictCodeService;
	@Resource
	ProductQueryService productQueryService;
	@Resource
	PayService payService;
	@Resource
	MerchantProductService merchantProductService;
	@Resource
	MerchantProductProfitService merchantProductProfitService;
//	@Resource
//	MallCustomPicService mallCustomPicService;
	@Resource
	DdUserMerchantService userMerchantService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private DdUserService ddUserService;
	@Resource
	private MerchantFirstGoodsRecordService merchantFirstGoodsRecordService;
	@Resource
	private MerchantCouponMoneyRecordService merchantCouponMoneyRecordService;
	@Resource
	private MerchantBondMoneyRecordService merchantBondMoneyRecordService;
	@Resource
	private MerchantSaleMoneyRecordService saleMoneyRecordService;
	@Resource
	private MerchantRebateMoneyRecordService rebateRecordService;
	@Resource
	private SysAdminService adminService;
	@Resource
	private DictCodeService DictCodeService;
	@Resource
	private MerchantProxyAreaService proxyAreaService;
	/**
	 * 全部页面
	 * @author zhaolei
	 * @date 2015年12月28日
	 * @param admin
	 * @param model
	 * @return
	 */
	@RequestMapping("/cityMerchant.html")
	public String cityMerchant(
			Model model,@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,String keywords,Byte dictCodeValue) {
		if("".equals(keywords)){
			keywords = null;
		}
		if(pageNo==null){
			pageNo=1;
		}
		/*Date startTime = null;
		Date endTime = null;
		if(!"".equals(daterange) && daterange!=null){
			String[] strSplit = daterange.split("\\ ");
			startTime = DateUtil.parse(strSplit[0]);
			endTime = DateUtil.getDateEndTime(DateUtil.parse(strSplit[2]));
			model.addAttribute("dateTime",(DateUtil.format(startTime, "yyyy-MM-dd")+" - "+DateUtil.format(endTime, "yyyy-MM-dd")));
		}*/
		List<SsssCityMerchant> cityList = ssssCityMerchantService.selectList(keywords,dictCodeValue,pageNo);
		
		PageInfo<MallProduct> productListB = productQueryService.queryProductBySystem(Status.NORMAL.getStatus(), null, (byte)1, 1, 500);
		model.addAttribute("productListB", productListB.getList());
		PageInfo<MallProduct> productListC = productQueryService.queryProductBySystem(Status.NORMAL.getStatus(), (byte)1, null, 1, 500);
		model.addAttribute("productListC", productListC.getList());
		//查询所有有效的部门
		List<DdOrg> orgList = departmentService.queryInvalidDepartment(InvalidEnum.FALSE);
		model.addAttribute("orgList", orgList);
		PageInfo<SsssCityMerchant> pageInfo = new PageInfo<SsssCityMerchant>(cityList);
		model.addAttribute("keywords", keywords);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("dataList",cityList);
		model.addAttribute("dictCodeValue", dictCodeValue);
		model.addAttribute("typeNames", dictCodeService.queryDictCodeByDictType(DictTypeEnum.CITY_MERCHANT_TYPE.getDictType()));
		return "crm/cityMerchantList_new";
	}
	
	/**
	 * 查询下级代理列表
	 * @param parentId
	 * @param model
	 * @return
	 */
	@RequestMapping("/downMerchant.html")
	public String selectMerchant(
			@RequestParam(value="parentId",required = false)Long parentId,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model){
		//上级代理
		SsssCityMerchant parentCity = ssssCityMerchantService.getById(parentId);
		//上上级代理
		model.addAttribute("ppCityId", parentCity.getParentCityMerchantId());
		if(parentCity.getParentCityMerchantId()!=null){
			model.addAttribute("ppCity", ssssCityMerchantService.getById(parentCity.getParentCityMerchantId()));
		}
		
		List<SsssCityMerchant> citys = ssssCityMerchantService.queryMerchantByParentId(parentId,pageNo);
		PageInfo<SsssCityMerchant> pageInfo = new PageInfo<SsssCityMerchant>(citys);
		model.addAttribute("citys", citys);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("parentId", parentId);
		model.addAttribute("parentCity", parentCity);
		model.addAttribute("typeNames", dictCodeService.queryDictCodeByDictType(DictTypeEnum.CITY_MERCHANT_TYPE.getDictType()));
		return "crm/cityMerchantList_down_new";
	}
	
	/**
	 * 增加代理商
	 * @return
	 */
	@RequestMapping("/addMerchant.html")
	public String addHtml(Model model){
		List<SsssCityMerchant> merchants = ssssCityMerchantService.getCitys();
		List<SsssCityMerchant> lists = new ArrayList<SsssCityMerchant>();
		//去除单店加盟
		for(SsssCityMerchant city : merchants){
			if(city.getLevel()!=null && city.getLevel()!=4){
				lists.add(city);
			}
		}
		model.addAttribute("merchants", lists);
		model.addAttribute("types", dictCodeService.queryDictCodeByDictType(DictTypeEnum.CITY_MERCHANT_TYPE.getDictType()));
		return "crm/add_city_merchant";
	}

	/**
	 * 编辑代理商信息
	 * @param cityId
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit.html")
	public String update(@RequestParam(value="cityId",required=false) Long cityId,Model model){
		model.addAttribute("merchants", ssssCityMerchantService.getCitys());
		SsssCityMerchant city = ssssCityMerchantService.getById(cityId);
		model.addAttribute("city", city);
		SsssCityMerchant parentCity = ssssCityMerchantService.getById(city.getParentCityMerchantId());
		List<DictCode> codes = dictCodeService.queryDictCodeByDictType(DictTypeEnum.CITY_MERCHANT_TYPE.getDictType());
		if(parentCity!=null){
			model.addAttribute("parentCity", parentCity);
			List<DictCode> lists = new ArrayList<DictCode>();
			for(DictCode code : codes){
				if(parentCity.getLevel()<Byte.parseByte(code.getDictCodeValue())){
					lists.add(code);
				}
			}
			model.addAttribute("types", lists);
		}else{
			model.addAttribute("types", codes);
		}
		model.addAttribute("areas", proxyAreaService.queryByMerchantId(cityId));
		return "crm/edit_city_merchant";
	}
	
	/**
	 * 根据上级代理类型，判断要添加代理商的可选类型
	 * @param cityId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectType.html")
	public String checkType(@RequestParam(value="cityId",required=false) Long cityId){
		Response response = new Response();
		SsssCityMerchant city = ssssCityMerchantService.getById(cityId);
		List<DictCode> codes = dictCodeService.queryDictCodeByDictType(DictTypeEnum.CITY_MERCHANT_TYPE.getDictType());
		List<DictCode> lists = new ArrayList<DictCode>();
		for(DictCode code : codes){
			if(city.getLevel()<Byte.parseByte(code.getDictCodeValue())){
				lists.add(code);
			}
		}
		response.setResult(lists);
		return JSON.toJSONString(response);
	}
	//刪除区域
	@ResponseBody
	@RequestMapping("/delete.html")
	public String deleteChannelArea(
			@RequestParam(value="cityId",required=false) Long cityId,
			@RequestParam(value="areaCode",required = false)Long areaCode){
		proxyAreaService.deleteArea(cityId, areaCode);
		Response response = new Response();
		response.setMessage("删除成功");
		return JSON.toJSONString(response);
	}
	
	@RequestMapping("/save.html")
	public String savePer(@ModelAttribute(value = "cityMerchant") SsssCityMerchant cityMerchant,
			HttpServletRequest request){
		String[] areaCodes = request.getParameterValues("areaCode");
		cityMerchant.setStatus(Constant.Status.NORMAL.getStatus());
		cityMerchant.setBondMoneyBalance(cityMerchant.getBondMoney());
		SysAdmin admin = WebUtil.getLoginUser(request);
		cityMerchant.setAdminId(admin.getId());
		if(cityMerchant.getId()==null){
			ssssCityMerchantService.insert(cityMerchant,request);
			if(areaCodes!=null){
				//添加区
				addAreaData(cityMerchant,areaCodes);
			}
		}else{
			ssssCityMerchantService.update(cityMerchant);
			//更新区
			updateAreaData(cityMerchant,areaCodes);
		}
		return "redirect:/crm/cityMerchant/cityMerchant.html";
	}
	
	public void addAreaData(SsssCityMerchant cityMerchant,String[] areaCodes){
		MerchantProxyArea area = new MerchantProxyArea();
		SsssCityMerchant ssssCityMerchant = ssssCityMerchantService.getByLinkTel(cityMerchant.getLinkTel());
		area.setMerchantLevel(ssssCityMerchant.getLevel());
		area.setMerchantId(ssssCityMerchant.getId());
		for(String code : areaCodes){
			StaffRegion region = staffRegionService.selectByPrimaryKey(Long.parseLong(code));
			area.setAreaCode(Long.parseLong(code));
			area.setAreaName(region.getName());
			area.setCreateDateTime(new Date());
			area.setUpdateDateTime(new Date());
			area.setAdminId(ssssCityMerchant.getAdminId());
			proxyAreaService.insertArea(area);
		}
	}
	public void updateAreaData(SsssCityMerchant city,String[] areaCodes){
		List<MerchantProxyArea> areas = proxyAreaService.queryByMerchantId(city.getId());
		if(areaCodes!=null){
			for(MerchantProxyArea area : areas){
				boolean flag = Arrays.asList(areaCodes).contains(area.getAreaCode()+"");
				//删除去掉的负责区
				if(!flag){
					proxyAreaService.deleteArea(city.getId(), area.getAreaCode());
				}
			}
			//新增或更新区
			for(String code : areaCodes){
				MerchantProxyArea pArea = proxyAreaService.selectByAreaCode(Long.parseLong(code));
				if(pArea!=null){
					pArea.setUpdateDateTime(new Date());
					proxyAreaService.updateArea(pArea);
				}else{
					MerchantProxyArea proxyArea = new MerchantProxyArea();
					proxyArea.setAdminId(city.getAdminId());
					proxyArea.setAreaCode(Long.parseLong(code));
					proxyArea.setCreateDateTime(new Date());
					proxyArea.setMerchantId(city.getId());
					proxyArea.setMerchantLevel(city.getLevel());
					proxyArea.setUpdateDateTime(new Date());
					proxyArea.setAreaName(staffRegionService.selectByPrimaryKey(Long.parseLong(code)).getName());
					proxyAreaService.insertArea(proxyArea);
				}
			}
		}else{
			proxyAreaService.deleteAreaByCityId(city.getId());
		}
	}
	
	/**
	 * 添加下级代理时，检查负责区域是否在上级代理负责区域内
	 * @param parentId
	 * @param areaCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkArea.html")
	public String checkArea(
			@RequestParam(value="parentId",required = false)Long parentId,
			@RequestParam(value="areaCode",required = false)Long areaCode){
		Response response = new Response();
		//没有上级代理，不用做地区判断，直接添加
		if(parentId==null){
			return JSON.toJSONString(response);
		}
		StaffRegion reg = staffRegionService.selectByPrimaryKey(areaCode);
		StaffRegion regio = staffRegionService.selectByPrimaryKey(reg.getParent());
		List<MerchantProxyArea> areas = proxyAreaService.queryByMerchantId(parentId);
		if(areas!=null){
			for(MerchantProxyArea area : areas){
				if(reg.getGrade()==2){//市
					if(areaCode.longValue()!=area.getAreaCode().longValue() && regio.getId().longValue()!=area.getAreaCode().longValue()){
						response.setSuccess(false);
						response.setMessage("选择地区不在上级代理负责地区内，请重新选择");
					}
				}
				if(reg.getGrade()==3){//区
					StaffRegion region = staffRegionService.selectByPrimaryKey(regio.getParent());
					if(areaCode.longValue()!=area.getAreaCode().longValue() 
							&& regio.getId().longValue()!=area.getAreaCode().longValue()
							&& region.getId().longValue()!=area.getAreaCode().longValue()){
						response.setSuccess(false);
						response.setMessage("选择地区不在上级代理负责地区内，请重新选择");
					}
				}
				
			}
		}
		return JSON.toJSONString(response);
	}
	/**
	 * 城市运营中心详情
	 */
	@RequestMapping("/orderDetails.html")
	public String orderDetails(
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,Model model,HttpServletRequest request){
		String parentId = request.getParameter("parentId");
		if(parentId!=null){
			SsssCityMerchant parentCity = ssssCityMerchantService.getById(Long.parseLong(parentId));
			model.addAttribute("parentCity", parentCity);
		}
		model.addAttribute("parentId", parentId);
		SsssCityMerchant city = ssssCityMerchantService.getById(id);
		Pager<MallOrder> pager = orderService.queryOrdersByUserIdAndStatus(city.getUserId(), null, pageNo, 10);
//		List<SsssOrder> dataList = ssssOrderService.selectByMerchantId(id,pageNo);
//		PageInfo<SsssOrder> pageInfo = new PageInfo<SsssOrder>(dataList);
//		model.addAttribute("pageInfo",pageInfo);
//		List<MallOrder> mallOrderList = new ArrayList<MallOrder>();
//		for(SsssOrder ssssOrder : dataList){
//			MallOrder mallOrder = orderService.getMallOrder(ssssOrder.getOrderNumber());
//			PayHistory payHistory = payService.getPayHistoryByPayNumber(mallOrder.getPayNumber());
//			mallOrder.setPayType(payHistory.getPayType());
//			mallOrder.setTotalPrice(ssssOrder.getOrderTotalMoney());
//			mallOrder.setDiscountPrice(ssssOrder.getProfit());
//			mallOrderList.add(mallOrder);
//		}
		model.addAttribute("pageInfo", pager);
		model.addAttribute("city", city);
		return "/crm/orderList_new";
	}
	/**
	 * 订单详情
	 */
	@RequestMapping("/orderDetail.html")
	public String orderDetail(String orderNumber,Model model){
		MallOrder order=orderService.getMallOrder(orderNumber);
		List<MallOrderItem> itemList = orderService.queryOrderItems(order.getUserId(), orderNumber);
		for(MallOrderItem mallOrderItem:itemList){
			mallOrderItem.setOrderItemExtend(
					orderService.getOrderItemExtend(order.getUserId(), mallOrderItem.getId()));
			Map<String, Object> map = new HashMap<String, Object>();
//			if(mallOrderItem.getIsCustomMade()!=null && mallOrderItem.getIsCustomMade().intValue()==1){
//				//MallCustomPic customPic = mallCustomPicService.getmCustomPic(mallCustomPicId);
//				//model.addAttribute("url", customPic.getUrl());
//			}else{
				map.put("productId", mallOrderItem.getProductId());
				map.put("formatId", mallOrderItem.getProductModelFormatId());
				List<MallProductPic> pics = productQueryService.queryProductPicByFormatId(map);
				mallOrderItem.setPics(pics);
//			}
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
		return "/crm/orderDetail_new";
	}
	
	/**
	 * 返利金额记录信息
	 * @param pageNo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/rebateRecord.html")
	public String rebateRecord(
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model,HttpServletRequest request) {
		Long merchantId = Long.parseLong(request.getParameter("merchantId"));
		List<MerchantRebateMoneyRecord> records = rebateRecordService.queryListByMerchantId(merchantId, pageNo);
		PageInfo<MerchantRebateMoneyRecord> pageInfo = new PageInfo<MerchantRebateMoneyRecord>(records);
		List<MerchantRebateMoneyRecord> list = new ArrayList<MerchantRebateMoneyRecord>();
		for(MerchantRebateMoneyRecord record : pageInfo.getList()){
			if(record.getDownMerchantId()!=null){
				SsssCityMerchant city = ssssCityMerchantService.getById(record.getDownMerchantId());
				record.setDownCityName(city.getCityMerchantName());
			}
			list.add(record);
		}
		List<DictCode> codes = dictCodeService.queryDictCodeByDictType(DictTypeEnum.BALANCE_CHANGE_STATUS.getDictType());
		model.addAttribute("codes", codes);
		model.addAttribute("merchantId",merchantId);
		model.addAttribute("records", list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("types", dictCodeService.queryDictCodeByDictType(DictTypeEnum.CITY_MERCHANT_TYPE.getDictType()));
		return "crm/rebate_Record_List";
	}
	/**
	 * 销售额记录信息
	 * @param pageNo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/saleRecordList.html")
	public String saleRecordList(
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model,HttpServletRequest request) {
		Long merchantId = Long.parseLong(request.getParameter("merchantId"));
		List<MerchantSaleMoneyRecord> records = saleMoneyRecordService.queryListByMerchantId(merchantId,pageNo);
		PageInfo<MerchantSaleMoneyRecord> pageInfo = new PageInfo<MerchantSaleMoneyRecord>(records);
		
		List<DictCode> codes = dictCodeService.queryDictCodeByDictType(DictTypeEnum.BALANCE_CHANGE_STATUS.getDictType());
		model.addAttribute("codes", codes);
		model.addAttribute("merchantId",merchantId);
		model.addAttribute("records", records);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("types", dictCodeService.queryDictCodeByDictType(DictTypeEnum.CITY_MERCHANT_TYPE.getDictType()));
		return "crm/saleMoneyRecordList";
	}
	
	/**
	 * 首批提货款记录信息
	 * @param pageNo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/firstGoodsMoneyRecordList.html")
	public String disCityMerchant(
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model,HttpServletRequest request) {
		Long merchantId = Long.parseLong(request.getParameter("merchantId"));
		List<MerchantFirstGoodsMoneyRecord> records = merchantFirstGoodsRecordService.queryListByMerchantId(merchantId,pageNo);
		PageInfo<MerchantFirstGoodsMoneyRecord> pageInfo = new PageInfo<MerchantFirstGoodsMoneyRecord>(records);
		List<MerchantFirstGoodsMoneyRecord> list = new ArrayList<MerchantFirstGoodsMoneyRecord>();
		for(MerchantFirstGoodsMoneyRecord record : pageInfo.getList()){
			if(record.getAdminId()!=null){
				SysAdmin admin = adminService.getAdminById(record.getAdminId());
				record.setUserName(admin.getUserName());
			}
			list.add(record);
		}
		List<DictCode> codes = dictCodeService.queryDictCodeByDictType(DictTypeEnum.BALANCE_CHANGE_STATUS.getDictType());
		model.addAttribute("codes", codes);
		model.addAttribute("merchantId",merchantId);
		model.addAttribute("records", list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("types", dictCodeService.queryDictCodeByDictType(DictTypeEnum.CITY_MERCHANT_TYPE.getDictType()));
		return "crm/firstGoodsMoneyRecordList";
	}
	/**
	 * 优惠券金额变更记录
	 * @param pageNo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/couponRecord.html")
	public String couponRecord(
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model,HttpServletRequest request) {
		Long merchantId = Long.parseLong(request.getParameter("merchantId"));
		List<MerchantCouponMoneyRecord> records = merchantCouponMoneyRecordService.queryListByMerchantId(merchantId,pageNo);
		PageInfo<MerchantCouponMoneyRecord> pageInfo = new PageInfo<MerchantCouponMoneyRecord>(records);
		List<MerchantCouponMoneyRecord> list = new ArrayList<MerchantCouponMoneyRecord>();
		for(MerchantCouponMoneyRecord record : pageInfo.getList()){
			if(record.getAdminId()!=null){
				SysAdmin admin = adminService.getAdminById(record.getAdminId());
				record.setUserName(admin.getUserName());
			}
			list.add(record);
		}
		List<DictCode> codes = dictCodeService.queryDictCodeByDictType(DictTypeEnum.BALANCE_CHANGE_STATUS.getDictType());
		model.addAttribute("codes", codes);
		model.addAttribute("merchantId",merchantId);
		model.addAttribute("records", list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("types", dictCodeService.queryDictCodeByDictType(DictTypeEnum.CITY_MERCHANT_TYPE.getDictType()));
		return "crm/coupon_Record_List";
	}
	/**
	 * 保证金变更记录
	 * @param pageNo
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/bondRecord.html")
	public String bondRecord(
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model,HttpServletRequest request) {
		Long merchantId = Long.parseLong(request.getParameter("merchantId"));
		List<MerchantBondMoneyRecord> records = merchantBondMoneyRecordService.queryListByMerchantId(merchantId,pageNo);
		PageInfo<MerchantBondMoneyRecord> pageInfo = new PageInfo<MerchantBondMoneyRecord>(records);
		List<MerchantBondMoneyRecord> list = new ArrayList<MerchantBondMoneyRecord>();
		for(MerchantBondMoneyRecord record : pageInfo.getList()){
			if(record.getAdminId()!=null){
				SysAdmin admin = adminService.getAdminById(record.getAdminId());
				record.setUserName(admin.getUserName());
			}
			list.add(record);
		}
		List<DictCode> codes = dictCodeService.queryDictCodeByDictType(DictTypeEnum.BALANCE_CHANGE_STATUS.getDictType());
		model.addAttribute("codes", codes);
		model.addAttribute("merchantId",merchantId);
		model.addAttribute("records", list);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("types", dictCodeService.queryDictCodeByDictType(DictTypeEnum.CITY_MERCHANT_TYPE.getDictType()));
		return "crm/bond_Record_List";
	}
	/**
	 * 给代理商添加代理产品和产品的利润
	 * @param productIds
	 * @param productProfitIds
	 * @param merchantId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addProduct")
	public String addProduct(
			@RequestParam(value="productIds[]",required=false) Long[] productIds,
			@RequestParam(value="productProfitIds[]",required=false) Long[] productProfitIds,
			@RequestParam("merchantId") Long merchantId){
		Response response = new Response();
		merchantProductService.deleteByMerchantId(merchantId);
		merchantProductProfitService.deleteByMerchantId(merchantId);
		if(productIds!=null && productIds.length>0){
			MerchantProduct product = new MerchantProduct();
			product.setMerchantId(merchantId);
			for(int i=0;i<productIds.length;i++){
				product.setProductId(productIds[i]);
				merchantProductService.insert(product);
			}
		}
		if(productProfitIds!=null && productProfitIds.length>0){
			MerchantProductProfit profit = new MerchantProductProfit();
			profit.setMerchantId(merchantId);
			for(int i=0;i<productProfitIds.length;i++){
				profit.setProductId(productProfitIds[i]);
				merchantProductProfitService.insert(profit);
			}
		}
		return JSON.toJSONString(response);
	}
	
	
	/**
	 * 根据代理商Id查询所代理的产品
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getProduct")
	public String getProduct(
			@RequestParam("merchantId") Long merchantId){
		Response response = new Response();
		List<MerchantProduct> productList = merchantProductService.queryList(merchantId);
		List<MallProduct> list = new ArrayList<MallProduct>();
		if(productList.size()>0){
			for(MerchantProduct product : productList){
				MallProduct mallProduct = productQueryService.getProduct(product.getProductId());
				list.add(mallProduct);
			}
			response.setResult(list);
		}
		
		return JSON.toJSONString(response);
	}
	/**
	 * 根据代理商Id查询有利润的产品
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getProductProfit")
	public String getProductProfit(
			@RequestParam("merchantId") Long merchantId){
		Response response = new Response();
		List<MerchantProductProfit> profitList = merchantProductProfitService.queryList(merchantId);
		List<MallProduct> list = new ArrayList<MallProduct>();
		if(profitList.size()>0){
			for(MerchantProductProfit profit : profitList){
				MallProduct mallProduct = productQueryService.getProduct(profit.getProductId());
				list.add(mallProduct);
			}
			response.setResult(list);
		}
		
		return JSON.toJSONString(response);
	}
	/**
	 * 判断当前新增的代理商是否合法
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
				SsssCityMerchant ssssInfo = ssssCityMerchantService.getByUserId(user.getId());
				if(ssssInfo!=null && ssssInfo.getId().intValue()>0){
					//说明已经存在该代理商的人员不允许添加了
					code = 1;
					msg = "在系统中已存在该手机号码用户为代理商的信息不允许重复添加。";
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
	 * 给代理商设置业务员
	 * 1个代理商可能有多个业务员
	 * 多对多关系
	 * @param merchantId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveUserMerchant")
	public String saveUserMerchant(@RequestParam(value="merchantId",required= false)Long merchantId,
			@RequestParam(value="ids[]",required=false) String[] ids,
			HttpServletRequest request){
		Response response = new Response();
		//首先删除代理商现有业务员
		userMerchantService.deleteByMerchantId(merchantId);
		DdUserMerchant userMerchant = new DdUserMerchant();
		userMerchant.setMerchantId(merchantId);
		//获取分配业务员的Id号
		if(ids!=null && ids.length>0){
			for(int i=0;i<ids.length;i++){
				userMerchant.setUserId(ids[i]);
				userMerchantService.insert(userMerchant);
			}
		}
		return JSON.toJSONString(response);
	}
	
	/**
	 * 根据部门Id查询员工列表
	 * @param merchantId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserList")
	public String getOrgUser(@RequestParam(value="departmentId",required= false)String departmentId,HttpServletRequest request){
		Response response = new Response();
		List<DdUser> userList = ddUserService.queryDdUsersByDepartmentId(departmentId);
		if(userList.size()>0){
			response.setResult(userList);
		}
		return JSON.toJSONString(response);
	}
	
	/**
	 * 查询代理商对应的业务员，显示用
	 * @param merchantId
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getUserMerchants")
	public String getUserMerchants(@RequestParam(value="merchantId",required= false)Long merchantId,HttpServletRequest request){
		Response response = new Response();
		//根据代理商ID查询对应的业务员ID
		List<DdUserMerchant> list = userMerchantService.queryByMerchantId(merchantId);
		List<DdUser> userLists = new ArrayList<DdUser>();
		//根据员工ID查询员工信息
		for(DdUserMerchant userMerchant : list){
			DdUser user = ddUserService.getByUserId(userMerchant.getUserId());
			userLists.add(user);
		}
		response.setResult(userLists);
		return JSON.toJSONString(response);
	}
	
	/**
	 * 追加首批提货款
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addFirstMoney.html")
	public String addFirstMoney(@RequestParam(value="money",required = false)Float money,
			@RequestParam(value="pwd",required= false)String pwd,
			@RequestParam(value="remark",required= false)String remark,
			@RequestParam(value="merchantId",required= false)Long merchantId,
			@RequestParam(value="level",required= false)Byte level,HttpServletRequest request){
		Response response = new Response();
		if(StringUtils.isBlank(remark)){
			response.setMessage("备注不能为空");
			response.setSuccess(false);
			return JSON.toJSONString(response);
		}
		if(StringUtils.isBlank(pwd)){
			response.setMessage("密码不能为空");
			response.setSuccess(false);
			return JSON.toJSONString(response);
		}
		SysAdmin admin = WebUtil.getLoginUser(request);
		if(!admin.getUserPwd().equals(Md5Util.encode(pwd))){
			response.setSuccess(false);
			response.setMessage("密码错误");
			return JSON.toJSONString(response);
		}
		DecimalFormat df = new DecimalFormat("#.##");
		SsssCityMerchant cityMerchant = ssssCityMerchantService.getById(merchantId);
		//首批提货款记录
		MerchantFirstGoodsMoneyRecord record = new MerchantFirstGoodsMoneyRecord();
		record.setAdminId(admin.getId());
		record.setMerchantId(merchantId);
		record.setCreateDateTime(new Date());
		record.setMoney(money);
		record.setRemark(remark);
		record.setMerchantLevel(level);
		record.setStatus(BalanceChangeStatusEnum.ADDBANLANCE.getCode());
		record.setType(BalanceChangeStatusEnum.ADDBANLANCE.getCode());
		//优惠券记录增加
		MerchantCouponMoneyRecord couponRecord = new MerchantCouponMoneyRecord();
		couponRecord.setMerchantId(cityMerchant.getId());
		couponRecord.setAdminId(admin.getId());
		couponRecord.setCreateDateTime(new Date());
		couponRecord.setRemark("提货款追加解冻");
		couponRecord.setMerchantLevel(cityMerchant.getLevel());
		couponRecord.setStatus(BalanceChangeStatusEnum.ADDBANLANCE.getCode());
		if(money!=null && money>0){
			record.setFirstGoodsMoneyBalanceBefore(cityMerchant.getFirstGoodsMoneyBalance());
			cityMerchant.setFirstGoodsMoney(
					(cityMerchant.getFirstGoodsMoney()==null?0:cityMerchant.getFirstGoodsMoney())+money);
			cityMerchant.setFirstGoodsMoneyBalance(
					(cityMerchant.getFirstGoodsMoneyBalance()==null?0:cityMerchant.getFirstGoodsMoneyBalance())+money);
			//本次追加提货款解冻的优惠券
			Float unfreezeCoupon = Float.parseFloat(df.format(money*Constant.CouponFactor.UNFREEZE.getStatus()));
			//解冻金额小于冻结金额
			if((cityMerchant.getFreezeCoupon()==null?0:cityMerchant.getFreezeCoupon())>unfreezeCoupon){
				//本次追加扣除解冻之后的冻结优惠券
				Float freezeCoupon = cityMerchant.getFreezeCoupon()-unfreezeCoupon;
				couponRecord.setAddUnfreezeCouponMoney(unfreezeCoupon);
				couponRecord.setBeforeFreezeCouponMoney(cityMerchant.getFreezeCoupon());
				couponRecord.setAfterFreezeCouponMoney(freezeCoupon);
				couponRecord.setBeforeUnfreezeCouponMoney(cityMerchant.getUnfreezeCoupon());
				couponRecord.setAfterUnfreezeCouponMoney(cityMerchant.getUnfreezeCoupon()+unfreezeCoupon);
				cityMerchant.setFreezeCoupon(freezeCoupon);
				cityMerchant.setUnfreezeCoupon(cityMerchant.getUnfreezeCoupon()+unfreezeCoupon);
			}else{
				couponRecord.setAddUnfreezeCouponMoney(cityMerchant.getFreezeCoupon());
				couponRecord.setBeforeUnfreezeCouponMoney(cityMerchant.getUnfreezeCoupon());
				couponRecord.setAfterUnfreezeCouponMoney(
						(cityMerchant.getUnfreezeCoupon()==null?0:cityMerchant.getUnfreezeCoupon())+(cityMerchant.getFreezeCoupon()==null?0:cityMerchant.getFreezeCoupon()));
				couponRecord.setBeforeFreezeCouponMoney(cityMerchant.getFreezeCoupon());
				couponRecord.setAfterFreezeCouponMoney(0f);
				cityMerchant.setUnfreezeCoupon(
						(cityMerchant.getUnfreezeCoupon()==null?0:cityMerchant.getUnfreezeCoupon())+(cityMerchant.getFreezeCoupon()==null?0:cityMerchant.getFreezeCoupon()));
				cityMerchant.setFreezeCoupon(0f);
			}
			record.setFirstGoodsMoneyBalanceAfter(
					cityMerchant.getFirstGoodsMoneyBalance());
			//记录修改信息
			merchantFirstGoodsRecordService.insertSelective(record);
			if(couponRecord.getBeforeFreezeCouponMoney()!=0){
				merchantCouponMoneyRecordService.insert(couponRecord);
			}
			ssssCityMerchantService.updateCityMerchant(cityMerchant);
			response.setMessage("追加提货款成功");
		}
		return JSON.toJSONString(response);
	}
	/**
	 * 扣减首批提货款
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/minusFirstMoney.html")
	public String minusFirstMoney(@RequestParam(value="money",required = false)Float money,
			@RequestParam(value="pwd",required= false)String pwd,
			@RequestParam(value="remark",required= false)String remark,
			@RequestParam(value="merchantId",required= false)Long merchantId,
			@RequestParam(value="level",required= false)Byte level,HttpServletRequest request){
		Response response = new Response();
		SsssCityMerchant cityMerchant = ssssCityMerchantService.getById(merchantId);
		if(cityMerchant.getFirstGoodsMoneyBalance()<money || cityMerchant.getFirstGoodsMoney()<money){
			response.setMessage("扣减金额不能大于首批提货款余额");
			response.setSuccess(false);
			return JSON.toJSONString(response);
		}
		if(StringUtils.isBlank(remark)){
			response.setMessage("备注不能为空");
			response.setSuccess(false);
			return JSON.toJSONString(response);
		}
		if(StringUtils.isBlank(pwd)){
			response.setMessage("密码不能为空");
			response.setSuccess(false);
			return JSON.toJSONString(response);
		}
		SysAdmin admin = WebUtil.getLoginUser(request);
		if(!admin.getUserPwd().equals(Md5Util.encode(pwd))){
			response.setSuccess(false);
			response.setMessage("密码错误");
			return JSON.toJSONString(response);
		}
		MerchantFirstGoodsMoneyRecord record = new MerchantFirstGoodsMoneyRecord();
		record.setAdminId(admin.getId());
		record.setMerchantId(merchantId);
		record.setCreateDateTime(new Date());
		record.setMoney(-money);
		record.setRemark(remark);
		record.setMerchantLevel(level);
		record.setFirstGoodsMoneyBalanceBefore(cityMerchant.getFirstGoodsMoneyBalance());
		record.setStatus(BalanceChangeStatusEnum.MINUSBALANCE.getCode());
		record.setType((byte)1);
		if(money!=null && money>0){
			cityMerchant.setFirstGoodsMoney(
					(cityMerchant.getFirstGoodsMoney()==null?0:cityMerchant.getFirstGoodsMoney())-money);
			cityMerchant.setFirstGoodsMoneyBalance(
					(cityMerchant.getFirstGoodsMoneyBalance()==null?0:cityMerchant.getFirstGoodsMoneyBalance())-money);
			record.setFirstGoodsMoneyBalanceAfter(
					cityMerchant.getFirstGoodsMoneyBalance());
			//记录修改信息
			merchantFirstGoodsRecordService.insertSelective(record);
			ssssCityMerchantService.updateCityMerchant(cityMerchant);
			response.setMessage("扣减提货款成功");
		}
		return JSON.toJSONString(response);
	}
	
	/**
	 * 追加优惠券
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addCoupon.html")
	public String addCoupon(@RequestParam(value="money",required = false)Float money,
			@RequestParam(value="pwd",required= false)String pwd,
			@RequestParam(value="remark",required= false)String remark,
			@RequestParam(value="merchantId",required= false)Long merchantId,
			@RequestParam(value="level",required= false)Byte level,HttpServletRequest request){
		Response response = new Response();
		if(StringUtils.isBlank(remark)){
			response.setMessage("备注不能为空");
			response.setSuccess(false);
			return JSON.toJSONString(response);
		}
		if(StringUtils.isBlank(pwd)){
			response.setMessage("密码不能为空");
			response.setSuccess(false);
			return JSON.toJSONString(response);
		}
		SysAdmin admin = WebUtil.getLoginUser(request);
		if(!admin.getUserPwd().equals(Md5Util.encode(pwd))){
			response.setSuccess(false);
			response.setMessage("密码错误");
			return JSON.toJSONString(response);
		}
		MerchantCouponMoneyRecord record = new MerchantCouponMoneyRecord();
		record.setAdminId(admin.getId());
		record.setMerchantId(merchantId);
		record.setCreateDateTime(new Date());
		record.setMerchantLevel(level);
		record.setOrderNumber(null);
		record.setAddFreezeCouponMoney(money);
		record.setAddUnfreezeCouponMoney(0f);
		record.setUseCouponMoney(0f);
		record.setRemark(remark);
		record.setStatus(BalanceChangeStatusEnum.ADDBANLANCE.getCode());
		if(money!=null && money>0){
			SsssCityMerchant cityMerchant = ssssCityMerchantService.getById(merchantId);
			record.setBeforeUnfreezeCouponMoney(cityMerchant.getUnfreezeCoupon());
			record.setAfterUnfreezeCouponMoney(cityMerchant.getUnfreezeCoupon());
			record.setBeforeFreezeCouponMoney(cityMerchant.getFreezeCoupon());
			record.setAfterFreezeCouponMoney(cityMerchant.getFreezeCoupon()+money);
			
			cityMerchant.setFreezeCoupon(cityMerchant.getFreezeCoupon()+money);
			
			//记录修改信息
			merchantCouponMoneyRecordService.insert(record);
			ssssCityMerchantService.updateCityMerchant(cityMerchant);
			response.setMessage("追加优惠券成功");
		}
		return JSON.toJSONString(response);
	}
	/**
	 * 扣减优惠券
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/minusCoupon.html")
	public String minusCoupon(@RequestParam(value="money",required = false)Float money,
			@RequestParam(value="pwd",required= false)String pwd,
			@RequestParam(value="remark",required= false)String remark,
			@RequestParam(value="merchantId",required= false)Long merchantId,
			@RequestParam(value="level",required= false)Byte level,HttpServletRequest request){
		Response response = new Response();
		SsssCityMerchant cityMerchant = ssssCityMerchantService.getById(merchantId);
		if(cityMerchant.getFreezeCoupon()<money){
			response.setMessage("扣减金额不能大于冻结总金额");
			response.setSuccess(false);
			return JSON.toJSONString(response);
		}
		if(StringUtils.isBlank(remark)){
			response.setMessage("备注不能为空");
			response.setSuccess(false);
			return JSON.toJSONString(response);
		}
		if(StringUtils.isBlank(pwd)){
			response.setMessage("密码不能为空");
			response.setSuccess(false);
			return JSON.toJSONString(response);
		}
		SysAdmin admin = WebUtil.getLoginUser(request);
		if(!admin.getUserPwd().equals(Md5Util.encode(pwd))){
			response.setSuccess(false);
			response.setMessage("密码错误");
			return JSON.toJSONString(response);
		}
		MerchantCouponMoneyRecord record = new MerchantCouponMoneyRecord();
		record.setAdminId(admin.getId());
		record.setMerchantId(merchantId);
		record.setCreateDateTime(new Date());
		record.setMerchantLevel(level);
		record.setOrderNumber(null);
		record.setAddFreezeCouponMoney(-money);
		record.setAddUnfreezeCouponMoney(0f);
		record.setUseCouponMoney(0f);
		record.setRemark(remark);
		record.setStatus(BalanceChangeStatusEnum.MINUSBALANCE.getCode());
		if(money!=null && money>0){
			record.setBeforeUnfreezeCouponMoney(cityMerchant.getUnfreezeCoupon());
			record.setAfterUnfreezeCouponMoney(cityMerchant.getUnfreezeCoupon());
			record.setBeforeFreezeCouponMoney(cityMerchant.getFreezeCoupon());
			record.setAfterFreezeCouponMoney(cityMerchant.getFreezeCoupon()-money);
			
			cityMerchant.setFreezeCoupon(cityMerchant.getFreezeCoupon()-money);
			//记录修改信息
			merchantCouponMoneyRecordService.insert(record);
			ssssCityMerchantService.updateCityMerchant(cityMerchant);
			response.setMessage("扣除优惠券成功");
		}
		return JSON.toJSONString(response);
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
	
//	@RequestMapping("/setModelFormatMonyelist.html")
//	public String setModelFormatMoneylist(
//			@RequestParam(value = "merchantid", required = false) Long ssssId,//代理商或4s店的ID
//			@RequestParam(value="pageNo",defaultValue="1") Integer pageNo,
//			@RequestParam(value="keywords",required=false)String keywords,
//			@RequestParam(value="startTime",required=false)Date startTime,
//			@RequestParam(value="endTime",required=false)Date endTime,
//			@RequestParam(value="error",required=false)String error, //错误提示码
//			HttpServletRequest request,ModelMap model){
//		if(org.apache.commons.lang.StringUtils.isNotBlank(keywords)){
//			model.addAttribute("keywords",keywords);
//		}
//		if(startTime != null){
//			model.addAttribute("startTime",DateUtil.format(startTime, "yyyy-MM-dd"));
//		}
//		if(endTime != null){
//			model.addAttribute("endTime",DateUtil.format(endTime, "yyyy-MM-dd"));
//		}
//		if(pageNo == null){
//			pageNo = 1;
//		}
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("keywords", keywords);
//		map.put("startTime", startTime);
//		map.put("endTime", endTime);
//		map.put("pageNo", pageNo);
//		List<MallBasicCategoryModel> list = categoryQueryService.queryAllCategoryModel(map);
//		PageInfo<MallBasicCategoryModel> pageInfo=new PageInfo<MallBasicCategoryModel>(list);
//		//设置查询设置过的价格值
//		list = setPrice(list,ssssId);
//		model.addAttribute("pageInfo",pageInfo);
//		model.addAttribute("list",list);
//		model.addAttribute("flag", 1);
//		model.addAttribute("merchantid", ssssId);
//		model.addAttribute("error", error);
//		return "crm/modelFormatMoneyMerchantList";
//	}
//	private List<MallBasicCategoryModel> setPrice(List<MallBasicCategoryModel> list,Long ssssId){
//		List<MallBasicCategoryModel> resList = new ArrayList<MallBasicCategoryModel>();
//		for (MallBasicCategoryModel mallBasicCategoryModel : list) {
//			List<MallBasicCategoryModelFormat> listTemp = mallBasicCategoryModel.getFormats();
//			List<MallBasicCategoryModelFormat> listTempRes = new ArrayList<MallBasicCategoryModelFormat>();
//			for (MallBasicCategoryModelFormat mallBasicCategoryModelFormat : listTemp) {
//				//查询价格
//				SsssMerchantFormatPrice ssssMerchantFormatPrice = ssssMerchantFormatPriceService.getSsssMerchantFormatPriceByFormatIdAndMerchantId(mallBasicCategoryModelFormat.getId(), ssssId);
//				if(ssssMerchantFormatPrice != null){
//					mallBasicCategoryModelFormat.setCrmMoney(ssssMerchantFormatPrice.getPrice());
//					mallBasicCategoryModelFormat.setPriceId(ssssMerchantFormatPrice.getId());
//				}else{
//					mallBasicCategoryModelFormat.setCrmMoney(0f);
//					mallBasicCategoryModelFormat.setPriceId(0l);
//				}
//				listTempRes.add(mallBasicCategoryModelFormat);
//			}
//			mallBasicCategoryModel.setFormats(listTempRes);
//			resList.add(mallBasicCategoryModel);
//		}
//		return resList;
//	}
	
//	@RequestMapping("/savePrice.html")
//	public String savePrice(@ModelAttribute(value = "info") SsssMerchantFormatPrice ssssMerchantFormatPrice,
//			HttpServletRequest request){
//		//检查一下设置的价格是否合理
//		//获取产品价格
//		String error = "0";
//		MallBasicCategoryModelFormat basicCategoryModelFormat = categoryQueryService.getCategoryModelFormat(ssssMerchantFormatPrice.getFormatId());
//		if(basicCategoryModelFormat != null){
//			float price = ssssMerchantFormatPrice.getPrice();//输入的价格
//			if(price<basicCategoryModelFormat.getPrice()){
//				//判断设置的价格是否大于上级所设置的价格
//				SsssCityMerchant merchant = ssssCityMerchantService.get(ssssMerchantFormatPrice.getMerchantId());
//				Long cityMerchantId = merchant.getParentCityMerchantId();
//				if(cityMerchantId.intValue() > 0 ){//说明是设置的市代的价格
//					SsssMerchantFormatPrice parentssssMerchantFormatPrice = ssssMerchantFormatPriceService.getSsssMerchantFormatPriceByFormatIdAndMerchantId(ssssMerchantFormatPrice.getFormatId(), cityMerchantId);
//					if(parentssssMerchantFormatPrice != null){
//						if(price>parentssssMerchantFormatPrice.getPrice()){//输入的价格合法
//							error = saveFormatPrice(ssssMerchantFormatPrice,request);
//						}else{
//							error = "4";//设置价格过小，要设置超过市代价格
//						}
//					}else{
//						error = "3";//省代未设置价格，需要先设置市代价格
//					}
//				}else{//省代直接保存
//					error = saveFormatPrice(ssssMerchantFormatPrice,request);
//				}
//			}else{
//				error = "2";//设置价格过大超过了官网设置的价格
//			}
//		}else{
//			error = "1";//产品未找到
//		}
//		return "redirect:/crm/cityMerchant/setModelFormatMonyelist.html?error="+error+"&merchantid="+ssssMerchantFormatPrice.getMerchantId();
//	}
//	private String saveFormatPrice(SsssMerchantFormatPrice ssssMerchantFormatPrice,HttpServletRequest request){
//		String error="0";
//		if(ssssMerchantFormatPrice.getId()==null || ssssMerchantFormatPrice.getId().intValue() == 0){
//			SysAdmin admin = WebUtil.getLoginUser(request);
//			ssssMerchantFormatPrice.setCreatorId(admin.getId());
//			ssssMerchantFormatPrice.setCreateDateTime(new Date());
//			ssssMerchantFormatPrice.setInvalid(InvalidEnum.FALSE.getInvalidValue());
//			ssssMerchantFormatPrice.setStatus(Constant.Status.NORMAL.getStatus());
//			ssssMerchantFormatPriceService.insert(ssssMerchantFormatPrice);
//		}else{
//			//修改时需要判断下级的设置价格是否也符合更改规则
//			float price = ssssMerchantFormatPrice.getPrice();
//			//比较下级4S店的价格是否合规
//			Long merchantId = ssssMerchantFormatPrice.getMerchantId();//代理商的ID
//			List<SsssCityMerchant> mList = ssssCityMerchantService.getListByPid(merchantId);
//			if(mList != null){
//				for (SsssCityMerchant ssssCityMerchant : mList) {
//					SsssMerchantFormatPrice merchantFormatPrice = ssssMerchantFormatPriceService.getSsssMerchantFormatPriceByFormatIdAndMerchantId(ssssMerchantFormatPrice.getFormatId(), ssssCityMerchant.getId());
//					if(merchantFormatPrice == null){//说明下级未设置过
//						continue;
//					}else{
//						if(merchantFormatPrice.getPrice()<price){//下级的价格，设置时
//							error = "5";//下级价格
//							break;
//						}
//					}
//				}
//			}
//			//比较下级代理商的价格是否合规
//			if(error.equals("0")){
//				List<SsssInfo> infoList = ssssInfoService.getListByPid(merchantId);
//				if(infoList != null){
//					for (SsssInfo ssssinfo : infoList) {
//						SsssMerchantFormatPrice merchantFormatPrice = ssssMerchantFormatPriceService.getSsssFormatPriceByFormatIdAndssssId(ssssMerchantFormatPrice.getFormatId(), ssssinfo.getId());
//						if(merchantFormatPrice == null){//说明下级未设置过
//							continue;
//						}else{
//							if(merchantFormatPrice.getPrice()<price){//下级的价格，设置时
//								error = "5";//下级价格
//								break;
//							}
//						}
//					}
//				}
//			}
//			if(error.equals("0"))
//				ssssMerchantFormatPriceService.update(ssssMerchantFormatPrice);
//		}
//		return error;
//	}
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}
