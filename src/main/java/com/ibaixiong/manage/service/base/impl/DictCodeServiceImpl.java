package com.ibaixiong.manage.service.base.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.manage.dao.base.DictCodeDao;
import com.ibaixiong.manage.service.base.DictCodeService;
import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.util.DictTypeEnum;

@Service
public class DictCodeServiceImpl implements DictCodeService {

	@Resource
	private DictCodeDao dictCodeDao;
	
	@Override
	public List<DictCode> queryDictCodeByDictType(String dictType) {
		
		return dictCodeDao.queryDictCodeByDictType(dictType);
	}
	@Override
	public List<DictCode> queryDictCodeList(Byte type) {
		return queryDictCodeList(null, type);
	}
	@Override
	public List<DictCode> queryDictCodeList(DictTypeEnum dictType, Byte type) {
		Map<String, Object> map=new HashMap<String, Object>();
		if(dictType != null)
			map.put("dictType", dictType.getDictType());
		map.put("type", type);
		map.put("isDisplay", true);
		return dictCodeDao.queryDictCodeList(map);
	}
	
	@Override
	public List<DictCode> queryDictCodeList(DictTypeEnum dictType, Byte type,Boolean isDisplay) {
		Map<String, Object> map=new HashMap<String, Object>();
		if(dictType != null)
			map.put("dictType", dictType.getDictType());
		map.put("type", type);
		map.put("isDisplay", isDisplay);
		return dictCodeDao.queryDictCodeList(map);
	}
	
	@Override
	public List<DictCode> queryDictCodeList(DictTypeEnum dictType) {
		return queryDictCodeList(dictType,null);
	}
	@Override
	public int save(DictCode dictCode) {
		return dictCodeDao.insertSelective(dictCode);
	}
	@Override
	public int update(DictCode dictCode) {
		return dictCodeDao.updateByPrimaryKeySelective(dictCode);
	}
	@Override
	public DictCode selectByPrimaryKey(Long id) {
		return dictCodeDao.selectByPrimaryKey(id);
	}
}
