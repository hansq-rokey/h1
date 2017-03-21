package com.ibaixiong.manage.service.smart.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.core.web.SmsUtils;
import com.ibaixiong.entity.SmartDateError;
import com.ibaixiong.manage.dao.base.DictCodeDao;
import com.ibaixiong.manage.dao.smart.SmartDateErrorDao;
import com.ibaixiong.manage.service.base.mode.DictCode;
import com.ibaixiong.manage.service.smart.SmartDateErrorService;
import com.ibaixiong.manage.util.DictTypeEnum;

@Service
public class SmartDateErrorServiceImpl implements SmartDateErrorService {

	@Resource
	SmartDateErrorDao smartDateErrorDao;
	@Autowired
	DictCodeDao dictCodeDao;
	@Autowired
	SmsUtils smsUtils;
	@Override
	public List<SmartDateError> queryAllSmartDateErrors(int pageNo) {
		
		PageHelper.startPage(pageNo, 10, "id desc");
		return smartDateErrorDao.queryDateErrors(null);
	}

	@Override
	public List<SmartDateError> querySendedSmartDateErrors(int pageNo) {
		PageHelper.startPage(pageNo, 10, "id desc");
		return smartDateErrorDao.queryDateErrors((byte)2);
	}

	@Override
	public List<SmartDateError> queryUnhandleSmartDateErrors(int pageNo) {
		PageHelper.startPage(pageNo, 10, "id desc");
		return smartDateErrorDao.queryDateErrors((byte)1);
	}

	@Override
	public int updateSmartDateError(SmartDateError error) {
		
		return smartDateErrorDao.updateByPrimaryKeySelective(error);
	}

	@Override
	public List<SmartDateError> querySmartDateErrors(Byte status, int pageNo) {
		PageHelper.startPage(pageNo, 10, "id desc");
		return smartDateErrorDao.queryDateErrors(status);
	}

	@Override
	public int sendSmartDateError() {
		List<SmartDateError> list=smartDateErrorDao.queryDateErrors((byte)1);
		if(list.size()>0){
			System.out.println("进来发送短信了");
			List<DictCode> dictCodes= dictCodeDao.queryDictCodeByDictType(DictTypeEnum.SMART_ERROR_SENDED_CONTACTS.getDictType());
			for(DictCode dc:dictCodes){
				smsUtils.sendOnce(dc.getDictCodeValue(), "温控器时间戳有"+list.size()+"个出现问题，请登录CMS系统进行处理！");
			}
			for(SmartDateError sde:list){
				sde.setStatus((byte)2);
				this.updateSmartDateError(sde);
			}
		}
		return 0;
	}

}
