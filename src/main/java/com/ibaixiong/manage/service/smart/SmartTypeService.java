/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.smart;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ibaixiong.entity.SmartType;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年8月6日-下午12:13:11
 */
public interface SmartTypeService {
	/**
	 * 获取智能类型列表，支持分页
	 * @param status		状态
	 * @param pageNo		页码
	 * @return
	 */
	List<SmartType> listSmartTypeByStatus(Byte status,Integer pageNo);

	/**
	 * 
	 * @param bxid4
	 * @return
	 */
	SmartType getSmartTypeByBxid4(String bxid4);
	
	SmartType getSmartType(Long id);
	
	int saveSmartType(SmartType smartType);
	
	String uploadImg(MultipartFile file, String bxid4,String name);
}
