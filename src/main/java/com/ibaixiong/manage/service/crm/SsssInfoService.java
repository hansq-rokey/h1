package com.ibaixiong.manage.service.crm;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ibaixiong.entity.SsssInfo;

public interface SsssInfoService {
	List<SsssInfo> getListByPid(Long pid,String linkTel,Date startTime,Date endTime,Integer pageNo);
	
	void insert(SsssInfo city,HttpServletRequest req);
	
	void update(SsssInfo city);
	
	SsssInfo get(Long id);
	
	SsssInfo getByUserId(Long userId);

	SsssInfo getById(Long id);

	List<SsssInfo> getStopListByPid(Long pid,Integer pageNo);
}
