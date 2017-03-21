package com.ibaixiong.manage.exception;

/**
 * 价格不能为空异常类
 * 
 * @author yaoweiguo
 * @Email yaoweiguo@ibaixiong.com
 * @Description TODO
 * @date 2015年11月18日
 *
 */
public class PriceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2303052077054185744L;

	public PriceException() {
		super();
	}

	public PriceException(String message) {
		super(message);
	}

	public PriceException(Throwable cause) {
		super(cause);
	}

	public PriceException(String message, Throwable cause) {
		super(message, cause);
	}

}
