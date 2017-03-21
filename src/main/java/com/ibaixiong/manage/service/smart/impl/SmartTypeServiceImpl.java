/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.smart.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.Constant.Status;
import com.ibaixiong.core.utils.ALiYunUtil;
import com.ibaixiong.entity.SmartType;
import com.ibaixiong.manage.dao.smart.SmartTypeDao;
import com.ibaixiong.manage.service.smart.SmartTypeService;

/**
 * @description
 * @author chenzehe
 * @email hljuczh@163.com
 * @create 2015年8月11日-下午1:29:38
 */
@Service
@Transactional
public class SmartTypeServiceImpl implements SmartTypeService {
	@Autowired
	SmartTypeDao smartTypeDao;

	@Override
	public List<SmartType> listSmartTypeByStatus(Byte status,Integer pageNo) {
		PageHelper.startPage(pageNo, 10, true);
		List<SmartType> smartTypeList = smartTypeDao.listSmartTypeByStatus(status);
		return smartTypeList;
	}

	@Override
	public SmartType getSmartTypeByBxid4(String bxid4) {
		return smartTypeDao.selectByBxid4(bxid4);
	}

	@Override
	public SmartType getSmartType(Long id) {
		
		return smartTypeDao.selectByPrimaryKey(id);
	}

	@Override
	public int saveSmartType(SmartType smartType) {
		smartType.setCreateDateTime(new Date());
		smartType.setUpdateTime(new Date());
		smartType.setStatus(Status.NORMAL.getStatus());
		
		return smartTypeDao.insertSelective(smartType);
	}

	@Override
	public String uploadImg(MultipartFile file, String bxid4,String name) {
		String original = file.getOriginalFilename();
		String suffx = original.substring(original.lastIndexOf(".") + 1, original.length());
		String key = ALiYunUtil.createSmartTypeKey(suffx, bxid4, name);
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
}
