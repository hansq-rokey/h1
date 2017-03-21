package com.ibaixiong.manage.dao.ccm;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.CcmProcess;

public interface CcmProcessDao {
    void deleteByPrimaryKey(Long id);

    Long insertSelective(CcmProcess record);

    CcmProcess selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(CcmProcess record);

    List<CcmProcess> queryProcessByAdminId(Long adminId);
    
    List<CcmProcess> queryProcessByQuestionId(Long questionId);
    
    CcmProcess getProcessByQidAndLid(@Param("questionId") Long questionId,@Param("adminId") Long adminId);
}