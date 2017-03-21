/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.service.mall;

import java.util.List;

import com.ibaixiong.entity.ErpFormatMaterialRelation;

/**
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年8月16日
 * @since  1.0.0
 */
public interface ErpFormatMaterialRelationService {
	
	int add(Long formatId,Long materialId);
	
	int delete(Long formatId,Long materialId);
	
	ErpFormatMaterialRelation getErpFormatMaterialRelation(Long formatId,Long materialId);
	
	List<ErpFormatMaterialRelation> queryErpFormatMaterialRelations(Long formatId);

}
