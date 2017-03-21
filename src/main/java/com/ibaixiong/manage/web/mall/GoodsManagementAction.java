package com.ibaixiong.manage.web.mall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.core.utils.ALiYunUtil;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.ErpFormatMaterialRelation;
import com.ibaixiong.entity.ErpPurchaseMaterial;
import com.ibaixiong.entity.FreightTemplate;
import com.ibaixiong.entity.SysAdmin;
import com.ibaixiong.manage.exception.PriceException;
import com.ibaixiong.manage.service.base.DictCodeService;
import com.ibaixiong.manage.service.mall.ErpFormatMaterialRelationService;
import com.ibaixiong.manage.service.mall.ErpPurchaseMaterialService;
import com.ibaixiong.manage.service.mall.FreightTemplateService;
import com.ibaixiong.manage.service.mall.ProductService;
import com.ibaixiong.manage.util.DictTypeEnum;
import com.ibaixiong.manage.web.util.Response;
import com.ibaixiong.manage.web.util.WebUtil;
import com.papabear.commons.entity.Pager;
import com.papabear.commons.entity.enumentity.Constant.ProductStatus;
import com.papabear.commons.entity.enumentity.Constant.Status;
import com.papabear.commons.entity.enumentity.InvalidEnum;
import com.papabear.commons.entity.enumentity.PicTypeEnum;
import com.papabear.dis.api.DisCUDService;
import com.papabear.dis.api.DisQueryService;
import com.papabear.dis.entity.DisBase;
import com.papabear.product.api.CategoryCUDService;
import com.papabear.product.api.CategoryQueryService;
import com.papabear.product.api.ProductCUDService;
import com.papabear.product.api.ProductQueryService;
import com.papabear.product.entity.MallBasicCategoryModel;
import com.papabear.product.entity.MallBasicCategoryModelFormat;
import com.papabear.product.entity.MallProduct;
import com.papabear.product.entity.MallProductDetail;
import com.papabear.product.entity.MallProductPic;
import com.papabear.product.entity.MallProductProperties;
import com.papabear.product.entity.MallProductTag;
import com.papabear.product.entity.MallProductTagData;
import com.papabear.product.entity.ProductType;

/**
 * 商品管理 baixiong.com Inc. Copyright (c) 1999-2001 All Rights Reserved.
 * 
 * @author yaoweiguo
 * @Email yaoweiguo@ibaixiong.com
 * @Description TODO
 * @date 2015年8月11日
 *
 */
@Controller
@RequestMapping("/mall")
public class GoodsManagementAction {

	@Resource
	private CategoryQueryService categoryQueryService;
	@Resource
	private ProductQueryService productQueryService;
	@Resource
	private DictCodeService dictCodeService;
	@Resource
	private ProductCUDService productCUDService;
	@Resource
	private DisCUDService disCUDService;
	@Resource
	private DisQueryService disQueryService;
	@Resource
	private ProductService productService;
	@Resource
	private CategoryCUDService categoryCUDService;
	@Resource
	private ErpPurchaseMaterialService erpPurchaseMaterialService;
	@Resource
	private ErpFormatMaterialRelationService formatMaterialRelationService;
	@Resource
	private FreightTemplateService  freightTemplateService;
	
	Logger log=LoggerFactory.getLogger(getClass());

