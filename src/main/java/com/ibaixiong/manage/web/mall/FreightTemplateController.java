package com.ibaixiong.manage.web.mall;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.entity.FreightTemplate;
import com.ibaixiong.entity.FreightTemplateValue;
import com.ibaixiong.entity.FreightTemplateValueArea;
import com.ibaixiong.entity.StaffRegion;
import com.ibaixiong.manage.service.crm.StaffRegionService;
import com.ibaixiong.manage.service.mall.FreightTemplateService;
import com.ibaixiong.manage.service.mall.FreightTemplateValueAreaService;
import com.ibaixiong.manage.service.mall.FreightTemplateValueService;
import com.ibaixiong.manage.util.TemplateTypeEnum;
import com.ibaixiong.manage.web.util.Response;
import com.papabear.commons.utils.StringUtils;
import com.papabear.product.api.CategoryCUDService;
import com.papabear.product.api.CategoryQueryService;
import com.papabear.product.api.ProductCUDService;
import com.papabear.product.api.ProductQueryService;
import com.papabear.product.entity.MallBasicCategoryModelFormat;
import com.papabear.product.entity.MallProduct;

@Controller
@RequestMapping("/mall/template")
public class FreightTemplateController {

	@Resource
	private FreightTemplateValueService freightTemplateValueService;
	@Resource
	private FreightTemplateService freightTemplateService;
	@Resource
	private StaffRegionService regionService;
	@Resource
	private FreightTemplateValueAreaService freightTemplateAreaService;
	@Resource
	private ProductQueryService productQueryService;
	@Resource
	private CategoryQueryService categoryQueryService;
	@Resource
	private ProductCUDService productCUDService;
	@Resource
	private CategoryCUDService categoryCUDService;
	
