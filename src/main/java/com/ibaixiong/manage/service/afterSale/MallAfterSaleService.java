package com.ibaixiong.manage.service.afterSale;

import com.ibaixiong.entity.SysAdmin;

public interface MallAfterSaleService {

	/**确认退款
	 * @param serviceId          售后服务ID
	 * @param serviceItemId      售后服务详细ID，列表退款不需要提供
	 * @param num                退款数量，列表退款不需要提供
	 * @param money              退款金额，列表退款不需要提供
	 */
	int confirmRefund(Long serviceId,SysAdmin loginAdmin);
}
