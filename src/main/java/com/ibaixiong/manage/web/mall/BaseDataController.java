package com.ibaixiong.manage.web.mall;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.manage.service.mall.ProductService;
import com.ibaixiong.manage.web.util.WebUtil;
import com.papabear.commons.entity.Pager;
import com.papabear.product.api.CategoryCUDService;
import com.papabear.product.api.CategoryQueryService;
import com.papabear.product.api.ProductCUDService;
import com.papabear.product.api.ProductQueryService;
import com.papabear.product.entity.MallBasicCategory;
import com.papabear.product.entity.MallBasicCategoryModel;
import com.papabear.product.entity.MallBasicCategoryModelExt;
import com.papabear.product.entity.MallBasicCategoryModelFormat;
import com.papabear.product.entity.MallFormatExt;
import com.papabear.product.entity.MallProduct;
import com.papabear.product.entity.MallProductProperties;

@Controller
@RequestMapping("/mall")
public class BaseDataController {

	@Resource
	private CategoryQueryService categoryQueryService;
	@Resource
	private CategoryCUDService categoryCUDService;
	@Resource
	private ProductQueryService productQueryService;
	@Resource
	private ProductCUDService productCUDService;
	@Resource
	private ProductService productService;
	
	/**
	 * 类目设置-》产品品类
	 * @param model
	 * @return
	 */
	@RequestMapping("/base/data")
	public String toBaseData(ModelMap model){

		model.addAttribute("categoryList", categoryQueryService.queryBasicCategory(null));
		
		return "mall/base_data";
	}
	/**
	 * 产品品类保存
	 * @param category
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/base/data/save")
	public String saveCaotegory(@ModelAttribute("category")MallBasicCategory category,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		int code=0;
		String msg = "";
		if(StringUtils.isBlank(category.getName())||StringUtils.isBlank(category.getCode())||category.getCode().length()!=2){
			msg="不能为空或者类目代码长度不对!";
		}else{
			try {
				category.setCreatorId(WebUtil.getLoginUser(request).getId());
				code=categoryCUDService.saveCategory(category);				
			} catch (Exception e) {
				msg="保存失败，请检查是否有重复信息！";
			}
		}
		return JSON.toJSONString(ResponseResult.result(code, msg,map));
	}
	
	/**
	 * 保存、修改产品型号时对属性值上传图片
	 * 异步上传图片
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upload")
	public String uploadPic(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletResponse response){
		int code=1;
		Map<String, Object> map=new HashMap<String, Object>();
		if(file==null||file.isEmpty()){
			code=0;
		}else{
			String url=productService.upload(file);
			map.put("url", url);
		}
		return JSON.toJSONString(ResponseResult.result(code, "",map));
	}
	/**
	 * 类目设置-》产品型号
	 * 查询产品型号所有数据
	 * @param modelId  型号ID
	 * @param type     1：增加  2：update
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/base/model/list")
	public String queryModelList(HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "";
		int code = 1;
		List<MallBasicCategoryModel> modelList=categoryQueryService.queryAllCategoryModel();
		int total = modelList.size();
		int pageSize = 10;
		int pageNo = 1;
		Pager<MallBasicCategoryModel> pager = new Pager<MallBasicCategoryModel>(total,pageNo,pageSize);
		pager.setList(modelList);
		for(MallBasicCategoryModel m:modelList){
			MallBasicCategory cate=new MallBasicCategory();
			cate.setCode(m.getCategory().getCode());
			m.setCategory(cate);
		}
		map.put("models", modelList);
		return JSON.toJSONString(ResponseResult.result(code, msg,map));
	}
	
	@ResponseBody
	@RequestMapping("/model/category")
	public String queryModelListByCategoryId(@RequestParam Long id){

		List<MallBasicCategoryModel> list=categoryQueryService.queryByCategoryId(id);
		List<Map<String, Object>> listMap=new ArrayList<Map<String,Object>>();
		
		for(MallBasicCategoryModel model:list){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("id", model.getId());
			map.put("name", model.getName());
			map.put("code", model.getCode());
			listMap.add(map);
		}
		
		
		return JSON.toJSONString(listMap);
	}
	
	
	/**
	 * 产品型号详情并编辑
	 * @param modelId  型号ID
	 * @param type     1：增加  2：update
	 * @param response
	 */
	@RequestMapping("/base/model/edit")
	public String edit(@RequestParam(value="id",required=false)Long id,HttpServletResponse response,HttpServletRequest request,ModelMap model){
		
		if(id!=null&&id.intValue()!=0){
			MallBasicCategoryModel categoryModel=categoryQueryService.getModel(id);
			model.addAttribute("model", categoryModel);
			List<MallBasicCategoryModelFormat> formats=categoryQueryService.queryByCategoryModel(id);
			model.addAttribute("formats", formats);
			model.addAttribute("properties", productQueryService.queryProductPropertiesByModelId(id));
			return "mall/edit_model";
		}else{
			model.addAttribute("categoryList", categoryQueryService.queryBasicCategory(null));
			return "mall/add_model";
		}
	}
	
