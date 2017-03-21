package com.ibaixiong.manage.service.bbs;

import java.util.List;

import com.ibaixiong.entity.BbsPermissions;

public interface PermissionsService {
	List<BbsPermissions> getPerByFormId(Long formId);
	Long save(BbsPermissions per);
	Long update(BbsPermissions per);
	BbsPermissions getPerByFormIdAndOperateId(Long formId,Long operateId);
	void deletePer(Long formId,Long operateId);
}
