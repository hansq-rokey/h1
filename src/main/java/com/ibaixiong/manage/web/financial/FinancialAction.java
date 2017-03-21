package com.ibaixiong.manage.web.financial;

import java.util.ArrayList;
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

import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.core.web.DateEditor;
import com.ibaixiong.entity.User;
import com.ibaixiong.manage.exception.OrderNullException;
import com.ibaixiong.manage.service.base.DictCodeService;
import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.service.bbs.UserService;
import com.ibaixiong.manage.util.DictTypeEnum;
import com.papabear.commons.entity.Pager;
import com.papabear.order.api.OrderService;
import com.papabear.order.entity.MallOrder;
import com.papabear.order.entity.MallOrderHistory;
import com.papabear.order.entity.MallOrderRevicerInformation;
import com.papabear.pay.api.PayService;
import com.papabear.pay.entity.OrderPay;

/**
 * 财务统计功能
 * 
 * @author zhaolei
 *
 */
@Controller
@RequestMapping("/financial")
public class FinancialAction {
	@Resource
	private PayService payService;
	@Resource
	private DictCodeService dictCodeService;
	@Resource
	private OrderService orderService;
	@Resource
	private UserService userService;

	/**
	 * 列表查询
	 * 
	 * @author zhaolei
	 * @date 2015年11月9日
	 * @param queryType
	 * @param queryStr
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryList.html")
	public String queryList(@RequestParam(value = "payType", required = false) Integer payType,
			@RequestParam(value = "queryStr", required = false) String queryStr, @RequestParam(value = "startTime", required = false) Date startTime,
			@RequestParam(value = "endTime", required = false) Date endTime, @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			HttpServletRequest request, Model model) {
		if (startTime != null) {
			model.addAttribute("startTime", DateUtil.format(startTime, "yyyy-MM-dd"));
		}
		if (endTime != null) {
			model.addAttribute("endTime", DateUtil.format(endTime, "yyyy-MM-dd"));
			endTime = DateUtil.getDateEndTime(endTime);
		}
		//只显示付款之后的订单
		Pager<OrderPay> pagerOrderPay = payService.queryOrderPayListWithPayHistory(queryStr, payType, 1, startTime, endTime, pageNo, 10);
		for (OrderPay orderPay : pagerOrderPay.getList()) {
			User user = userService.getUser(orderPay.getUserId());
			if (user == null) {
				continue;
			}
			orderPay.setBxNum(user.getBxNum());
			orderPay.setPhone(user.getPhone());
		}
		model.addAttribute("dataList", pagerOrderPay.getList());
		model.addAttribute("pageInfo", pagerOrderPay);
		model.addAttribute("payType", payType);
		model.addAttribute("queryStr", queryStr);
		model.addAttribute("payTypeList", dictCodeService.queryDictCodeByDictType(DictTypeEnum.PAY_TYPE.getDictType()));
		return "financial/list";
	}

	@RequestMapping("/detail.html")
	public String detail(@RequestParam(value = "orderNumber", required = false) String orderNumber, HttpServletRequest request, Model model) {
		List<MallOrder> list = new ArrayList<MallOrder>();
		List<List<DictCode>> dictCodeList = new ArrayList<List<DictCode>>();
		// 原订单信息
		// MallOrder order = mallOrderService.getByOrderNumber(orderNumber);
		MallOrder order = orderService.getMallOrder(orderNumber);
		if(order==null){
			throw new OrderNullException("没有查询到该订单相关信息!");
		}
		// 查询订单详细信息
		// order.setOrderItems(mallOrderItemService.getListByOrderNumber(orderNumber));
		order.setOrderItems(orderService.queryOrderItems(null, orderNumber));
		// order = setStatus(orderNumber, order);
		// model.addAttribute("orderStatusList", setStatus(orderNumber, order));
		list.add(order);
		dictCodeList.add(setStatus(orderNumber, order));
		// 被拆分之后的订单信息
		// List<MallOrder> chList =
		// mallOrderService.getByParentOrderNumber(orderNumber);
		List<MallOrder> chList = orderService.queryParentOrders(orderNumber);
		if (chList != null && chList.size() > 0) {
			for (MallOrder mallOrder : chList) {
				// mallOrder.setOrderItems(mallOrderItemService.getListByOrderNumber(mallOrder.getOrderNumber()));
				mallOrder.setOrderItems(orderService.queryOrderItems(null, mallOrder.getOrderNumber()));
				// mallOrder = setStatus(mallOrder.getOrderNumber(), mallOrder);
				// model.addAttribute("orderStatusList", dictCodes);
				list.add(mallOrder);
				dictCodeList.add(setStatus(orderNumber, order));
			}
		}
		// MallOrderRevicerInformation reciver =
		// mallOrderRevicerInformationService.getByOrderIdAndUserId(order.getId(),
		// order.getUserId());
		MallOrderRevicerInformation reciver = orderService.getRevicerByUserIdAndOrderNumber(null, order.getOrderNumber());
		model.addAttribute("dataList", list);
		model.addAttribute("dictCodeList", dictCodeList);
		model.addAttribute("reciver", reciver);
		model.addAttribute("pay", payService.getPayHistoryByPayNumber(orderNumber));
		return "financial/detail";
	}

	// TODO
	private List<DictCode> setStatus(String orderNumber, MallOrder order) {
		List<MallOrderHistory> listHistory = orderService.queryHistoryByOrderNumber(orderNumber);

		List<DictCode> dictCodes = null;
		if (order.getIsCustomMade() != null && order.getIsCustomMade().intValue() == 1) {
			dictCodes = dictCodeService.queryDictCodeList(DictTypeEnum.ORDER_STATUS);
		} else {
			dictCodes = dictCodeService.queryDictCodeList(DictTypeEnum.ORDER_STATUS, (byte) 0);
		}
		for (DictCode code : dictCodes) {
			for (MallOrderHistory history : listHistory) {
				if (code.getDictCodeValue().trim().equals(history.getStatus().toString())) {
					code.setFlow(true);
					code.setOrderHistory(history);
				}
			}
		}
		// for (MallOrderHistory mallOrderHistory : listHistory) {
		// if(mallOrderHistory.getStatus().intValue() == 10){
		// order.setStatus10(mallOrderHistory);
		// }
		// if(mallOrderHistory.getStatus().intValue() == 20){
		// order.setStatus20(mallOrderHistory);
		// }
		// if(mallOrderHistory.getStatus().intValue() == 30){
		// order.setStatus30(mallOrderHistory);
		// }
		// if(mallOrderHistory.getStatus().intValue() == 40){
		// order.setStatus40(mallOrderHistory);
		// }
		// if(mallOrderHistory.getStatus().intValue() == 50){
		// order.setStatus50(mallOrderHistory);
		// }
		// if(mallOrderHistory.getStatus().intValue() == 60){
		// order.setStatus60(mallOrderHistory);
		// }
		// }
		return dictCodes;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}
