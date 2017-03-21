package com.ibaixiong.manage.dao.ding;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.DdWorkLog;

public interface DdWorkLogDao {
    int deleteByPrimaryKey(Long id);

    int insert(DdWorkLog record);

    int insertSelective(DdWorkLog record);

    DdWorkLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DdWorkLog record);

    int updateByPrimaryKey(DdWorkLog record);
    /**
     * 查询相关日志列表按照分类分为查询自己的，查询别人推送给自己的两种
     * @author zhaolei
     * @date 2016年1月26日
     * @param userId
     * @param type 1：查询自己的2：查询别人推送给自己的
     * @return
     */
    List<DdWorkLog> getListByQueryType(@Param("userId") String userId,@Param("type")int type);
    
    DdWorkLog getTodayLog(Map<String, Object> map);
    
    List<DdWorkLog> getListByQueryTypeAndYM(@Param("userId")String userId,@Param("type")int type,@Param("year")String year,@Param("month")String month);
}