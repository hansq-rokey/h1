package com.ibaixiong.manage.service.crm.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.MerchantProxyArea;
import com.ibaixiong.manage.dao.crm.MerchantProxyAreaDao;
import com.ibaixiong.manage.service.crm.MerchantProxyAreaService;

@Service
public class MerchantProxyAreaServiceImpl implements MerchantProxyAreaService {
	
	@Resource
	private MerchantProxyAreaDao areaDao;
	@Override
	public void insertArea(MerchantProxyArea record) {
		// TODO Auto-generated method stub
		areaDao.insertSelective(record);
	}

	@Override
	public int updateArea(MerchantProxyArea record) {
		// TODO Auto-generated method stub
		return areaDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<MerchantProxyArea> queryByMerchantId(Long merchantId) {
		// TODO Auto-generated method stub
		return areaDao.queryAreaByMerchantId(merchantId);
	}

	@Override
	public MerchantProxyArea selectByAreaCode(Long areaCode) {
		// TODO Auto-generated method stub
		return areaDao.selectAreaByCode(areaCode);
	}

	@Override
	public int deleteArea(Long merchantId, Long areaCode) {
		// TODO Auto-generated method stub
		return areaDao.deleteAreaByCityIdAndCode(merchantId, areaCode);
	}

	@Override
	public int deleteAreaByCityId(Long merchantId) {
		// TODO Auto-generated method stub
		return areaDao.deleteAreaByCityId(merchantId);
	}

}
