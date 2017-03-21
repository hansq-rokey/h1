package com.ibaixiong.manage.web.util;

import javax.servlet.http.HttpServletRequest;

import com.ibaixiong.entity.SysAdmin;

/**
 * @author zhaolei
 * @date 2015-7-13
 */
public class WebUtil {
	
	/**
	 * 获取登陆的用户信息
	 * @param request
	 * @return
	 */
	public static SysAdmin getLoginUser(HttpServletRequest request) {
		return (SysAdmin) request.getSession().getAttribute("admin");
	}
	
}
