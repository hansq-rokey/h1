package com.ibaixiong.manage.service.ding;

import com.ibaixiong.entity.DdAdminUser;

public interface DdAdminUserService {
	void deleteByAdminId(String adminId);
	void insert(DdAdminUser adminUser);
}
