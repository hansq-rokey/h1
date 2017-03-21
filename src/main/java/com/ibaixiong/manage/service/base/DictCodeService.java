package com.ibaixiong.manage.service.base;

import java.util.List;

import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.util.DictTypeEnum;

public interface DictCodeService {

	/**
	 * 根据字典类型查询值
	 * @param dictType
	 * @return
	 */
	List<DictCode> queryDictCodeByDictType(String dictType);
	
	/**
	 * 查询字典表数据
	 * @param dictType
	 * @param type 0：普通   1：私人订制    null：普通+私人定制
	 * @return
	 */
	public List<DictCode> queryDictCodeList(DictTypeEnum dictType,Byte type);
	
	List<DictCode> queryDictCodeList(DictTypeEnum dictType, Byte type,Boolean isDisplay);
	
	public List<DictCode> queryDictCodeList(Byte type);
	
	/**
	 * 查询字典表数据
	 * @param dictType
	 * @return
	 */
	public List<DictCode> queryDictCodeList(DictTypeEnum dictType);
	
	/**
	 * 新增状态
	 * @param dictCode
	 * @return
	 */
	public int save(DictCode dictCode);
	
	/**
	 * 更新状态值
	 * @param dictCode
	 * @return
	 */
	public int update(DictCode dictCode);
	
	public DictCode selectByPrimaryKey(Long id);
}
