package com.ibaixiong.manage.service.bbs.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.entity.BbsPermissions;
import com.ibaixiong.manage.dao.bbs.BbsPermissionsDao;
import com.ibaixiong.manage.service.bbs.PermissionsService;
@Service
public class PermissionsServiceImpl implements PermissionsService {
	@Resource
	private BbsPermissionsDao bbsPermissionsDao;
	@Override
	public List<BbsPermissions> getPerByFormId(Long formId) {
		return bbsPermissionsDao.getPerByFormId(formId);
	}
	@Override
	public Long save(BbsPermissions per) {
		return bbsPermissionsDao.insertSelective(per);
	}
	@Override
	public Long update(BbsPermissions per) {
		return bbsPermissionsDao.updateByPrimaryKeySelective(per);
	}
	@Override
	public BbsPermissions getPerByFormIdAndOperateId(Long formId, Long operateId) {
		return bbsPermissionsDao.getPerByFormIdAndOperateId(formId, operateId);
	}
	@Override
	public void deletePer(Long formId, Long operateId) {
		bbsPermissionsDao.deletePer(formId, operateId);
	}
}
