package com.ibaixiong.manage.exception;

/**
 * 订单为空异常类
 * 
 * @author yaoweiguo
 * @Email yaoweiguo@ibaixiong.com
 * @Description TODO
 * @date 2015年11月18日
 *
 */
public class OrderNullException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2303052077054185744L;

	public OrderNullException() {
		super();
	}

	public OrderNullException(String message) {
		super(message);
	}

	public OrderNullException(Throwable cause) {
		super(cause);
	}

	public OrderNullException(String message, Throwable cause) {
		super(message, cause);
	}

}