	/**
	 * 商品添加跳转
	 * 
	 * @return
	 */
	@RequestMapping("/product/toadd")
	public String toAdd(ModelMap model) {
		// 查询可用的产品型号
		model.addAttribute("modelList", categoryQueryService.queryModelByNotProduct());
		model.addAttribute("templates", freightTemplateService.queryAll());
		model.addAttribute("tags", productCUDService.queryListTags());
//		return "mall/add_product";
		return "mall/add_product_first_new";
	}
	
	
	/**
	 * 商品保存,基本信息保存
	 * STEP 1
	 * @return
	 */
	@RequestMapping(value="/product/save",method=RequestMethod.POST,params={"level=1"})
	public String saveStepOne(@ModelAttribute("product") MallProduct product,ModelMap model,HttpServletRequest request) {
		Float priceValue = Float.parseFloat(request.getParameter("price"));
		//商品标签数组
		String[] tagIds = request.getParameterValues("tagId");
		if(product.getIsMaterialCalculate()==null){
			product.setIsMaterialCalculate((byte)0);
		}
		if(product.getcDisplay()==null){
			product.setcDisplay(Byte.parseByte("0"));
		}
		if(product.getbDisplay()==null){
			product.setbDisplay(Byte.parseByte("0"));
		}
		SysAdmin admin = WebUtil.getLoginUser(request);
		product.setCreatorId(admin.getId());
		Long productId =productService.save(product);
		if (productId == null) {
			model.addAttribute("msg", "新增商品保存失败");
			model.addAttribute("modelList", categoryQueryService.queryModelByNotProduct());
			return "mall/add_product_first_new";
		}
		model.addAttribute("productId", productId);
		model.addAttribute("modelId", product.getCategoryModelId());
		MallProduct mallProduct = productQueryService.getProduct(productId);
		mallProduct.setDetailUrlApp("http://m.ibaixiong.com/product/detail/"+productId+".html");
		productCUDService.updateProduct(mallProduct);
		//增加商品标签记录
		if(tagIds!=null){
			for(String tagId:tagIds){
				MallProductTag productTag = new MallProductTag();
				productTag.setCreateDateTime(new Date());
				productTag.setProductId(productId);
				productTag.setUpdateDateTime(new Date());
				productTag.setTagId(Long.parseLong(tagId));
				productCUDService.insert(productTag);
			}
		}
		//更新所有规格的进货价格，跨区域获取金额,和规格可见性
		List<MallBasicCategoryModelFormat> formats= categoryQueryService.queryFormatsByModelId(product.getCategoryModelId());
		for(MallBasicCategoryModelFormat format:formats){
			format.setProductPurchasePrice(product.getProductPurchasePrice());
			format.setAreaMoney(product.getAreaMoney());
			format.setPrice(priceValue);
			format.setcDisplay(product.getcDisplay());
			format.setbDisplay(product.getbDisplay());
			format.setFreightTemplateId(product.getFreightTemplateId());
			categoryCUDService.updateCategoryModelFormat(format);
		}
		return "mall/add_product_second_new";
	}
	
	/**
	 * 商品APP详情页、参数页保存
	 * STEP 2
	 * @return
	 */
	@RequestMapping(value="/product/save",params={"level=2"})
	public String saveStepTwo(@ModelAttribute MallProductDetail productDetail,ModelMap model, HttpServletRequest request) {
		productDetail.setStatus(Status.NORMAL.getStatus());
		String modelIdStr=request.getParameter("modelId");

		if (StringUtils.isBlank(productDetail.getAppParamterDetail())||
				StringUtils.isBlank(productDetail.getAppProductDetail())||
				StringUtils.isBlank(modelIdStr)) {
			model.addAttribute("msg", "数据不能为空");
			model.addAttribute("productId", productDetail.getProductId());
			model.addAttribute("modelId", modelIdStr);
			return "mall/add_product_second_new";
		}
		productCUDService.createProductDetail(productDetail);
		
		List<MallProductProperties> propertiesList=productQueryService.queryProductPropertiesByModelId(Long.parseLong(modelIdStr));
		model.addAttribute("properties", propertiesList);
		List<MallBasicCategoryModelFormat> formatList=categoryQueryService.queryByCategoryModel(Long.parseLong(modelIdStr));
		List<MallBasicCategoryModelFormat> formats = new ArrayList<MallBasicCategoryModelFormat>();
		for(MallBasicCategoryModelFormat format : formatList){
			format.setTemplateName(freightTemplateService.selectByPrimaryKey(format.getFreightTemplateId()).getName());
			formats.add(format);
		}
		model.addAttribute("templates", freightTemplateService.queryAll());
		model.addAttribute("formats", formats);
		model.addAttribute("productId", productDetail.getProductId());
		return "mall/add_product_third_new";
	}

	
	/**
	 * 商品规格数据修改
	 * STEP 3
	 * @return
	 */
	@RequestMapping(value = "/product/save", params = { "level=3" })
	public String saveStepThird(
			@ModelAttribute MallBasicCategoryModelFormat format,
			ModelMap model, HttpServletRequest request) {
		System.out.println(
				format.getId()+","+format.getLength()+","+format.getWidth()+","+format.getExplain()+","+format.getAreaMoney()
				+format.getcDisplay()+","+format.getbDisplay());
//		Long productId = Long.parseLong(request.getParameter("productId"));
//		MallProduct product = productQueryService.getProduct(productId);
		if (format.getPrice() == null || format.getPrice().floatValue() == 0) {
			throw new PriceException("价格不能为空");
		}
		if (format.getLength() == null) {
			format.setLength(0);
		}
		if (format.getWidth() == null) {
			format.setWidth(0);
		}
		if(format.getcDisplay()==null){
			format.setcDisplay((byte)0);
		}
		if(format.getbDisplay()==null){
			format.setbDisplay((byte)0);
		}
		categoryCUDService.updateCategoryModelFormat(format);

		return "redirect:/mall/product/list.html";
	}
	
