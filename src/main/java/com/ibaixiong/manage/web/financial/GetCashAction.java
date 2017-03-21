package com.ibaixiong.manage.web.financial;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.SsssCityMerchant;
import com.ibaixiong.entity.SsssGetcash;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.manage.service.crm.SsssCityMerchantService;
import com.ibaixiong.manage.service.crm.SsssGetcashService;
import com.ibaixiong.manage.service.crm.SsssInfoService;
import com.ibaixiong.manage.web.util.WebUtil;
import com.papabear.pay.api.PayService;

/**
 * 提现审核
 * 
 * @author zhaolei
 *
 */
@Controller
@RequestMapping("/cash")
public class GetCashAction {
	@Resource
	SsssGetcashService ssssGetcashService;
	@Resource
	SsssInfoService ssssInfoService;
	@Resource
	SsssCityMerchantService ssssCityMerchantService;
	@Resource
	PayService payService;

	@RequestMapping("/list.html")
	public String toList(Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SsssGetcash> list = ssssGetcashService.getList(map);
		model.addAttribute("dataList", list);
		return "financial/cashList";
	}

	@RequestMapping("/setStatus.html")
	public void setStatus(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "status", required = false) Byte status, Model model,
			HttpServletResponse response, HttpServletRequest request) {
		// 修改提现表
		SysAdmin admin = WebUtil.getLoginUser(request);
		SsssGetcash updateCash = new SsssGetcash();
		updateCash.setId(id);
		updateCash.setStatus(status);
		updateCash.setAdminId(admin.getId());
		updateCash.setUpdateTime(new Date());
		ssssGetcashService.update(updateCash);
		// 如果是同意提现金额需要添加变更记录金额
		if (status.intValue() == 1) {
			SsssGetcash cash = ssssGetcashService.get(id);
			String remark = "提现申请通过，提现金额：" + cash.getMoney() + ",提现用户:" + cash.getUserId() + ",操作管理员:" + admin.getId();
			payService.createTakeCashPay(-cash.getMoney(), cash.getUserId(), remark, cash.getSsssId(), cash.getCityMerchantId());
			SsssCityMerchant city = ssssCityMerchantService.getById(cash.getCityMerchantId());
			city.setMoney(city.getMoney()-cash.getMoney());
			ssssCityMerchantService.update(city);
		}
		// 修改
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JSON.toJSONString(ResponseResult.result(0, "")));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
}
