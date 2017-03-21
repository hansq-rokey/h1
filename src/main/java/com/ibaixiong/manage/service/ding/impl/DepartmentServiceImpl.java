package com.ibaixiong.manage.service.ding.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.ibaixiong.constant.Constant.Status;
import com.ibaixiong.constant.InvalidEnum;
import com.ibaixiong.entity.DdOrg;
import com.ibaixiong.entity.DdOrgUser;
import com.ibaixiong.entity.DdUser;
import com.ibaixiong.manage.dao.ding.DdAdminUserDao;
import com.ibaixiong.manage.dao.ding.DdOrgDao;
import com.ibaixiong.manage.dao.ding.DdOrgUserDao;
import com.ibaixiong.manage.dao.ding.DdUserDao;
import com.ibaixiong.manage.exception.OApiException;
import com.ibaixiong.manage.service.ding.DepartmentService;
import com.ibaixiong.manage.util.DingConstant;
import com.ibaixiong.manage.util.HttpHelper;
import com.ibaixiong.manage.util.ding.entity.Department;
import com.ibaixiong.manage.util.ding.entity.User;
import com.ibaixiong.manage.util.ding.helper.DepartmentHelper;
import com.ibaixiong.manage.util.ding.helper.UserHelper;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Resource
	private DdOrgDao orgDao;
	@Resource
	private DdUserDao ddUserDao;
	@Resource
	private DdOrgUserDao ddOrgUserDao;
	
	private DdAdminUserDao ddAdminUserDao;

	/**
	 * 同步部门
	 */
	@Override
	@Transactional
	public int syncDepartment() throws OApiException {
		JSONObject json = HttpHelper.httpGet(DingConstant.GET_ACCESS_TOKEN);
		String token = json.getString("token");

		List<DdOrg> orgList = orgDao.queryInvalidOrg(InvalidEnum.FALSE.getInvalidValue());
		List<DdUser> userList = ddUserDao.queryInvalidUsers(InvalidEnum.FALSE.getInvalidValue());
		Map<String, DdOrg> map = new HashMap<String, DdOrg>();
		Map<String, DdUser> userMap = new HashMap<String, DdUser>();
		for (DdOrg org : orgList) {
			map.put(org.getId(), org);
		}
		for (DdUser user : userList) {
			userMap.put(user.getId(), user);
		}
		List<Department> list = DepartmentHelper.listDepartments(token);
		Map<String, User> userAllMap = new HashMap<String, User>();
		for (Department d : list) {
			if (StringUtils.isBlank(d.id) || StringUtils.isBlank(d.name) || StringUtils.isBlank(d.parentid))
				continue;
			String id = d.id;
			// 更新
//			System.out.println("部门ID:"+id+"是否有值="+map.containsKey(id));
			if (map.containsKey(id)) {
				DdOrg org = map.get(id);
				org.setUpdateTime(new Date());
				org.setOrgName(d.name);
				orgDao.updateByPrimaryKeySelective(org);
				map.remove(id);
			} else {// 新增
				DdOrg org = new DdOrg();
				org.setCreateDateTime(new Date());
				org.setId(id);
				org.setInvalid(InvalidEnum.FALSE.getInvalidValue());
				org.setOrgName(d.name);
				org.setStatus(Status.NORMAL.getStatus());
				org.setUpdateTime(new Date());
				orgDao.insert(org);
			}
			userAllMap = this.addMap(id, token, userAllMap);
		}
		this.syncUser(userMap, userAllMap);
		// 删除不用的部门
		Set<String> set = map.keySet();
		if (set.size() > 0) {
			for (String key : set) {
				DdOrg org = map.get(key);
				org.setInvalid(InvalidEnum.TRUE.getInvalidValue());
				org.setUpdateTime(new Date());
				orgDao.updateByPrimaryKey(org);
				ddOrgUserDao.deleteByDepartmentId(key);
			}
		}

		// 删除不用的人员
		if (userMap.size() > 0) {
			Set<String> keys = userMap.keySet();
			for (String key : keys) {
				DdUser user = userMap.get(key);
				user.setUpdateTime(new Date());
				user.setInvalid(InvalidEnum.TRUE.getInvalidValue());
				ddUserDao.updateByPrimaryKey(user);
				ddOrgUserDao.deleteByUserId(user.getId());
				
				//删除管理下属及被管理权限
				ddAdminUserDao.deleteByAdminId(user.getId());
				ddAdminUserDao.deleteByUserId(user.getId());
			}
		}
		return 0;
	}

	public Map<String, User> addMap(String departmentId, String accessToken, Map<String, User> userAllMap) throws OApiException {
		List<User> users = UserHelper.getUserDetails(accessToken, departmentId);
		List<DdOrgUser> ddOrgUsers = ddOrgUserDao.queryOrgUsersByDepartmentId(departmentId);
		Map<String, DdOrgUser> map = new HashMap<String, DdOrgUser>();
		for (DdOrgUser ou : ddOrgUsers) {
			map.put(ou.getUserId(), ou);
		}

		for (User dingUser : users) {
			userAllMap.put(dingUser.userid, dingUser);
			map.remove(dingUser.userid);
			this.saveOrgUserRelation(departmentId, dingUser.userid);
		}
		if (map.size() > 0) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				DdOrgUser ddOrgUser = map.get(key);
				ddOrgUserDao.deleteByPrimaryKey(ddOrgUser.getId());
			}
		}

		return userAllMap;
	}

	/**
	 * 同步用户
	 * 
	 * @param departmentId
	 *            部门ID
	 * @param accessToken
	 *            凭证
	 * @throws NumberFormatException
	 * @throws OApiException
	 */
	private Map<String, DdUser> syncUser(Map<String, DdUser> userMap, Map<String, User> userAllMap) {

		Iterator<Entry<String, User>> it = userAllMap.entrySet().iterator();
		while (it.hasNext()) {
			User dingUser = it.next().getValue();
			String userId = dingUser.userid;
			if (userMap.containsKey(userId)) {
				DdUser user = userMap.get(userId);
				user.setUpdateTime(new Date());
				user.setUserName(dingUser.name);
				user.setAvatar(dingUser.avatar);
				user.setPosition(dingUser.position);
				user.setMobile(dingUser.mobile);
				ddUserDao.updateByPrimaryKeySelective(user);
				// 移除已有的对象
				userMap.remove(userId);
			} else {
				DdUser user = new DdUser();
				user.setAvatar(dingUser.avatar);
				user.setCreateDateTime(new Date());
				user.setId(userId);
				user.setInvalid(InvalidEnum.FALSE.getInvalidValue());
				user.setStatus(Status.NORMAL.getStatus());
				user.setUpdateTime(new Date());
				user.setUserName(dingUser.name);
				user.setPosition(dingUser.position);
				user.setMobile(dingUser.mobile);
				ddUserDao.insert(user);
			}
		}
		return userMap;
	}

	/**
	 * 维护部门与人员的关系
	 * 
	 * @param departmentId
	 * @param userId
	 */
	private void saveOrgUserRelation(String departmentId, String userId) {
		DdOrgUser ddOrgUser = ddOrgUserDao.getByDepartmentIdAndUserId(departmentId, userId);
		if (ddOrgUser == null) {
			DdOrgUser orgUser = new DdOrgUser();
			orgUser.setCreateDateTime(new Date());
			orgUser.setOrgId(departmentId);
			orgUser.setUserId(userId);
			orgUser.setInvalid(InvalidEnum.FALSE.getInvalidValue());
			orgUser.setStatus(Status.NORMAL.getStatus());
			orgUser.setUpdateTime(new Date());
			ddOrgUserDao.insert(orgUser);
		}

	}

	@Override
	public List<DdOrg> queryInvalidDepartment(InvalidEnum invalidEnum) {

		return orgDao.queryInvalidOrg(invalidEnum.getInvalidValue());
	}

}