	/**
	 * 产品修改,产品基础数据修改
	 * 默认第一步
	 * @author yaoweiguo
	 * @date 2016年8月10日
	 * @param id
	 * @param model
	 * @return
	 * @since V2.1.0
	 */
	@RequestMapping(value="/product/edit")
	public String editStepOne(@RequestParam("id") Long id, ModelMap model){
		MallProduct product = productQueryService.getProduct(id);
		List<MallBasicCategoryModelFormat> format = categoryQueryService.queryByCategoryModel(product.getCategoryModelId());
		if(product.getFreightTemplateId()!=null){
			FreightTemplate template = freightTemplateService.selectByPrimaryKey(product.getFreightTemplateId());
			product.setTemplateName(template.getName());
		}
		//商品现有的标签
		List<MallProductTag> productTags = productCUDService.queryProductTagsByProductId(product.getId());
		//所有标签
		List<MallProductTagData> tags =	productCUDService.queryListTags();
		//商品现有标签和所有标签比较，用于编辑显示
		List<MallProductTagData> newTags = new ArrayList<MallProductTagData>();
		for(MallProductTagData tag:tags){
			for(MallProductTag productTag : productTags){
				if(productTag.getTagId()==tag.getId()){
					tag.setType((byte)0);//状态，页面默认选中条件
				}
			}
			newTags.add(tag);
		}
		model.addAttribute("tags", newTags);
		model.addAttribute("product", product);
		model.addAttribute("productId", product.getId());
		model.addAttribute("price", format.get(0).getPrice());
		//所有规格模板
		model.addAttribute("templates", freightTemplateService.queryAll());
		
		return "mall/edit_product_first_new";
	}
	
	/**
	 * 产品修改,产品详细页、参数页修改
	 * 默认第二步
	 * @author yaoweiguo
	 * @date 2016年8月10日
	 * @param id
	 * @param model
	 * @return
	 * @since V2.1.0
	 */
	@RequestMapping(value="/product/edit",params={"level=2"})
	public String editStepTwo(@RequestParam("id") Long id, ModelMap model){
		MallProductDetail detail=productQueryService.getProductDetail(id, Status.NORMAL.getStatus());
		model.addAttribute("detail", detail);
		model.addAttribute("productId", detail.getProductId());
		return "mall/edit_product_second_new";
	}
	
