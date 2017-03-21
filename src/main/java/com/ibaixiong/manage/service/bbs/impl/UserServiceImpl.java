package com.ibaixiong.manage.service.bbs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.User;
import com.ibaixiong.manage.dao.bbs.UserDao;
import com.ibaixiong.manage.service.bbs.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserDao userDao;
	
	@Override
	public List<User> queryUserList(Byte status, String queryName, Long roleId,Integer pageNo) {
		PageHelper page= new PageHelper();
		page.startPage(pageNo, PageConstant.pageSize, true);
		return userDao.queryUserList(status, queryName, roleId);
	}

	@Override
	public void updateStatus(User user) {
		userDao.updateBlockStatus(user);
	}
	@Override
	public List<String> queryRoleNames(Long userId) {
		return userDao.queryRoleNames(userId);
	}
	@Override
	public User getUserByPhone(String phone) {
		List<User> userList=userDao.queryByAccount(phone);
		if(userList.size()>0){
			return userList.get(0);
		}
		return null;
	}

	@Override
	public User getUser(Long userId) {
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public void updateType(User user) {
		userDao.updateByPrimaryKeySelective(user);
	}
}
