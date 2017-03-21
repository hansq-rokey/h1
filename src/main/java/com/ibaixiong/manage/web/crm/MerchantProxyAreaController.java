package com.ibaixiong.manage.web.crm;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.entity.MerchantProxyArea;
import com.ibaixiong.manage.service.crm.MerchantProxyAreaService;
import com.ibaixiong.manage.service.crm.SsssCityMerchantService;
import com.ibaixiong.manage.service.crm.StaffRegionService;
import com.ibaixiong.manage.web.util.Response;

/**
 * @author Administrator
 * 代理商负责区域
 */
@Controller
@RequestMapping("/proxyArea")
public class MerchantProxyAreaController {

	@Resource
	private MerchantProxyAreaService areaService;
	@Resource
	private SsssCityMerchantService cityMerchantService;
	@Resource
	private StaffRegionService staffRegionService;
	
	/**
	 * 判断加入的地区是否已经存在，这里不做插入数据操作，只做判断
	 * @param countyId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add")
	public String addArea(@RequestParam(value="areaCode",required = false)Long areaCode){
		Response response = new Response();
		MerchantProxyArea proxyArea = areaService.selectByAreaCode(areaCode);
		if(proxyArea!=null){
			response.setMessage("此地区已有负责人");
			response.setSuccess(false);
		}
		return JSON.toJSONString(response);
	}
}






