	/**
	 * 产品修改,产品基础数据修改
	 * 默认第三步
	 * @author yaoweiguo
	 * @date 2016年8月10日
	 * @param id
	 * @param model
	 * @return
	 * @since V2.1.0
	 */
	@RequestMapping(value="/product/edit",params={"level=3"})
	public String editStepThird(@RequestParam("id") Long id, ModelMap model){
		MallProduct product = productQueryService.getProduct(id);
		model.addAttribute("product", product);
		
		List<MallProductProperties> propertiesList=productQueryService.queryProductPropertiesByModelId(product.getCategoryModelId());
		model.addAttribute("properties", propertiesList);
		List<MallBasicCategoryModelFormat> formatList=categoryQueryService.queryByCategoryModel(product.getCategoryModelId());
		List<MallBasicCategoryModelFormat> formats = new ArrayList<MallBasicCategoryModelFormat>();
		for(MallBasicCategoryModelFormat format : formatList){
			FreightTemplate template = freightTemplateService.selectByPrimaryKey(format.getFreightTemplateId());
			format.setTemplateName(template.getName());
			formats.add(format);
		}
		model.addAttribute("formats", formats);
		model.addAttribute("productId", id);
		// 产品缩略图
		model.addAttribute("productPicList", productQueryService.queryPics(product, null, PicTypeEnum.APP_THUMBNAIL.getType().shortValue()));
		//所有规格模板
		model.addAttribute("templates", freightTemplateService.queryAll());
		return "mall/edit_product_third_new";
	}
	
	/**
	 * 商品更新
	 * 
	 * @return
	 * @since V2.1.0
	 */
	@RequestMapping(value="/product/update",params={"level=1"})
	public String updateStepOne(@ModelAttribute("product") MallProduct product,
			HttpServletRequest request) throws IOException {
		//商品标签Id数组
		String[] tagIds = request.getParameterValues("tagId");
		Float price = Float.parseFloat(request.getParameter("price"));
		
		if(product.getcDisplay()==null){
			product.setcDisplay(Byte.parseByte("0"));
		}
		if(product.getbDisplay()==null){
			product.setbDisplay(Byte.parseByte("0"));
		}
		SysAdmin admin = WebUtil.getLoginUser(request);
		product.setCreatorId(admin.getId());
		productCUDService.updateProduct(product);
		
		//商品现有的标签
		List<MallProductTag> productTags = productCUDService.queryProductTagsByProductId(product.getId());
		//如果商品原有标签为null,则直接添加操作
		if(productTags==null){
			for(String tagId : tagIds){
				MallProductTag ptag = new MallProductTag();
				ptag.setProductId(product.getId());
				ptag.setTagId(Long.parseLong(tagId));
				ptag.setCreateDateTime(new Date());
				ptag.setUpdateDateTime(new Date());
				productCUDService.insert(ptag);
			}
		}else{
			if(tagIds!=null){
				//判断现有的标签是否在页面上选中的标签数组中
				for(MallProductTag productTag : productTags){
					boolean flag = Arrays.asList(tagIds).contains(productTag.getTagId()+"");
					if(!flag){
						productCUDService.deleteProductTag(productTag.getId());
					}
				}
				//更新或者添加标签
				for(String tagId : tagIds){
					MallProductTag mallProductTag = productCUDService.selectProductTagByIds(product.getId(),Long.parseLong(tagId));
					if(mallProductTag!=null){
						mallProductTag.setUpdateDateTime(new Date());
						productCUDService.updateProductTag(mallProductTag);
					}else{
						MallProductTag ptag = new MallProductTag();
						ptag.setProductId(product.getId());
						ptag.setTagId(Long.parseLong(tagId));
						ptag.setCreateDateTime(new Date());
						ptag.setUpdateDateTime(new Date());
						productCUDService.insert(ptag);
					}
				}
			}else{
				//页面上没有选中商品标签-点击保存，要把现有的商品标签数据全部删除掉
				productCUDService.deleteProductTagByProductId(product.getId());
			}
		}
		List<MallBasicCategoryModelFormat> formats= categoryQueryService.queryFormatsByModelId(product.getCategoryModelId());
		for(MallBasicCategoryModelFormat format:formats){
			format.setcDisplay(product.getcDisplay());
			format.setbDisplay(product.getbDisplay());
			format.setFreightTemplateId(product.getFreightTemplateId());
			categoryCUDService.updateCategoryModelFormat(format);
		}

		return "redirect:/mall/product/edit.html?id="+product.getId();
	}
	/**
	 * 商品更新
	 * 
	 * @return
	 * @since V2.1.0
	 */
	@RequestMapping(value="/product/update",params={"level=2"})
	public String updateStepTwo(@ModelAttribute MallProductDetail productDetail){
		MallProductDetail detail=productQueryService.getProductDetail(productDetail.getProductId(), Status.NORMAL.getStatus());
		if(detail==null){
			productDetail.setStatus(Status.NORMAL.getStatus());
			productCUDService.createProductDetail(productDetail);
		}else{
			productCUDService.updateProductDetail(productDetail);			
		}
		
		return "redirect:/mall/product/edit.html?id="+productDetail.getProductId()+"&level=2";
	}
	/**
	 * 商品更新
	 * 
	 * @return
	 * @since V2.1.0
	 */
	@RequestMapping(value="/product/update",params={"level=3"})
	public String updateStepThird(
			@ModelAttribute MallBasicCategoryModelFormat format,
			HttpServletRequest request) {
		Long productId = Long.parseLong(request.getParameter("productId"));
		if (format.getPrice() == null || format.getPrice().floatValue() == 0) {
			throw new PriceException("价格不能为空");
		}
		if (format.getLength() == null) {
			format.setLength(0);
		}
		if (format.getWidth() == null) {
			format.setWidth(0);
		}
		if(format.getcDisplay()==null){
			format.setcDisplay((byte)0);
		}
		if(format.getbDisplay()==null){
			format.setbDisplay((byte)0);
		}
		categoryCUDService.updateCategoryModelFormat(format);

		return "redirect:/mall/product/edit.html?id=" + productId + "&level=3";
	}
	
