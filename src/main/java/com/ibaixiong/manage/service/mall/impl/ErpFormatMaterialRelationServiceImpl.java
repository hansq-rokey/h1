/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.mall.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.ErpFormatMaterialRelation;
import com.ibaixiong.manage.dao.mall.ErpFormatMaterialRelationDao;
import com.ibaixiong.manage.service.mall.ErpFormatMaterialRelationService;
import com.papabear.commons.entity.enumentity.Constant.Status;

/**
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年8月17日
 * @since  1.0.0
 */
@Service
public class ErpFormatMaterialRelationServiceImpl implements ErpFormatMaterialRelationService {
	@Resource
	private ErpFormatMaterialRelationDao formatMaterialRelationDao;

	/* (non-Javadoc)
	 * @see com.ibaixiong.manage.service.mall.ErpFormatMaterialRelationService#add(java.lang.Long, java.lang.Long)
	 */
	@Override
	public int add(Long formatId, Long materialId) {
		ErpFormatMaterialRelation relation=new ErpFormatMaterialRelation();
		relation.setCreateDateTime(new Date());
		relation.setFormatId(formatId);
		relation.setMaterialId(materialId);
		relation.setStatus(Status.NORMAL.getStatus());
		relation.setUpdateTime(new Date());
		
		
		return formatMaterialRelationDao.insertSelective(relation);
	}

	/* (non-Javadoc)
	 * @see com.ibaixiong.manage.service.mall.ErpFormatMaterialRelationService#delete(java.lang.Long, java.lang.Long)
	 */
	@Override
	public int delete(Long formatId, Long materialId) {
		
		return formatMaterialRelationDao.deleteByFormatIdAndMaterialId(formatId, materialId);
	}

	/* (non-Javadoc)
	 * @see com.ibaixiong.manage.service.mall.ErpFormatMaterialRelationService#queryErpFormatMaterialRelations(java.lang.Long)
	 */
	@Override
	public List<ErpFormatMaterialRelation> queryErpFormatMaterialRelations(Long formatId) {
		// TODO Auto-generated method stub
		return formatMaterialRelationDao.queryErpFormatMaterialRelations(formatId);
	}

	/* (non-Javadoc)
	 * @see com.ibaixiong.manage.service.mall.ErpFormatMaterialRelationService#getErpFormatMaterialRelation(java.lang.Long, java.lang.Long)
	 */
	@Override
	public ErpFormatMaterialRelation getErpFormatMaterialRelation(Long formatId, Long materialId) {
		
		return formatMaterialRelationDao.getErpFormatMaterialRelation(formatId, materialId);
	}

}
