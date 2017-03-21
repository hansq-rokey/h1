package com.ibaixiong.manage.service.sys.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.SysLog;
import com.ibaixiong.manage.dao.sys.SysLogDao;
import com.ibaixiong.manage.service.sys.SysLogService;
@Service
public class SysLogServiceImpl implements SysLogService {
	@Resource
	private SysLogDao sysLogDao;
	@Override
	public List<SysLog> querySysLogList(String queryName, String startDate,
			String endDate, Long adminId,Integer pageNo) {
		PageHelper page= new PageHelper();
		page.startPage(pageNo, PageConstant.pageSize, true);
		return sysLogDao.querySysLogList(queryName, startDate, endDate, adminId);
	}

}