	@RequestMapping("/product/update/status")
	public String updateProductStatus(@RequestParam Long id,
			@RequestParam(value="pageNo",defaultValue="1",required=false) Integer pageNo){
		
		MallProduct product=productQueryService.getProduct(id);
		byte redirectStatus=product.getStatus();
		if(product!=null){
			byte status=ProductStatus.ONLINE.getStatus();
			if(product.getStatus().intValue()==ProductStatus.ONLINE.getStatus().intValue()){
				status=ProductStatus.OFFLINE.getStatus();
			}
			product.setStatus(status);
		}
		productCUDService.updateProduct(product);
		
		return "redirect:/mall/product/list.html?status="+redirectStatus+"&pageNo="+pageNo;
	}
	
	/**
	 * 商品更新跳转
	 * 
	 * @return
	 */
	@RequestMapping("/product/toupdate")
	public String toUpdate(@RequestParam("id") Long id, ModelMap model) {
		MallProduct product = productQueryService.getProduct(id);
		model.addAttribute("product", product);
		// 产品缩略图
		model.addAttribute("productPicList", productQueryService.queryPics(product, null, (short) 1));
		// model.addAttribute("modelList",
		// basicCategoryModelService.queryAll());
		// 返回产品相关对象ID进行查询
		if (product.getBasicCategoryModel() != null) {
			DisBase dbase = disQueryService.getBaseByModelId(product.getBasicCategoryModel().getId());
			if (dbase != null) {
				// 优惠金额，有效天数两个参数的添加
				model.addAttribute("baseId", dbase.getId());
				model.addAttribute("disMoney", dbase.getDisMoney());
				model.addAttribute("profitMoney", dbase.getProfitMoney());
				model.addAttribute("timeCount", dbase.getTimeCount());
			} else {
				model.addAttribute("baseId", "");
				model.addAttribute("disMoney", "");
				model.addAttribute("profitMoney", "");
				model.addAttribute("timeCount", "");
			}
		}
		return "mall/update_product";
	}

