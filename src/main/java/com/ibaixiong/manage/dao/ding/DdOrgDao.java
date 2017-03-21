package com.ibaixiong.manage.dao.ding;

import java.util.List;

import com.ibaixiong.entity.DdOrg;

public interface DdOrgDao {
    int deleteByPrimaryKey(String id);

    int insert(DdOrg record);

    int insertSelective(DdOrg record);

    DdOrg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DdOrg record);

    int updateByPrimaryKey(DdOrg record);
    /**
     * 查询有效的部门
     * @param invalid
     * @return
     */
    List<DdOrg> queryInvalidOrg(boolean invalid);
}