package com.ibaixiong.manage.service.mall;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ibaixiong.entity.MallCustomPic;

public interface MallCustomPicService {

	List<MallCustomPic> queryMallCustomPic(Map<String, Object> map);
	
	MallCustomPic getmCustomPic (Long id);
	
	int upload(MultipartFile file,String orderNumber,MallCustomPic pic,Long adminId);
	
	List<MallCustomPic> queryMallCustomPicByOrderNumber(String orderNumber);
}
