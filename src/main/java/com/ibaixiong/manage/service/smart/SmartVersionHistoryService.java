package com.ibaixiong.manage.service.smart;

import java.util.List;

import com.ibaixiong.entity.SmartVersion;
import com.ibaixiong.entity.SmartVersionHistory;

public interface SmartVersionHistoryService {

	/**
	 * 查询版本记录列表
	 * @param versionId
	 * @param pageNo
	 * @return
	 */
	List<SmartVersionHistory> listSmartVersionHistories(Long versionId,int pageNo);

	/**
	 * 保存
	 * @param history
	 * @return
	 */
	int saveSmartVersionHistory(SmartVersion smartVersion);
}
