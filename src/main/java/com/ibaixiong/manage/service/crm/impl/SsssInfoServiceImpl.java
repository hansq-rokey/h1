package com.ibaixiong.manage.service.crm.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.InvalidEnum;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.core.utils.AccountUtil;
import com.ibaixiong.core.utils.Md5Util;
import com.ibaixiong.core.web.RequestUtils;
import com.ibaixiong.entity.BbsUserRole;
import com.ibaixiong.entity.Grade;
import com.ibaixiong.entity.SsssCityMerchant;
import com.ibaixiong.entity.SsssInfo;
import com.ibaixiong.entity.StaffRegion;
import com.ibaixiong.entity.User;
import com.ibaixiong.entity.Userinfo;
import com.ibaixiong.manage.dao.bbs.BbsUserRoleDao;
import com.ibaixiong.manage.dao.bbs.UserDao;
import com.ibaixiong.manage.dao.bbs.UserinfoDao;
import com.ibaixiong.manage.dao.crm.SsssCityMerchantDao;
import com.ibaixiong.manage.dao.crm.SsssInfoDao;
import com.ibaixiong.manage.dao.crm.StaffRegionDao;
import com.ibaixiong.manage.service.crm.SsssInfoService;
import com.papabear.pay.api.PayAccountService;

@Service
public class SsssInfoServiceImpl implements SsssInfoService {
	@Resource
	SsssInfoDao ssssInfoDao;
	@Resource
	SsssCityMerchantDao ssssCityMerchantDao;
	@Resource
	private UserDao userDao;
	@Resource
	private UserinfoDao userinfoDao;
	@Resource
	private BbsUserRoleDao bbsUserRoleDao;
	@Resource
	private StaffRegionDao staffRegionDao;
	@Resource
	PayAccountService payAccountService;

