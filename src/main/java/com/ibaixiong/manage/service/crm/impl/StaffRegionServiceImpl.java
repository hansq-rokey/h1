package com.ibaixiong.manage.service.crm.impl;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.InvalidEnum;
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
import com.ibaixiong.manage.service.crm.StaffRegionService;
import com.papabear.pay.api.PayAccountService;

@Service
public class StaffRegionServiceImpl implements StaffRegionService {
	@Resource
	private StaffRegionDao staffRegionDao;

	@Override
	public StaffRegion selectByPrimaryKey(Long id) {
		return staffRegionDao.selectByPrimaryKey(id);
	}
	
}
