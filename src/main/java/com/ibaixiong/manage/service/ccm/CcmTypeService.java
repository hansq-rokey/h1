package com.ibaixiong.manage.service.ccm;

import java.util.List;

import com.ibaixiong.entity.CcmType;

public interface CcmTypeService {
	List<CcmType> getAll();
	void insert(CcmType type);
}
