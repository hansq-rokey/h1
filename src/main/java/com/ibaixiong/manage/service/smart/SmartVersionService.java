package com.ibaixiong.manage.service.smart;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ibaixiong.entity.SmartVersion;

public interface SmartVersionService {

	/**
	 * 根据状态查询智能硬件版本，支持分页
	 * @param status	状态
	 * @param pageNo	页码
	 * @return
	 */
	List<SmartVersion> listSmartVersionsByStatus(Byte status,Integer pageNo);
	/**
	 * 
	 * @param pageNo
	 * @return
	 */
	List<SmartVersion> listNormallSmartVersions(Integer pageNo);
	
	int saveSmartVersion(SmartVersion smartVersion);
	
	int updateSmartVersion(SmartVersion smartVersion);
	/**
	 * 保存升级文件
	 * @param file				升级文件
	 * @param cVersionLast2		硬件版本
	 * @return
	 */
	SmartVersion saveUpgradeFile(MultipartFile file,SmartVersion smartVersion);
	
	SmartVersion getSmartVersion(Long id);
}
