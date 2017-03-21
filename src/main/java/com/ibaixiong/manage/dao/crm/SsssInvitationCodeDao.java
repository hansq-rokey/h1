package com.ibaixiong.manage.dao.crm;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.SsssInvitationCode;

public interface SsssInvitationCodeDao {
	int deleteByPrimaryKey(Long id);

	int insert(SsssInvitationCode record);

	int insertSelective(SsssInvitationCode record);

	SsssInvitationCode selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SsssInvitationCode record);

	int updateByPrimaryKey(SsssInvitationCode record);

	/**
	 * 
	 * @param map
	 * @return
	 */
	List<SsssInvitationCode> queryInviteCodeByMobileAndProductId(Map<String, Object> map);

	/**
	 * 
	 * @param productId
	 *            产品ID
	 * @param status
	 *            状态
	 * @param id
	 *            邀请码ID
	 * @param phone
	 *            手机号码
	 * @return
	 */
	SsssInvitationCode getInviteCodeByMobileAndProductId(@Param("productId") Long productId, @Param("status") Byte status, @Param("id") Long id,
			@Param("phone") String phone);

	SsssInvitationCode getSsssInvitationCode(Map<String, Object> map);
	
	List<SsssInvitationCode> getListByOrderNumber(@Param("orderNumber") String orderNumber);
	/**
	 * 根据item的Id查询是否在退款时使用了邀请码
	 * @author zhaolei
	 * @date 2016年1月8日
	 * @param item
	 * @return
	 */
	List<SsssInvitationCode> getListByOrderItemId(List<Long> item);
	/**
	 * 修改邀请码使用状态
	 * @author zhaolei
	 * @date 2016年1月21日
	 * @param record
	 * @return
	 */
	int reset(SsssInvitationCode record);
	/**
	 * 返回符合条件需要重置的邀请码
	 * @author zhaolei
	 * @date 2016年1月21日
	 * @param date
	 * @return
	 */
	List<SsssInvitationCode> getListByReset(Date date);
}