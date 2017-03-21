package com.ibaixiong.manage.dao.crm;

import java.util.List;
import java.util.Map;

import com.ibaixiong.entity.SsssGetcash;

public interface SsssGetcashDao {
    int deleteByPrimaryKey(Long id);

    int insert(SsssGetcash record);

    int insertSelective(SsssGetcash record);

    SsssGetcash selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SsssGetcash record);

    int updateByPrimaryKey(SsssGetcash record);
    
    List<SsssGetcash> getList(Map<String, Object> map);
}