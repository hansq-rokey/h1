package com.ibaixiong.manage.service.crm;

import java.util.Date;
import java.util.List;

import com.ibaixiong.entity.SsssInvitationCode;

public interface SsssInvitationCodeService {

	/**
	 * 
	 * @param phone
	 * @param idList
	 * @return
	 */
	List<SsssInvitationCode> queryInviteCodeByMobileAndProductId(String phone,List<Long> idList);
	/**
	 * 重置邀请码
	 * @author zhaolei
	 * @date 2016年1月21日
	 * @param date
	 */
	void reset(Date date);
}
