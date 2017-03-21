package com.ibaixiong.manage.service.ding;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.DdWorkLog;

public interface DdWorkLogService {
	/**
	 * 查询自己的
	 * @author zhaolei
	 * @date 2016年1月26日
	 * @param userId
	 * @param pageNo
	 * @return
	 */
	List<DdWorkLog> getOwnSendList(String userId,Integer pageNo);
	/**
	 * 查询别人推送给自己的
	 * @author zhaolei
	 * @date 2016年1月26日
	 * @param userId
	 * @param pageNo
	 * @return
	 */
	List<DdWorkLog> getOtherPushSendList(String userId,Integer pageNo);
	/**
	 * 查询今天发布的日志
	 * @author zhaolei
	 * @date 2016年1月27日
	 * @param map
	 * @return
	 */
	DdWorkLog getTodayLog(Map<String, Object> map);
	
	void insert(DdWorkLog log);
	
	DdWorkLog get(Long id);
	
	void update(DdWorkLog log);
	/**
	 * 查询日报按照年月
	 * @author zhaolei
	 * @date 2016年2月24日
	 * @param userId
	 * @param type
	 * @return
	 */
	List<DdWorkLog> getListByQueryTypeAndYM(String userId,int type,String year,String month);
}
