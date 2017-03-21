package com.ibaixiong.manage.service.ding;

import java.util.List;

import com.ibaixiong.entity.DdUser;

public interface DdUserService {
	
	List<DdUser> queryDdUsersByDepartmentId(String departmentId);
	
	List<DdUser> queryDbUserByAdminId(String adminId);
	
	List<DdUser> queryAll();
	
	DdUser getByUserId(String userId);
}
