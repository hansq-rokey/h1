package com.ibaixiong.manage.web.order;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.entity.User;
import com.ibaixiong.manage.service.base.DictCodeService;
import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.service.bbs.UserService;
import com.ibaixiong.manage.service.mall.MallOrderService;
import com.ibaixiong.manage.util.DictTypeEnum;
import com.ibaixiong.manage.util.TbOrderTypeEnum;
import com.ibaixiong.manage.web.util.DateEditor;
import com.ibaixiong.manage.web.util.Response;
import com.ibaixiong.manage.web.util.WebUtil;
import com.papabear.commons.entity.Pager;
import com.papabear.commons.entity.enumentity.Constant.Status;
import com.papabear.order.api.OrderService;
import com.papabear.order.entity.MallOrder;
import com.papabear.order.entity.MallOrderCashRecord;
import com.papabear.order.entity.MallOrderHistory;
import com.papabear.order.entity.MallOrderItem;
import com.papabear.order.entity.MallOrderRevicerInformation;
import com.papabear.order.entity.OrderConstant.OrderStatus;
import com.papabear.pay.api.PayService;

@Controller
@RequestMapping("/noEffectOrder")
public class NoEffectOrderController {
	@Resource
	private MallOrderService mallOrderService;
	@Resource
	private OrderService orderService;
	@Resource
	private DictCodeService dictCodeService;
	@Resource
	private UserService userService;
	@Resource
	private PayService payService;
	@RequestMapping("/list.html")
	public String searchOrderList(@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,@RequestParam(value="start",required=false) Date startTime,
			@RequestParam(value="end",required=false) Date endTime,ModelMap model,HttpServletRequest request){
		int pageSize=10;
		Byte status=null;
		String keywords=request.getParameter("keywords");
		String statusStr=request.getParameter("status");
		if(StringUtils.isNotBlank(statusStr)){
			status=Byte.valueOf(statusStr);
		}
		if(pageNo == null){ 
			pageNo = 1;
		}
		Pager<MallOrder> pager=orderService.queryOrdersBysearch(keywords, startTime, endTime, status, pageNo, pageSize,(byte)1);
		
		
//		List<MallOrder>list=mallOrderService.queryByParamters(keywords, startTime, endTime, status,pageNo,pageSize);
		
//		PageInfo<MallOrder> pageInfo=new PageInfo<MallOrder>(list,pageSize);
		model.addAttribute("pageInfo", pager);
		for(MallOrder order:pager.getList()){
			MallOrderRevicerInformation information = orderService.getRevicerByUserIdAndOrderNumber(order.getUserId(), order.getOrderNumber());
			order.setInformation(information);
			order.setOrderItems(orderService.queryOrderItems(null, order.getOrderNumber()));
			User user=userService.getUser(order.getUserId());
			if(null==user)continue;
			order.setBxNum(user.getBxNum());
			order.setPhone(user.getPhone());
			order.setEmail(user.getEmail());
		}
		model.addAttribute("orderList", pager.getList());
		model.addAttribute("status", status);
		if(startTime != null){
			model.addAttribute("start",DateUtil.format(startTime, "yyyy-MM-dd"));
		}
		if(endTime != null){
			model.addAttribute("end",DateUtil.format(endTime, "yyyy-MM-dd"));
			endTime=DateUtil.getDateEndTime(endTime);
		}
		model.addAttribute("keywords", keywords);
		List<DictCode> dictCodes=dictCodeService.queryDictCodeList(DictTypeEnum.ORDER_STATUS,null,null);
		model.addAttribute("orderStatusList", dictCodes);	
		return "order/no_effect_order_list";
	}
	
