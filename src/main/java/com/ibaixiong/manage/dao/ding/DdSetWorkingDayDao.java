package com.ibaixiong.manage.dao.ding;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.DdSetWorkingDay;

public interface DdSetWorkingDayDao {
    int deleteByPrimaryKey(Long id);

    int insert(DdSetWorkingDay record);

    int insertSelective(DdSetWorkingDay record);

    DdSetWorkingDay selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DdSetWorkingDay record);

    int updateByPrimaryKey(DdSetWorkingDay record);
    /**
     * 查询应发数量工作日
     * @author zhaolei
     * @date 2016年2月24日
     * @param year
     * @param month
     * @return
     */
    List<DdSetWorkingDay> shouldSendCountByYearMonth(@Param("year")String year,@Param("month")String month);
    
    void deleteByYearMonth(@Param("year")String year, @Param("month")String month);
}