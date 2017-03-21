package com.ibaixiong.manage.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.SysLog;

public interface SysLogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
    
    List<SysLog> querySysLogList(@Param("queryName") String queryName,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("adminId") Long adminId);
}