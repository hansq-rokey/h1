package com.ibaixiong.manage.dao.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.BbsPermissions;

public interface BbsPermissionsDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(BbsPermissions record);

    BbsPermissions selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsPermissions record);
    
    List<BbsPermissions> getPerByFormId(Long formId);
    
    BbsPermissions getPerByFormIdAndOperateId(@Param("formId")Long formId,@Param("operateId")Long operateId);
    
    void deletePer(@Param("formId")Long formId,@Param("operateId")Long operateId);
}