	@RequestMapping("/detail.html")
	public String orderDetail(
			@RequestParam(value="orderNumber",required=false)String orderNumber,
			ModelMap model,HttpServletRequest request){
//		MallOrder order = mallOrderService.getByOrderNumber(orderNumber);
		MallOrder order = orderService.getMallOrder(orderNumber);
		//查询订单详细信息
//		order.setOrderItems(mallOrderItemService.getListByOrderNumber(orderNumber));
		order.setOrderItems(orderService.queryOrderItems(null, orderNumber));
		MallOrderRevicerInformation reciver = orderService.getRevicerByUserIdAndOrderNumber(order.getUserId(), order.getOrderNumber());
		List<MallOrderHistory> listHistory = orderService.queryHistoryByOrderNumber(orderNumber);
		List<DictCode> dictCodes=null;
		if(order.getIsCustomMade() != null && order.getIsCustomMade().intValue()==1){
			dictCodes=dictCodeService.queryDictCodeList(DictTypeEnum.ORDER_STATUS);
		}else{
			dictCodes=dictCodeService.queryDictCodeList(DictTypeEnum.ORDER_STATUS,(byte)0);
		}
		model.addAttribute("orderStatusList", dictCodes);						
		for(DictCode code:dictCodes){
			for (MallOrderHistory history : listHistory) {
				if(code.getDictCodeValue().trim().equals(history.getStatus().toString())){
					code.setFlow(true);
					code.setOrderHistory(history);
				}
			}
		}
		model.addAttribute("order", order);
		model.addAttribute("reciver", reciver);
		return "order/no_effect_order_detail";
	}
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	/**
	 * 根据订单号查询该订单下的商品
	 * @param orderNumber
	 * @param price
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/toUpdate")
	public String updateOrderItem(@RequestParam("orderNumber")String orderNumber,
			HttpServletRequest request){
		Response response = new Response();
		try {
			List<MallOrderItem> orderItems = orderService.queryOrderItems(orderNumber);
			response.setResult(orderItems);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(response);
	}
	/**
	 * 修改订单总金额
	 * @param orderNumber
	 * @param price
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updatePrice")
	public String updateOrderStatus(@RequestParam("orderNumber")String orderNumber,
			@RequestParam("price") float price,HttpServletRequest request){
		String msg = "";
		int code = 0;
		SysAdmin admin = WebUtil.getLoginUser(request);
		try {
			//更新订单中商品的价格
			String dName = request.getParameter("dName");
			String[] dArray = dName.split(",");
			for(String combine : dArray){
				if(!StringUtils.isEmpty(combine)){
					String[] str = combine.split("-");
					MallOrderItem item = orderService.getMallOrderItem(Long.parseLong(str[0]));
					item.setTotalPrice(Float.parseFloat(str[1]));
					item.setUpdateTime(new Date());
					orderService.updateOrderItem(item);
				}
			}
			MallOrder order = orderService.getMallOrder(orderNumber);
			MallOrderCashRecord orderRecord = new MallOrderCashRecord();
			orderRecord.setAdminId(admin.getId());
			orderRecord.setUserId(order.getUserId());
			orderRecord.setOrderNumber(orderNumber);
			orderRecord.setBeforeMoney(order.getShouldPayMoney());
			orderRecord.setAfterMoney(price);
			orderRecord.setCreateDateTime(new Date());
			orderRecord.setType(order.getType());
			String remark = "";
			if(order.getType()==1){
				remark = TbOrderTypeEnum.CORDER.getValue();
			}
			if(order.getType()==2){
				remark = TbOrderTypeEnum.BORDER.getValue();
			}
			if(order.getType()==3){
				remark = TbOrderTypeEnum.TBORDER.getValue();
			}
			if(order.getType()==4){
				remark = TbOrderTypeEnum.IBAIXIONGORDER.getValue();
			}
			orderRecord.setRemark(remark);
//			mallOrderService.updateOrderDiscountPrice(orderNumber, price);			
			int flag=orderService.updateOrderByPrice(price, orderNumber);//TODO
			if(flag>0){
				orderService.addOrderCashRecord(orderRecord);
				payService.updateOrderPayRealByOrderNumber(price, orderNumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(ResponseResult.result(code, msg));
	}
}
