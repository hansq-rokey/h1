package com.ibaixiong.manage.service.base;

import java.util.List;

import com.ibaixiong.manage.service.base.mode.DictType;

public interface DictTypeService {

	/**
	 * 查询字典表状态数据
	 * @return
	 */
	public List<DictType> queryDictTypeList();
	
	/**
	 * 根据状态编码查询状态信息
	 * @param dictType
	 * @return
	 */
	public DictType getByDictType(String dictType);
	
	/**
	 * 新增状态类型
	 * @param dictType
	 */
	public void insert(DictType dictType);
}
