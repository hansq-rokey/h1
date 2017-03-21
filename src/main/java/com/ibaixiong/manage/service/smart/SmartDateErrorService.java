package com.ibaixiong.manage.service.smart;

import java.util.List;

import com.ibaixiong.entity.SmartDateError;

public interface SmartDateErrorService {

	/**
	 * 查询所有
	 * @return
	 */
	List<SmartDateError> queryAllSmartDateErrors(int pageNo);
	/**
	 * 查询已发送的
	 * @return
	 */
	List<SmartDateError> querySendedSmartDateErrors(int pageNo);
	/**
	 * 查询未处理的
	 * @return
	 */
	List<SmartDateError> queryUnhandleSmartDateErrors(int pageNo);
	
	List<SmartDateError> querySmartDateErrors(Byte status,int pageNo);
	
	int updateSmartDateError(SmartDateError error);
	
	int sendSmartDateError();
}