	@Override
	public List<SsssInfo> getListByPid(Long pid,String linkTel,Date startTime,Date endTime,Integer pageNo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pid", pid);
		map.put("linkTel", linkTel);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNo, PageConstant.bbspageSize, true);
		return ssssInfoDao.getListByPid(map);
	}

	@Override
	public void insert(SsssInfo city, HttpServletRequest req) {
		city.setCreateDateTime(new Date());
		city.setInvalid(InvalidEnum.FALSE.getInvalidValue());
		// 设置添加4s的编码
		city.setSsssNumber(getNumber(city.getCityMerchantId()));
		// 设置门店号
		city.setSsssCode(getCode(city));
		// 设置用户初始化信息
		String tel = city.getLinkTel();
		// 在用户信息表中查找是否存在已经注册过信息，如果没有注册过立即完成注册，注册过进行ID绑定
		User user = new User();
		user.setUserName(tel);// 设置用户名
		if (checkAccount(user)) {
			city.setUserId(add(user, req,6L));
		} else {
			List<User> userList = userDao.queryByAccount(user.getUserName());
			if (userList != null && userList.size() > 0) {
				city.setUserId(userList.get(0).getId());
				BbsUserRole bbsUserRole = new BbsUserRole();
				bbsUserRole.setCreateDateTime(new Date());
				bbsUserRole.setStatus(city.getStatus());
				bbsUserRole.setUpdateTime(new Date());
				bbsUserRole.setUserId(userList.get(0).getId());
				bbsUserRole.setRoleId(6L);
				bbsUserRoleDao.insertSelective(bbsUserRole);
			}
		}
		ssssInfoDao.insertSelective(city);
		payAccountService.createPayAccount(user.getId());
	}

	private String getNumber(Long id) {
		// 查询同一个父节点下的ID最大的那个，也就是最后插入的那个
		SsssInfo city = ssssInfoDao.getMaxIdEntityByPid(id);
		if (city == null) {// 说明还没有一个子集直接初始化返回
			SsssCityMerchant parentCity = ssssCityMerchantDao.selectByPrimaryKey(id);
			return parentCity.getMerchantNumber() + "001";
		} else {
			// 计算获取下一个编码
			return (Integer.parseInt(city.getSsssNumber()) + 1) + "";
		}
	}

	private String getCode(SsssInfo city) {
		String str = "";
		// 获取省份编码
		StaffRegion region = staffRegionDao.selectByPrimaryKey(Long.parseLong(city.getProvinceCode()));
		str = region.getCode() + "-C-" + getRandom();
		SsssInfo cityM = ssssInfoDao.getByCode(str);// 查询该编码是否唯一
		if (cityM == null)
			return str;
		else
			return getCode(city);
	}

	private int getRandom() {
		int i = (int) (Math.random() * 1000);
		return i;
	}

	private Long add(User user, HttpServletRequest req,Long roleId) {
		Random random = new Random();
		String salt = String.valueOf(random.nextInt(10000));
		if (AccountUtil.isEmail(user.getUserName())) {
			user.setEmail(user.getUserName());
		}
		if (AccountUtil.isMobile(user.getUserName())) {
			user.setPhone(user.getUserName());
		}
		user.setSalt(salt);
		user.setUserPwd("123456");
		String password = Md5Util.encode(Md5Util.encode(user.getUserPwd() + salt));
		user.setUserPwd(password);
		user.setCreateDateTime(new Date());
		user.setUpdateTime(new Date());
		user.setExpNum(0);
		user.setStatus(Constant.Status.NORMAL.getStatus());
		user.setPoints(0);
		Grade gradeEntitiy = new Grade();
		gradeEntitiy.setId(1l);
		user.setGradeEntity(gradeEntitiy);
		user.setAvailable(Constant.Status.NORMAL.getStatus());
		user.setAvatarImg(createRandomAvatarImg());
		// user.setBxNum("1111111");
		userDao.insert(user);
		user.setBxNum(createRandomBxNum(user.getId()));
		userDao.updateByPrimaryKeySelective(user);
		Userinfo userinfo = new Userinfo();
		userinfo.setLastIp(RequestUtils.getIpAddr(req));
		userinfo.setCreateDateTime(new Date());
		userinfo.setUserId(user.getId());
		userinfo.setStatus(Constant.Status.NORMAL.getStatus());
		userinfoDao.insert(userinfo);

		BbsUserRole bbsUserRole = new BbsUserRole();
		bbsUserRole.setCreateDateTime(new Date());
		bbsUserRole.setStatus(Constant.Status.NORMAL.getStatus());
		bbsUserRole.setUpdateTime(new Date());
		bbsUserRole.setUserId(user.getId());
		bbsUserRole.setRoleId(roleId);
		bbsUserRoleDao.insertSelective(bbsUserRole);
		return user.getId();
	}

	/**
	 * 随机生成一个头像
	 * 
	 * @return
	 */
	private String createRandomAvatarImg() {
		Random random = new Random();
		return String.format("http://baixiongbasicimage.oss-cn-hangzhou.aliyuncs.com/default_user_image/%s.png", random.nextInt(4) + 1);
	}

	/**
	 * 查找返回是否存可以使用该用户名
	 * 
	 * @author zhaolei
	 * @date 2015年12月29日
	 * @param user
	 * @return true 可以使用 false 说明已存在
	 */
	public boolean checkAccount(User user) {
		List<User> userList = userDao.queryByAccount(user.getUserName());
		if (userList.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 随机生成白熊号 规则：id+2位随机码
	 * 
	 * @param id
	 * @return
	 */
	private String createRandomBxNum(Long id) {
		Random random = new Random();
		String num = String.valueOf(id) + String.valueOf(random.nextInt(90) + 10);
		return num;
	}

	@Override
	public void update(SsssInfo city) {
		ssssInfoDao.updateByPrimaryKeySelective(city);
	}

	@Override
	public SsssInfo get(Long id) {
		return ssssInfoDao.selectByPrimaryKey(id);
	}

	@Override
	public SsssInfo getByUserId(Long userId) {
		return ssssInfoDao.getByUserId(userId);
	}

	@Override
	public SsssInfo getById(Long id) {
		return ssssInfoDao.getById(id);
	}

	@Override
	public List<SsssInfo> getStopListByPid(Long pid,Integer pageNo) {
		PageHelper.startPage(pageNo, PageConstant.bbspageSize, true);
		return ssssInfoDao.getStopListByPid(pid);
	}
}
