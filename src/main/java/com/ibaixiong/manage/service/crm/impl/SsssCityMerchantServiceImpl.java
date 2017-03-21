package com.ibaixiong.manage.service.crm.impl;

import java.text.DecimalFormat;
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
import com.ibaixiong.entity.MerchantBondMoneyRecord;
import com.ibaixiong.entity.MerchantCouponMoneyRecord;
import com.ibaixiong.entity.MerchantFirstGoodsMoneyRecord;
import com.ibaixiong.entity.SsssCityMerchant;
import com.ibaixiong.entity.StaffRegion;
import com.ibaixiong.entity.User;
import com.ibaixiong.entity.Userinfo;
import com.ibaixiong.entity.util.BalanceChangeStatusEnum;
import com.ibaixiong.manage.dao.bbs.BbsUserRoleDao;
import com.ibaixiong.manage.dao.bbs.UserDao;
import com.ibaixiong.manage.dao.bbs.UserinfoDao;
import com.ibaixiong.manage.dao.crm.SsssCityMerchantDao;
import com.ibaixiong.manage.dao.crm.StaffRegionDao;
import com.ibaixiong.manage.service.crm.MerchantBondMoneyRecordService;
import com.ibaixiong.manage.service.crm.MerchantCouponMoneyRecordService;
import com.ibaixiong.manage.service.crm.MerchantFirstGoodsRecordService;
import com.ibaixiong.manage.service.crm.SsssCityMerchantService;
import com.papabear.pay.api.PayAccountService;
@Service
public class SsssCityMerchantServiceImpl implements SsssCityMerchantService {
	@Resource
	SsssCityMerchantDao ssssCityMerchantDao;
	@Resource
	private UserDao userDao;
	@Resource
	private UserinfoDao userinfoDao;
	@Resource
	BbsUserRoleDao bbsUserRoleDao;
	@Resource
	private StaffRegionDao staffRegionDao;
	@Resource
	PayAccountService payAccountService;
	@Resource
	MerchantBondMoneyRecordService bondRecordService;
	@Resource
	MerchantCouponMoneyRecordService couponRecordService;
	@Resource
	MerchantFirstGoodsRecordService firstGoodsRecordService;
	@Override
	public List<SsssCityMerchant> getListByPid(Long pid) {
		return ssssCityMerchantDao.getListByPid(pid);
	}
	@Override
	public void insert(SsssCityMerchant city,HttpServletRequest req) {
		city.setFirstGoodsMoneyBalance(city.getFirstGoodsMoney());
		city.setCreateDateTime(new Date());
		city.setInvalid(InvalidEnum.FALSE.getInvalidValue());
		//设置添加运营商的编码
		city.setMerchantNumber(getRandom()+"001");
		//设置编码
		city.setMerchantCode(getCode(city));
		//设置用户初始化信息
		String tel = city.getLinkTel();
		//在用户信息表中查找是否存在已经注册过信息，如果没有注册过立即完成注册，注册过进行ID绑定
		User user = new User();
		user.setUserName(tel);//设置用户名
		if(checkAccount(user)){
			city.setUserId(add(user, req,7L));
		}else{
			List<User> userList=userDao.queryByAccount(user.getUserName());
			if(userList!=null && userList.size()>0){
				city.setUserId(userList.get(0).getId());
				BbsUserRole bbsUserRole=new BbsUserRole();
				bbsUserRole.setCreateDateTime(new Date());
				bbsUserRole.setStatus(city.getStatus());
				bbsUserRole.setUpdateTime(new Date());
				bbsUserRole.setUserId(userList.get(0).getId());
				bbsUserRole.setRoleId(7L);
				bbsUserRoleDao.insertSelective(bbsUserRole);
			}
		}
		ssssCityMerchantDao.insertSelective(city);
		//保证金记录
		MerchantBondMoneyRecord bondRecord = new MerchantBondMoneyRecord();
		bondRecord.setAdminId(city.getAdminId());
		bondRecord.setMerchantId(city.getId());
		bondRecord.setMerchantLevel(city.getLevel());
		bondRecord.setBeforeBondMoney(city.getBondMoney());
		bondRecord.setAfterBondMoney(city.getBondMoney());
		bondRecord.setCreateDateTime(new Date());
		bondRecord.setStatus(BalanceChangeStatusEnum.ADDBANLANCE.getCode());
		bondRecord.setRemark("代理商账号创建");
		bondRecordService.insert(bondRecord);
		
//		//优惠券记录
//		MerchantCouponMoneyRecord couponRecord = new MerchantCouponMoneyRecord();
//		couponRecord.setAdminId(city.getAdminId());
//		couponRecord.setMerchantLevel(city.getLevel());
//		couponRecord.setMerchantId(city.getId());
//		//首次添加账号解冻优惠券
//		DecimalFormat df = new DecimalFormat("#.##");
//		float unfreezeMoney = Float.parseFloat(df.format(city.getFirstGoodsMoney()*Constant.CouponFactor.UNFREEZE.getStatus()));
//		couponRecord.setCreateDateTime(new Date());
//		couponRecord.setRemark("代理商账号创建");
//		couponRecord.setStatus(BalanceChangeStatusEnum.ADDBANLANCE.getCode());
//		if(unfreezeMoney<city.getFreezeCoupon()){
//			//冻结优惠券
//			float freezeMoney = city.getFreezeCoupon()-unfreezeMoney;
//			couponRecord.setBeforeFreezeCouponMoney(city.getFreezeCoupon());
//			couponRecord.setAfterFreezeCouponMoney(freezeMoney);
//			couponRecord.setAfterUnfreezeCouponMoney(unfreezeMoney);
//			city.setFreezeCoupon(freezeMoney);
//			city.setUnfreezeCoupon(unfreezeMoney);
//		}else{
//			couponRecord.setBeforeFreezeCouponMoney(city.getFreezeCoupon());
//			couponRecord.setAfterFreezeCouponMoney(0f);
//			couponRecord.setAfterUnfreezeCouponMoney(city.getFreezeCoupon());
//			city.setUnfreezeCoupon(city.getFreezeCoupon());
//			city.setFreezeCoupon(0f);
//		}
//		couponRecordService.insert(couponRecord);
		//优惠券记录,首次添加账号
		MerchantCouponMoneyRecord couponRecord = new MerchantCouponMoneyRecord();
		couponRecord.setAdminId(city.getAdminId());
		couponRecord.setMerchantLevel(city.getLevel());
		couponRecord.setMerchantId(city.getId());
		couponRecord.setCreateDateTime(new Date());
		couponRecord.setRemark("代理商账号创建");
		couponRecord.setStatus(BalanceChangeStatusEnum.ADDBANLANCE.getCode());
		couponRecord.setAddFreezeCouponMoney(city.getFreezeCoupon());
		couponRecord.setBeforeFreezeCouponMoney(0f);
		couponRecord.setAfterFreezeCouponMoney(city.getFreezeCoupon());
		city.setUnfreezeCoupon(0f);
		couponRecordService.insert(couponRecord);
		//首批提货款记录
		MerchantFirstGoodsMoneyRecord firstGoodsRecord = new MerchantFirstGoodsMoneyRecord();
		firstGoodsRecord.setAdminId(city.getAdminId());
		firstGoodsRecord.setMerchantId(city.getId());
		firstGoodsRecord.setMerchantLevel(city.getLevel());
		firstGoodsRecord.setRemark("代理商账号创建");
		firstGoodsRecord.setStatus(BalanceChangeStatusEnum.ADDBANLANCE.getCode());
		firstGoodsRecord.setType(BalanceChangeStatusEnum.ADDBANLANCE.getCode());
		firstGoodsRecord.setMoney(city.getFirstGoodsMoney());
		firstGoodsRecord.setCreateDateTime(new Date());
		firstGoodsRecord.setFirstGoodsMoneyBalanceAfter(city.getFirstGoodsMoney());
		firstGoodsRecordService.insertSelective(firstGoodsRecord);
		ssssCityMerchantDao.updateByPrimaryKey(city);
		
		payAccountService.createPayAccount(user.getId());
	}
	private String getNumber(Long id){
		//查询同一个父节点下的ID最大的那个，也就是最后插入的那个
		SsssCityMerchant city = ssssCityMerchantDao.getMaxIdEntityByPid(id);
		if(city == null){//说明还没有一个子集直接初始化返回
			SsssCityMerchant parentCity = ssssCityMerchantDao.selectByPrimaryKey(id);
			return parentCity.getMerchantNumber()+"001";
		}else{
			//计算获取下一个编码
			return (Integer.parseInt(city.getMerchantNumber())+1)+"";
		}
	}
	private String getCode(SsssCityMerchant city){
		String str = "";
		//获取省份编码
		StaffRegion region = staffRegionDao.selectByPrimaryKey(Long.parseLong(city.getProvinceCode()));
		String tag = "";
		if(city.getParentCityMerchantId()== null){//说明是省代
			tag = "-A-";
		}else{
			tag = "-B-";
		}
		str = region.getCode()+tag+getRandom();
		SsssCityMerchant  cityM =  ssssCityMerchantDao.getByCode(str);//查询该编码是否唯一
		if(cityM == null)
			return str;
		else
			return getCode(city);
	}
	private int getRandom(){
		int i = (int)(Math.random() * 1000);
		return i;
	}
	private Long add(User user,HttpServletRequest req,Long roleId){
		Random random=new Random();		
		String salt=String.valueOf(random.nextInt(10000));
		if(AccountUtil.isEmail(user.getUserName())){
			user.setEmail(user.getUserName());
		}
		if(AccountUtil.isMobile(user.getUserName())){
			user.setPhone(user.getUserName());
		}
		user.setSalt(salt);//随机码
		user.setUserPwd("123456");
		String password=Md5Util.encode(Md5Util.encode(user.getUserPwd()+salt));
		user.setUserPwd(password);
		user.setCreateDateTime(new Date());
		user.setUpdateTime(new Date());
		user.setExpNum(0);
		user.setStatus(Constant.Status.NORMAL.getStatus());
		user.setPoints(0);
		Grade gradeEntitiy=new Grade();
		gradeEntitiy.setId(1l);
		user.setGradeEntity(gradeEntitiy);
		user.setAvailable(Constant.Status.NORMAL.getStatus());
		user.setAvatarImg(createRandomAvatarImg());
//		user.setBxNum("1111111");
		int flag =userDao.insert(user);
		user.setBxNum(createRandomBxNum(user.getId()));
		userDao.updateByPrimaryKeySelective(user);
		Userinfo userinfo=new Userinfo();
		userinfo.setLastIp(RequestUtils.getIpAddr(req));
		userinfo.setCreateDateTime(new Date());
		userinfo.setUserId(user.getId());
		userinfo.setStatus(Constant.Status.NORMAL.getStatus());
		userinfoDao.insert(userinfo);
		
		BbsUserRole bbsUserRole=new BbsUserRole();
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
	 * @return
	 */
	private String createRandomAvatarImg() {
		Random random = new Random();
		return String.format("http://baixiongbasicimage.oss-cn-hangzhou.aliyuncs.com/default_user_image/%s.png", random.nextInt(4) + 1);
	}
	/**
	 * 查找返回是否存可以使用该用户名
	 * @author zhaolei
	 * @date 2015年12月29日
	 * @param user
	 * @return true 可以使用 false 说明已存在
	 */
	public boolean checkAccount(User user){
		List<User> userList=userDao.queryByAccount(user.getUserName());
		if(userList.size()>0){
			return false;
		}
		return true;
	}
	/**
	 * 随机生成白熊号
	 * 规则：id+2位随机码
	 * @param id
	 * @return
	 */
	private String createRandomBxNum(Long id){
		Random random=new Random();
		String num=String.valueOf(id)+String.valueOf(random.nextInt(90)+10);
		return num;
	}
	@Override
	public void update(SsssCityMerchant city) {
		ssssCityMerchantDao.updateByPrimaryKeySelective(city);
		User user = userDao.selectByPrimaryKey(city.getUserId());
		user.setUserName(city.getLinkTel());
		userDao.updateByPrimaryKeySelective(user);
	}
	
	@Override
	public SsssCityMerchant get(Long id) {
		return ssssCityMerchantDao.selectByPrimaryKey(id);
	}
	
	@Override
	public SsssCityMerchant getByUserId(Long userId) {
		return ssssCityMerchantDao.getByUserId(userId);
	}
	@Override
	public List<SsssCityMerchant> getList(Integer pageNo) {
		PageHelper page = new PageHelper();
		page.startPage(pageNo, PageConstant.bbspageSize, true);
		return ssssCityMerchantDao.getList();
	}
	@Override
	public List<SsssCityMerchant> getCitys() {
		return ssssCityMerchantDao.getList();
	}
	@Override
	public SsssCityMerchant getByLinkTel(String linkTel) {
		return ssssCityMerchantDao.getByLinkTel(linkTel);
	}
	@Override
	public List<SsssCityMerchant> getListByStatus(Integer pageNo) {
		PageHelper.startPage(pageNo, PageConstant.bbspageSize, true);
		return ssssCityMerchantDao.getListByStatus();
	}
	@Override
	public SsssCityMerchant getById(Long id) {
		return ssssCityMerchantDao.getById(id);
	}
	@Override
	public List<SsssCityMerchant> selectList(String keywords,Byte typeValue,Integer pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keywords", keywords);
		map.put("typeValue", typeValue);
		PageHelper page = new PageHelper();
		page.startPage(pageNo,PageConstant.bbspageSize, true);
		return ssssCityMerchantDao.selectList(map);
	}
	@Override
	public void updateCityMerchant(SsssCityMerchant cityMerchant) {
		ssssCityMerchantDao.updateByPrimaryKeySelective(cityMerchant);
	}
	@Override
	public List<SsssCityMerchant> queryMerchantByParentId(Long parentId,Integer pageNo) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pageNo, PageConstant.bbspageSize, true);
		return ssssCityMerchantDao.queryMerchantByParentId(parentId);
	}
}
