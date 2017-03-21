package com.ibaixiong.manage.web.crm;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.entity.ChannelArea;
import com.ibaixiong.entity.SsssCityMerchant;
import com.ibaixiong.entity.StaffRegion;
import com.ibaixiong.manage.service.crm.ChannelAreaService;
import com.ibaixiong.manage.service.crm.SsssCityMerchantService;
import com.ibaixiong.manage.service.crm.StaffRegionService;
import com.ibaixiong.manage.web.util.Response;

/**
 * @author Administrator
 * 代理商负责区域
 */
@Controller
@RequestMapping("/area")
public class ChannelAreaController {

	@Resource
	private ChannelAreaService areaService;
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
	public String addArea(@RequestParam(value="countyId",required = false)Long countyId){
		Response response = new Response();
		ChannelArea channleArea = areaService.selectByCountyId(countyId);
		if(channleArea!=null){
			response.setMessage("此地区已有负责人");
			response.setSuccess(false);
		}
		return JSON.toJSONString(response);
	}
}






















