package com.ibaixiong.manage.service.mall;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.papabear.product.entity.MallProduct;


public interface ProductService {

	/**
	 * 图片上传
	 * 
	 * @param file
	 * @return
	 */
	String upload(MultipartFile file);
	/**
	 * 异步上传图片
	 * @author yaoweiguo
	 * @date 2016年4月15日
	 * @param file
	 * @param productId
	 * @param formatId
	 * @return
	 */
	Map<String, Object> upload(MultipartFile file,Long productId,Long formatId,byte type);
	/**
	 * APP产品缩略图上传，最多上传5张，超过5张不做处理，
	 * 更换图片需先删除之前的图片
	 * @author yaoweiguo
	 * @date 2016年8月10日
	 * @param files
	 * @param productId
	 * @return
	 */
	public List<Map<String, Object>> uploadThumbnail(MultipartFile[] files, Long productId);
	/**
	 * 保存商品，同时更新APP缩略图ID
	 * @author yaoweiguo
	 * @date 2016年4月22日
	 * @param product		产品ID
	 * @param picId			图片ID
	 * @return
	 */
	int save(MallProduct product, String picId);
	
	/**
	 * 保存商品，同时更新APP缩略图ID
	 * @author yaoweiguo
	 * @date 2016年4月22日
	 * @param product		产品ID
	 * @param picId			图片ID
	 * @since 1.1
	 * @return Long productId
	 */
	Long save(MallProduct product);

	int update(MallProduct product, MultipartFile file);

}