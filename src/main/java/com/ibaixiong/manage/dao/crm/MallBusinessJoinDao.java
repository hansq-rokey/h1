package com.ibaixiong.manage.dao.crm;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.MallBusinessJoin;

public interface MallBusinessJoinDao {
    int deleteByPrimaryKey(Long id);

    int insert(MallBusinessJoin record);

    int insertSelective(MallBusinessJoin record);

    MallBusinessJoin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MallBusinessJoin record);

    int updateByPrimaryKey(MallBusinessJoin record);
    
    List<MallBusinessJoin> queryBusinessJoins(@Param("status")Byte status);

	List<MallBusinessJoin> queryBusinessList(Map<String, Object> map);
}