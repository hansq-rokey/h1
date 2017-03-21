package com.ibaixiong.manage.service.ding;

import java.util.List;

import com.ibaixiong.constant.InvalidEnum;
import com.ibaixiong.entity.DdOrg;
import com.ibaixiong.manage.exception.OApiException;

public interface DepartmentService {

	int syncDepartment() throws OApiException;
	
	/**
	 * 查询有效的部门列表
	 * @param invalidEnum
	 * @return
	 */
	List<DdOrg> queryInvalidDepartment(InvalidEnum invalidEnum);
	
}
