package com.ibaixiong.manage.service.ding;

import java.util.List;

import com.ibaixiong.entity.DdOrgUser;

public interface DdOrgUserService {
	List<DdOrgUser> getByUserId(String userId);
}
