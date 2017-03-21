package com.ibaixiong.manage.service.ccm;

import java.util.List;

import com.ibaixiong.entity.CcmProcess;

public interface CcmProcessService {
	void insert(CcmProcess process);
	List<CcmProcess> queryProcessByAdminId(Long adminId);
	List<CcmProcess> queryProcessByQuestionId(Long questionId);
	/**
	 * 根据当前问题ID和登陆人ID查询里面存在指派的记录
	 * @author zhaolei
	 * @date 2015年8月19日
	 * @param questionId
	 * @param adminId
	 * @return
	 */
	CcmProcess getProcessByQidAndLid(Long questionId,Long adminId);
	void update(CcmProcess process);
}