	/**
	 * 商品保存
	 * 
	 * @return
	 */
//	@RequestMapping("/product/save")
	@Deprecated
	public String save(@ModelAttribute("product") MallProduct product,@RequestParam(value="disMoney",defaultValue="0") float disMoney, 
			@RequestParam(value="profitMoney",defaultValue="0") float profitMoney,
			@RequestParam(value="timeCount",defaultValue="0") int timeCount,ModelMap model, HttpServletRequest request) {
		SysAdmin admin = WebUtil.getLoginUser(request);
		String picId=request.getParameter("picId");
		product.setCreatorId(admin.getId());
		log.debug("图片上传ID={}", picId);
		int flag =productService.save(product, picId);
//		int flag = productCUDService.saveProduct(product, file);
		if (flag == 0) {
			model.addAttribute("msg", "新增商品保存失败");
			model.addAttribute("modelList", categoryQueryService.queryModelByNotProduct());
			return "mall/add_product";
		}
		if(disMoney>0&&profitMoney>0&&timeCount>0){
			DisBase base = new DisBase();
			base.setCategoryModelId(product.getBasicCategoryModel().getId());
			base.setDisMoney(disMoney);
			base.setProfitMoney(profitMoney);
			base.setTimeCount(timeCount);
			disCUDService.saveDisBase(base);			
		}
		return "redirect:/mall/product/list.html";
	}

