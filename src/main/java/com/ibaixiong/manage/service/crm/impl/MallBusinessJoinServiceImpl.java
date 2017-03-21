/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.crm.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.MallBusinessJoin;
import com.ibaixiong.manage.dao.crm.MallBusinessJoinDao;
import com.ibaixiong.manage.service.crm.MallBusinessJoinService;
import com.papabear.commons.entity.enumentity.Constant.Status;

/**
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年8月15日
 * @since  1.0.0
 */
@Service
public class MallBusinessJoinServiceImpl implements MallBusinessJoinService {

	@Resource
	private MallBusinessJoinDao businessJoinDao;
	/* (non-Javadoc)
	 * @see com.ibaixiong.mall.service.MallBusinessJoinService#add(com.ibaixiong.entity.MallBusinessJoin)
	 */
	@Override
	public void add(MallBusinessJoin businessJoin) {
		businessJoin.setCreateDateTime(new Date());
		businessJoin.setStatus(Status.NORMAL.getStatus());
		businessJoin.setOrigin((byte)1);
		businessJoinDao.insert(businessJoin);
		
	}
	/* (non-Javadoc)
	 * @see com.ibaixiong.manage.service.crm.MallBusinessJoinService#queryBusinessJoins(java.lang.Byte)
	 */
	@Override
	public List<MallBusinessJoin> queryBusinessJoins(Byte status,int pageNo,int pageSize) {
		PageHelper.startPage(pageNo, pageSize, "id desc");
		return businessJoinDao.queryBusinessJoins(status);
	}
	/* (non-Javadoc)
	 * @see com.ibaixiong.manage.service.crm.MallBusinessJoinService#updateRead(java.lang.Long)
	 */
	@Override
	public int updateRead(Long id) {
		MallBusinessJoin businessJoin=businessJoinDao.selectByPrimaryKey(id);
		if(businessJoin!=null){
			businessJoin.setStatus((byte)2);//标记已读
			businessJoinDao.updateByPrimaryKeySelective(businessJoin);
			return 1;
		}
		
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.ibaixiong.manage.service.crm.MallBusinessJoinService#delete(java.lang.Long)
	 */
	@Override
	public int delete(Long id) {
		MallBusinessJoin businessJoin=businessJoinDao.selectByPrimaryKey(id);
		if(businessJoin!=null){
			businessJoin.setStatus(Status.DELETE.getStatus());
			businessJoinDao.updateByPrimaryKeySelective(businessJoin);
			return 1;
		}
		return 0;
	}
	@Override
	public List<MallBusinessJoin> queryBusinessList(Byte status,
			Date startTime, Date endTime, Integer pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageHelper page = new PageHelper();
		page.startPage(pageNo,PageConstant.bbspageSize, true);
		return businessJoinDao.queryBusinessList(map);
	}
	@Override
	public List<MallBusinessJoin> queryBusinessList(Byte status,
			Date startTime, Date endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return businessJoinDao.queryBusinessList(map);
	}

}