	/**
	 * 产品型号和属性添加
	 * @param response
	 * @param request
	 * @param categoryModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/base/model/save")
	public String save(@ModelAttribute("model") MallBasicCategoryModelExt categoryModel,ModelMap model
			,HttpServletResponse response,HttpServletRequest request){
		
//		Long id=categoryModel.getId();
//		if(id==null){
//			modelService.save(categoryModel, WebUtil.getLoginUser(request));
//		}else{
//			modelService.update(categoryModel, WebUtil.getLoginUser(request));
//		}
		categoryModel.setCreatorId(WebUtil.getLoginUser(request).getId());
		categoryCUDService.insertCategoryModelExt(categoryModel);
		return "redirect:/mall/base/data.html";
	}
	
	/**
	 * 产品型号和属性添加
	 * @param response
	 * @param request
	 * @param categoryModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/base/model/update")
	public String update(@ModelAttribute("model") MallBasicCategoryModelExt categoryModel,ModelMap model
			,HttpServletResponse response,HttpServletRequest request){
		System.out.println("----");
//		Long id=categoryModel.getId();
//		if(id==null){
//			modelService.save(categoryModel, WebUtil.getLoginUser(request));
//		}else{
//			modelService.update(categoryModel, WebUtil.getLoginUser(request));
//		}
		categoryModel.setCreatorId(WebUtil.getLoginUser(request).getId());
		categoryCUDService.updateCategoryModelExt(categoryModel);
		return "redirect:/mall/base/data.html";
	}
	
	/**
	 * 
	 * @param modelId  型号ID
	 * @param type     1：增加  2：update
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/base/formats")
	public String queryModelFomarts(@RequestParam("modelId") Long modelId,@RequestParam(value="type",defaultValue="1") Integer type,
			HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "";
		int code = 0;
		List<MallProduct> productList=productQueryService.queryProductByFormat(modelId, true);
		if(type==1&&productList.size()>0){
			msg = "该型号下已经有产品了";
			code = -1;
		}else{
			List<MallBasicCategoryModelFormat> formatList=categoryQueryService.queryByCategoryModel(modelId);
			map.put("formats", formatList);
		}
		return JSON.toJSONString(ResponseResult.result(code, msg,map));
	}
	
	/**
	 * 
	 * @param modelId  型号ID
	 * @param type     1：增加  2：update
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/base/properties")
	public String queryModelProperties(@RequestParam("modelId") Long modelId,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "";
		int code = 0;
			List<MallProductProperties> propertiesList=productQueryService.queryProductPropertiesByModelId(modelId);
			map.put("properties", propertiesList);
		return JSON.toJSONString(ResponseResult.result(code, msg,map));
	}
	
	/**
	 * 型号编码
	 * @param categoryId
	 * @param code
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/model/check")
	public String checkProductNumber(@RequestParam("categoryId") Long categoryId,
			@RequestParam("code")String code,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "";
		int resultCode=0;
		if(StringUtils.isNotBlank(code)&&categoryId!=null&&categoryId.longValue()!=0){
			resultCode=1;
			map.put("success", categoryQueryService.checkCode(categoryId, code));
		}
		return JSON.toJSONString(ResponseResult.result(resultCode, msg,map));
	}
	
	//添加规格扩展属性
	@ResponseBody
	@RequestMapping("/format/ext/add")
	public String addFormatExt(@RequestParam Long formatId,@RequestParam int len,HttpServletRequest request){
		int code=1;
		String msg="提交成功";
		try {
			MallBasicCategoryModelFormat format=categoryQueryService.getCategoryModelFormat(formatId);
			if(format==null){
				code=0;
				msg="失败";
				return JSON.toJSONString(ResponseResult.result(code, msg));
			}
			List<MallFormatExt> list=new ArrayList<MallFormatExt>();
			for(int i=1;i<=len;i++){
				String id=request.getParameter("id-"+i);
				String type=request.getParameter("type-"+i);
				String identify=request.getParameter("identify-"+i);
				String propertyName=request.getParameter("propertyName-"+i);
				if(StringUtils.isBlank(type)||StringUtils.isBlank(propertyName)||StringUtils.isBlank(identify)){
					continue;
				}
				MallFormatExt ext=new MallFormatExt();
				ext.setCategoryModelFormatId(formatId);
				ext.setIdentify(identify);
				ext.setPropertyName(propertyName);
				ext.setType(Byte.valueOf(type));
				if(StringUtils.isNotBlank(id)){
					ext.setId(Long.valueOf(id));
				}
				list.add(ext);
			}
			for(MallFormatExt ext:list){
				categoryCUDService.saveOrUpdateFormatId(ext);
			}
			format.setIsExtProperties((byte)1);
			categoryCUDService.updateCategoryModelFormat(format);
		} catch (Exception e) {
			code=0;
			msg="失败";
		}
		
//		String[] typeArr=type.split(",");
//		String[] propertyNameArr=propertyName.split(",");
//		String[] identifyArr=identify.split(",");
//		int typeLen=typeArr.length;
//		int propertyNameLen=propertyNameArr.length;
//		int identifyLen=identifyArr.length;
//		if(typeLen!= propertyNameLen&&typeLen!=identifyLen){
//			return JSON.toJSONString(ResponseResult.result(0, "提交数据的不允许为空"));
//		}
//		for(int i=0;i<typeLen;i++){
//			categoryCUDService.saveFormatId(formatId, propertyNameArr[i], identifyArr[i], Byte.valueOf(typeArr[i]));
//		}
		return JSON.toJSONString(ResponseResult.result(code, msg));
	}
	
	@ResponseBody
	@RequestMapping("/format/ext/list")
	public String queryFormatExt(@RequestParam Long formatId){
		Map<String, Object>	map=new HashMap<String, Object>();
		List<MallFormatExt> formatExtList=categoryQueryService.queryMallFormatExts(formatId, null);
		map.put("result", formatExtList);
		return JSON.toJSONString(ResponseResult.result(1, "success",map));
	}
}
