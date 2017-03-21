package com.ibaixiong.manage.dao.ding;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.DdSetWorkingDayLog;

public interface DdSetWorkingDayLogDao {
    int deleteByPrimaryKey(String id);

    int insert(DdSetWorkingDayLog record);

    int insertSelective(DdSetWorkingDayLog record);

    DdSetWorkingDayLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DdSetWorkingDayLog record);

    int updateByPrimaryKey(DdSetWorkingDayLog record);
    /**
     * 查询应发数量工作日
     * @author zhaolei
     * @date 2016年2月24日
     * @param year
     * @param month
     * @return
     */
    DdSetWorkingDayLog shouldSendCountByYearMonth(@Param("year")int year,@Param("month")int month);
    
    /**
     * 根据年来查询
     * @param year	年份
     * @return
     */
    List<DdSetWorkingDayLog> querysetDayLogsByYear(int year);
}