	/**
	 * 模板列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String queryAll(Model model,@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo){
		List<FreightTemplate> templates = freightTemplateService.queryAll(pageNo);
		PageInfo<FreightTemplate> pageInfo = new PageInfo<FreightTemplate>(templates);
		model.addAttribute("templates", templates);
		model.addAttribute("pageInfo", pageInfo);
		return "/mall/freight_template_list";
	}
	
	/**
	 * 模板详细信息
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(@RequestParam(value="templateId",required=false)Long templateId,
			Model model){
		FreightTemplate template = freightTemplateService.selectByPrimaryKey(templateId);
		if(template.getSendType()==TemplateTypeEnum.DEFINED.getType()){
			List<FreightTemplateValue> templateValues = freightTemplateValueService.queryByTemplateId(templateId);
			List<FreightTemplateValue> values = new ArrayList<FreightTemplateValue>();
			for(FreightTemplateValue value : templateValues){
				value.setFreightTemplate(template);
				value.setPriceAll(value.getPriceInner()+value.getNumOut()*value.getPriceOut());
				List<FreightTemplateValueArea> areas = freightTemplateAreaService.queryByTemplateValueId(value.getId());
				value.setProvinceName(areas.get(0).getProvinceName());
				value.setValueAreas(areas);
				values.add(value);
			}
			model.addAttribute("values", values);
		}
		return "/mall/freight_template_detail";
	}
	
	/**
	 * 删除模板
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteTemplate")
	public String deleteTemplate(
			@RequestParam(value="id",required=false)Long id,
			Model model){
		Response response = new Response();
		freightTemplateValueService.deleteByTemplateId(id);
		freightTemplateAreaService.deleteByTemplateId(id);
		int sum = freightTemplateService.deleteByPrimaryKey(id);
		//模板删除，使用该模板的商品和商品规格同步改成全国包邮模板
		List<MallProduct> products = productQueryService.queryProductByTemplateId(id);
		for(MallProduct product : products){
			product.setFreightTemplateId(1l);
			productCUDService.updateProduct(product);
			System.out.println(product.getCategoryModelId()+"-");
			List<MallBasicCategoryModelFormat> formats = categoryQueryService.queryFormatsByModelId(product.getCategoryModelId());
			System.out.println(formats.size());
			for(MallBasicCategoryModelFormat format : formats){
				System.out.println(format.getFreightTemplateId()+","+product.getFreightTemplateId());
				format.setFreightTemplateId(1l);
				categoryCUDService.updateCategoryModelFormat(format);
			}
		}
		if(sum>0){
			response.setMessage("删除成功");
		}
		return JSON.toJSONString(response);
	}
	
	/**
	 * 删除非全国包邮模板中的一条信息
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="id",required=false)Long id,
			Model model){
		Response response = new Response();
		freightTemplateAreaService.deleteByTemplateValueId(id, null);
		int count = freightTemplateValueService.deleteByPrimaryKey(id);
		if(count>0){
			response.setMessage("删除成功");
		}
		return JSON.toJSONString(response);
	}
	
	/**
	 * 删除区域
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteArea")
	public String deleteArea(@RequestParam(value="templateValueId",required=false)Long templateValueId,
			@RequestParam(value="cityId",required=false)Long cityId,
			Model model){
		Response response = new Response();
		if(templateValueId!=null){
			int count = freightTemplateAreaService.deleteByTemplateValueId(templateValueId, cityId);
			if(count>0){
				response.setMessage("删除成功");
			}
		}
		return JSON.toJSONString(response);
	}
	
	/**
	 * 新增模板
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/add")
	public String add(@RequestParam(value="name",required=false)String name,
			@RequestParam(value="sendType",required=false)Byte sendType,
			@RequestParam(value="valuationType",required=false)Byte valuationType,
			@RequestParam(value="id",required=false)Long id,
			Model model,HttpServletRequest request){
		Response response = new Response();
		String mName = request.getParameter("m_name");
		String dName = request.getParameter("d_name");
		FreightTemplate temp = freightTemplateService.selectByName(name);
		FreightTemplate template = new FreightTemplate();
		if(id!=null){
			template = freightTemplateService.selectByPrimaryKey(id);
			if(temp!=null && !(template.getName().equals(name))){
				response.setSuccess(false);
				response.setMessage("该模板已经存在");
				return JSON.toJSONString(response);
			}
			template.setName(name);
			template.setSendType(sendType);
			template.setUpdateDateTime(new Date());
			freightTemplateService.updateByPrimaryKeySelective(template);
			freightTemplateValueService.deleteByTemplateId(id);
			freightTemplateAreaService.deleteByTemplateId(id);
		}else{
			if(temp!=null){
				response.setSuccess(false);
				response.setMessage("该模板已经存在");
				return JSON.toJSONString(response);
			}
			template.setName(name);
			template.setSendType(sendType);
			template.setCreateDateTime(new Date());
			template.setUpdateDateTime(new Date());
			freightTemplateService.insert(template);
		}
		if(sendType==TemplateTypeEnum.DEFINED.getType()){
			String[] mStr = mName.split("-");
			FreightTemplateValue templateValue = new FreightTemplateValue();
			templateValue.setTemplateId(template.getId());
			templateValue.setValuationType(valuationType);
			templateValue.setNumInner(Float.parseFloat(mStr[0]));
			templateValue.setPriceInner(Float.parseFloat(mStr[1]));
			templateValue.setNumOut(Float.parseFloat(mStr[2]));
			templateValue.setPriceOut(Float.parseFloat(mStr[3]));
			if(valuationType==1){
				templateValue.setUnit("件");
			}else{
				templateValue.setUnit("kg");
			}
			templateValue.setCreateDateTime(new Date());
			templateValue.setUpdateDateTime(new Date());
			freightTemplateValueService.insert(templateValue);
			FreightTemplateValueArea valueArea = new FreightTemplateValueArea();
			valueArea.setTemplateId(template.getId());
			valueArea.setTemplateValueId(templateValue.getId());
			valueArea.setCreateDateTime(new Date());
			freightTemplateAreaService.insert(valueArea);
			if(!StringUtils.isEmpty(dName)){
				String[] dStr = dName.split(",");
				for(String str : dStr){
					if(!StringUtils.isEmpty(str)){
						String[] value = str.split("-");
						FreightTemplateValue tValue = new FreightTemplateValue();
						tValue.setTemplateId(template.getId());
						tValue.setValuationType(valuationType);
						tValue.setNumInner(Float.parseFloat(value[1]));
						tValue.setPriceInner(Float.parseFloat(value[2]));
						tValue.setNumOut(Float.parseFloat(value[3]));
						tValue.setPriceOut(Float.parseFloat(value[4]));
						tValue.setCreateDateTime(new Date());
						tValue.setUpdateDateTime(new Date());
						if(valuationType==1){
							tValue.setUnit("件");
						}else{
							tValue.setUnit("kg");
						}
						freightTemplateValueService.insert(tValue);
						String[] provinceIds = value[0].split("=");
						for(String provinceId : provinceIds){
							if(!StringUtils.isEmpty(provinceId)){
								FreightTemplateValueArea area = new FreightTemplateValueArea();
								StaffRegion region = regionService.selectByPrimaryKey(Long.parseLong(provinceId));
								area.setProvinceId(Long.parseLong(provinceId));
								area.setProvinceName(region.getName());
								area.setTemplateId(template.getId());
								area.setTemplateValueId(tValue.getId());
								area.setCityId(null);
								area.setCityName(null);
								area.setCreateDateTime(new Date());
								freightTemplateAreaService.insert(area);
							}
						}
					}
				}
			}
		}
		return JSON.toJSONString(response);
	}
	
	/**
	 * 编辑模板
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/update")
	public String update(@RequestParam(value="id",required=false)Long id,
			Model model){
		Response response = new Response();
		Map<String,Object> map = new HashMap<String, Object>();
		FreightTemplate template = freightTemplateService.selectByPrimaryKey(id);
		map.put("template", template);
		List<FreightTemplateValue> templateValues = freightTemplateValueService.queryByTemplateId(id);
		List<FreightTemplateValue> values = new ArrayList<FreightTemplateValue>();
		if(templateValues.size()>0){
			for(FreightTemplateValue value : templateValues){
				List<FreightTemplateValueArea> area = freightTemplateAreaService.queryByTemplateValueId(value.getId());
				value.setValueAreas(area);
				if(area.size()==1 && area.get(0).getProvinceId()==null){
					map.put("value", value);
					continue;
				}
				values.add(value);
				map.put("values", values);
			}
		}
		response.setResult(map);
		return JSON.toJSONString(response);
	}
	
	/**
	 * 编辑模板的时候检查城市是否已经在该模板中
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkArea")
	public String checkArea(
			@RequestParam(value="provinceId",required=false)Long provinceId,
			@RequestParam(value="templateId",required=false)Long templateId){
		Response response = new Response();
		if(templateId!=null){
			FreightTemplateValueArea area = freightTemplateAreaService.selectByIds(templateId, provinceId);
			if(area!=null){
				response.setMessage("该城市已经在当前模板中");
				response.setSuccess(false);
			}
		}
		return JSON.toJSONString(response);
	}
}
