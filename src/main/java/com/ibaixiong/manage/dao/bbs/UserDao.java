package com.ibaixiong.manage.dao.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.User;

public interface UserDao {
    List<User> queryUserList(@Param("status") Byte status, @Param("queryName")  String queryName,@Param("roleId")  Long roleId);
    void updateBlockStatus(User user);
    User selectByPrimaryKey(Long id);
    /**
     * 根据用户id查询角色名称
     * @param userId
     * @return
     */
    List<String> queryRoleNames(Long userId);
    
    int insert(User record);
    
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据账号查询记录
     * @param account
     * @return
     */
    List<User> queryByAccount(String account);
}