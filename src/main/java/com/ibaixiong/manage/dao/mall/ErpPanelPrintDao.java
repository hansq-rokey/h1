package com.ibaixiong.manage.dao.mall;

import com.ibaixiong.entity.ErpPanelPrint;

public interface ErpPanelPrintDao {
    int deleteByPrimaryKey(Long id);

    int insert(ErpPanelPrint record);

    int insertSelective(ErpPanelPrint record);

    ErpPanelPrint selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ErpPanelPrint record);

    int updateByPrimaryKey(ErpPanelPrint record);
}