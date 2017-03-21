package com.ibaixiong.manage.service.ccm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.CcmProcess;
import com.ibaixiong.manage.dao.ccm.CcmProcessDao;
import com.ibaixiong.manage.service.ccm.CcmProcessService;
@Service
public class CcmProcessServiceImpl implements CcmProcessService {
	@Resource
	private CcmProcessDao ccmProcessDao;
	@Override
	public void insert(CcmProcess process) {
		ccmProcessDao.insertSelective(process);
	}

	@Override
	public List<CcmProcess> queryProcessByAdminId(Long adminId) {
		return ccmProcessDao.queryProcessByAdminId(adminId);
	}
	@Override
	public List<CcmProcess> queryProcessByQuestionId(Long questionId) {
		return ccmProcessDao.queryProcessByQuestionId(questionId);
	}
	@Override
	public CcmProcess getProcessByQidAndLid(Long questionId, Long adminId) {
		return ccmProcessDao.getProcessByQidAndLid(questionId, adminId);
	}
	@Override
	public void update(CcmProcess process) {
		ccmProcessDao.updateByPrimaryKeySelective(process);
	}
}
