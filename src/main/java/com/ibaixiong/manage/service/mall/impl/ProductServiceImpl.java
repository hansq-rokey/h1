package com.ibaixiong.manage.service.mall.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.ibaixiong.core.utils.ALiYunUtil;
import com.ibaixiong.manage.exception.PriceException;
import com.ibaixiong.manage.service.mall.ProductService;
import com.papabear.commons.entity.enumentity.InvalidEnum;
import com.papabear.commons.entity.enumentity.PicTypeEnum;
import com.papabear.product.api.CategoryCUDService;
import com.papabear.product.api.CategoryQueryService;
import com.papabear.product.api.ProductCUDService;
import com.papabear.product.api.ProductQueryService;
import com.papabear.product.entity.MallBasicCategory;
import com.papabear.product.entity.MallBasicCategoryModel;
import com.papabear.product.entity.MallBasicCategoryModelFormat;
import com.papabear.product.entity.MallProduct;
import com.papabear.product.entity.MallProductPic;

@Service
public class ProductServiceImpl implements ProductService {
	@Resource
	private ProductCUDService productCUDService;
	@Resource
	private ProductQueryService productQueryService;
	@Resource
	private CategoryQueryService categoryQueryService;
	@Resource
	private CategoryCUDService categoryCUDService;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int save(MallProduct product, String picId) {
		if (product.getBasicCategoryModel() != null) {
			List<MallProduct> productList = productQueryService.queryProductByFormat(product.getBasicCategoryModel().getId(),
					InvalidEnum.FALSE.getInvalidValue());
			// 查询该型号下有无商品
			if (productList.size() > 0)
				return 0;
			MallBasicCategoryModel model = categoryQueryService.getCategoryModelById(product.getBasicCategoryModel().getId());
			if (model == null)
				return 0;
			product.setCategoryModelCode(model.getCode());

			MallBasicCategory category = categoryQueryService.getCategoryById(model.getCategoryId());
			product.setBasicCategory(category);
			product.setCategoryCode(category.getCode());
			product.setTitle(model.getName());
		}

		Long productId = productCUDService.createMallProduct(product);

		// 保存图片缩略图
		
		if (StringUtils.isNotBlank(picId)) {
			Long appPicId=Long.valueOf(picId);
			
			MallProductPic pic=productQueryService.getProductPic(appPicId);
			if(pic!=null){
				pic.setProductId(productId);
				productCUDService.updateMallProductPic(pic);
			}
		}
		// 产品规格
		List<MallBasicCategoryModelFormat> modelSet = product.getFormstList();
		for (MallBasicCategoryModelFormat format : modelSet) {
			if (format.getPrice() == null || format.getPrice().floatValue() == 0) {
				throw new PriceException("价格不能为空");
			}
			if (format.getDiscountPrice() == null) {
				format.setDiscountPrice(format.getPrice());
			}
			if(format.getLength()==null){
				format.setLength(0);
			}
			if(format.getWidth()==null){
				format.setWidth(0);
			}
			categoryCUDService.updateCategoryModelFormat(format);
		}
		return 1;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Long save(MallProduct product) {
		MallBasicCategoryModel model = categoryQueryService.getCategoryModelById(product.getCategoryModelId());
		if (model == null)
			return null;

		
		List<MallProduct> productList = productQueryService.queryProductByFormat(product.getCategoryModelId(),InvalidEnum.FALSE.getInvalidValue());
		// 查询该型号下有无商品
			if (productList.size() > 0)
				return null;
			
		product.setCategoryModelCode(model.getCode());

		MallBasicCategory category = categoryQueryService.getCategoryById(model.getCategoryId());
		product.setCategoryId(category.getId());
		product.setCategoryCode(category.getCode());
		product.setTitle(model.getName());
		Long productId = productCUDService.createMallProduct(product);

		return productId;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int update(MallProduct product, MultipartFile file) {
		productCUDService.updateProduct(product);

		// 产品规格
		List<MallBasicCategoryModelFormat> modelSet = product.getFormstList();
		for (MallBasicCategoryModelFormat format : modelSet) {
			format.setUpdateTime(new Date());
			if (format.getPrice() == null || format.getPrice().floatValue() == 0) {
				throw new PriceException("价格不能为空");
			}
			if (format.getDiscountPrice() == null) {
				format.setDiscountPrice(format.getPrice());
			}
			if(format.getLength()==null){
				format.setLength(0);
			}
			if(format.getWidth()==null){
				format.setWidth(0);
			}
			categoryCUDService.updateCategoryModelFormat(format);
		}
		return 1;
	}

	/**
	 * 上传至阿里云服务器
	 */
	@Override
	public String upload(MultipartFile file) {
		if (file == null || file.isEmpty() || file.getSize() == 0) {
			return null;
		}
		String original = file.getOriginalFilename();
		String suffx = original.substring(original.lastIndexOf(".") + 1, original.length());
		String key = ALiYunUtil.createCmsKey(suffx);
		try {
			ALiYunUtil.uploadFile(key, file);
		} catch (OSSException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ALiYunUtil.IMAGE_URL + key;
	}

	@Override
	public Map<String, Object> upload(MultipartFile file, Long productId, Long formatId, byte type) {
		Map<String, Object> map = new HashMap<String, Object>();
		MallProduct product = new MallProduct();
		product.setId(productId);
		// 通过规格及类型查询有无规格图片，由于生成规格的时候也允许上传图片，所以也需要判断
		List<MallProductPic> picList=null;
		if(productId==null&&formatId==null){
			picList=new ArrayList<MallProductPic>();
		}else{
			picList = productQueryService.queryPics(null, formatId, Byte.valueOf(type).shortValue());
		}
		Long picId=null;
		// 判断之前相对应的图片，目前只考虑没有、或一张
		// TODO 一对多的关系没有考虑
		String url = this.upload(file);
		if (url == null) {
			return null;
		}
		if (picList.size() == 0) {
			picId=productCUDService.createMallProductPic(productId, formatId, 1, type, url);
		} else if (picList.size() >= 1) {
			MallProductPic productPic = picList.get(0);
			// 删除远程oss上的图片
			if (StringUtils.isNotBlank(productPic.getUrl())) {
				String key = productPic.getUrl().replace(ALiYunUtil.IMAGE_URL, "");
				ALiYunUtil.deleteObject(ALiYunUtil.BUCKET_NAME, key);// 删除远程图片
				productPic.setUrl(url);
				productPic.setProductId(productId);
				productCUDService.updateMallProductPic(productPic);
				picId=productPic.getId();
			}

		} else {
			// TODO 多产品图片处理
		}
		map.put("url", url);
		map.put("id", picId);
		return map;
	}
	
	/**
	 * 上传产品APP缩略图，支持多张
	 */
	@Override
	public List<Map<String, Object>> uploadThumbnail(MultipartFile[] files, Long productId) {
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		MallProduct product = new MallProduct();
		product.setId(productId);
		// 通过规格及类型查询有无规格图片，由于生成规格的时候也允许上传图片，所以也需要判断
		List<MallProductPic> picList=null;
		picList = productQueryService.queryPics(product, null, PicTypeEnum.APP_THUMBNAIL.getType().shortValue());
		int size=picList.size();		
		
		if(files==null){
			return null;
		}
		//目前最多上传5张图片，超过5张不做处理，同时需要上传图片需要对之前的图片进行删除操作后才可以上传
		for(MultipartFile file:files){
			size++;
			if(size>5){
				break;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			String url = this.upload(file);
			if (url == null) {
				continue;
			}
			Long picId=productCUDService.createMallProductPic(productId, null, 1, PicTypeEnum.APP_THUMBNAIL.getType(), url);
			map.put("url", url);
			map.put("id", picId);
			result.add(map);
		}
		return result;
	}
}