	/**
	 * 商品更新
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/product/update")
	public String update(@RequestParam(value = "file", required = false) MultipartFile file, @ModelAttribute("product") MallProduct product,
			@RequestParam(value="disMoney",defaultValue="0") float disMoney, @RequestParam(value="profitMoney",defaultValue="0") float profitMoney, 
			@RequestParam(value="timeCount",defaultValue="0") int timeCount,
			HttpServletRequest request) throws IOException {
		SysAdmin admin = WebUtil.getLoginUser(request);
		product.setCreatorId(admin.getId());
		productService.update(product, file);
		// productCUDService.updateProduct(product,file);
//		if (file == null || file.isEmpty() || file.getSize() == 0) {
//			productCUDService.updateProduct(product, null, null, 0);
//		} else {
//			productCUDService.updateProduct(product, file.getInputStream(), file.getOriginalFilename(), file.getSize());
//		}
		
		if(disMoney>0&&profitMoney>0&&timeCount>0){
			DisBase base = new DisBase();
			base.setCategoryModelId(product.getBasicCategoryModel().getId());
			base.setDisMoney(disMoney);
			base.setProfitMoney(profitMoney);
			base.setTimeCount(timeCount);
			disCUDService.saveDisBase(base);			
		}
		return "redirect:/mall/product/list.html";
	}

	/**
	 * 商品列表
	 * 
	 * @return
	 */
	@RequestMapping("/product/list")
	public String goodsList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value="status",defaultValue="1") Byte status,Model model) {
		if (pageNo == null) {
			pageNo = 1;
		}
		Pager<MallProduct> pager = productQueryService.queryAllProduct(status, pageNo,10);
		//PageInfo<MallProduct> pageInfo = new PageInfo<MallProduct>(productList);
		model.addAttribute("pager", pager);
		model.addAttribute("list", pager.getList());
		model.addAttribute("url", "/mall/product/list.html?status="+status+"&pageNo=");
		model.addAttribute("status", status);
		model.addAttribute("proStatusList", dictCodeService.queryDictCodeByDictType(DictTypeEnum.PRODUCT_STATUS.getDictType()));
		return "mall/product_list_new";
	}

	/**
	 * 产品类型列表
	 * 
	 * @return
	 */
	@RequestMapping("/product/delete")
	public String productDelete(@RequestParam("id") Long id, HttpServletRequest request) {
		MallProduct product=productQueryService.getProduct(id);
		if(product==null){
			
			
		}
		if(product.getStatus().intValue()!=ProductStatus.OFFLINE.getStatus().intValue()){
			return "";
		}
		//更新规格
//		List<MallBasicCategoryModelFormat > formatList=categoryQueryService.queryByCategoryModel(product.getCategoryModelId());
//		for(MallBasicCategoryModelFormat format:formatList){
//			format.setInvalid(InvalidEnum.TRUE.getInvalidValue());
//			categoryCUDService.updateCategoryModelFormat(format);
//		}
//		//更新型号
//		MallBasicCategoryModel model=categoryQueryService.getCategoryModelById(product.getCategoryModelId());
//		model.setInvalid(InvalidEnum.TRUE.getInvalidValue());
//		categoryCUDService.updateCategoryModel(model);
		//更新产品
		productCUDService.deleteProduct(id, WebUtil.getLoginUser(request).getId());
		return "redirect:/mall/product/list.html?status=-1";
	}

	/**
	 * 异步上传图片
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/product/upload")
	public String uploadPic(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "formatId", required = false) Long formatId,
			@RequestParam(value = "productId", required = false) Long productId, @RequestParam(value = "type", required = false) String type,
			HttpServletResponse response) {
		int code = 1;
		Map<String, Object> map =productService.upload(file, productId, formatId, Byte.valueOf(type));
		if (map == null) {
			code = 0;
		}
		return JSON.toJSONString(ResponseResult.result(code, "", map));
	}
	
	/**
	 * 产品缩略图上传，支持多图上传
	 * @author yaoweiguo
	 * @date 2016年8月10日
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upload/thumbnail")
	public String uploadProductThumbnail(@RequestParam(value = "file") MultipartFile[] files,
			@RequestParam(value = "productId", required = false) Long productId){
		List<Map<String,  Object >> result= productService.uploadThumbnail(files, productId);
		return JSON.toJSONString(result);
	}
	
	@ResponseBody
	@RequestMapping(value="/pic/delete")
	public String deleteAppThumbnail(@RequestParam Long id){
		Response response=new Response();
		MallProductPic pic=productQueryService.getProductPic(id);
		if(pic==null){
			response.setMessage("没有找到该图片");
			response.setSuccess(Boolean.FALSE);
			return JSON.toJSONString(response);
		}
		
		// 删除远程oss上的图片
		if (StringUtils.isNotBlank(pic.getUrl())) {
			String key = pic.getUrl().replace(ALiYunUtil.IMAGE_URL, "");
			ALiYunUtil.deleteObject(ALiYunUtil.BUCKET_NAME, key);// 删除远程图片
			productCUDService.deleteProductPic(id);
		}
		response.setMessage("成功删除该图片");
		return JSON.toJSONString(response);
	}
	
	/**
	 * 查询物料列表
	 * @author yaoweiguo
	 * @date 2016年8月17日
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/material")
	public String queryPurchaseMaterial(){
		List<ErpPurchaseMaterial> list=erpPurchaseMaterialService.getList(1);
		Response response=new Response();
		response.setResult(list);
		return JSON.toJSONString(response);
	}
	
	/**
	 * 查询规格下的物料信息
	 * @author yaoweiguo
	 * @date 2016年8月17日
	 * @param formatId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/material/format")
	public String queryformatMaterialRelation(@RequestParam Long formatId){
		List<ErpFormatMaterialRelation> list=formatMaterialRelationService.queryErpFormatMaterialRelations(formatId);
		Response response=new Response();
		response.setResult(list);
		return JSON.toJSONString(response);
	}
	
	/**
	 * 保存或更新规格与物流ID之间的关系
	 * @author yaoweiguo
	 * @date 2016年8月17日
	 * @param formatId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/material/update")
	public String saveOrUpdateFormatMaterialRelations(@RequestParam Long formatId,
			@RequestParam Long materialId){
		ErpFormatMaterialRelation formatMaterialRelation=formatMaterialRelationService.getErpFormatMaterialRelation(formatId, materialId);
		if(formatMaterialRelation==null){
			formatMaterialRelationService.add(formatId, materialId);
		}else{
			formatMaterialRelationService.delete(formatId, materialId);
		}
		Response response=new Response();
		return JSON.toJSONString(response);
	}

	/**
	 * 产品类型保存
	 * 
	 * @return
	 */
	@RequestMapping("/type/save")
	public String productTypeSave() {

		return "";
	}

	@RequestMapping("/type/edit")
	public String productTypeEdit(@RequestParam(required = true, value = "id") Long id, Model model) {
		ProductType productType = productQueryService.getProductType(id);
		model.addAttribute("bean", productType);
		return "";
	}

	@RequestMapping("/type/update")
	public String productTypeUpdate() {

		return "";
	}
}
