/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.manage.dao.drp;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.DrpAccount;

/**
 * 
 * @author yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年5月9日
 * @since  1.0.0
 */
public interface DrpAccountDao {
	int deleteByPrimaryKey(Long id);

	int insert(DrpAccount record);

	int insertSelective(DrpAccount record);

	DrpAccount selectByPrimaryKey(Long id);

	DrpAccount getDrpAccountByUserId(@Param("userId") Long userId);

	int updateByPrimaryKeySelective(DrpAccount record);

	int updateByPrimaryKey(DrpAccount record);

	int updateAccountAlipay(@Param("userId") Long userId, @Param("userRealName") String userRealName, @Param("userPhone") String userPhone,
			@Param("alipayName") String alipayName, @Param("alipayCnName") String alipayCnName);
}