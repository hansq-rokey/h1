package com.ibaixiong.manage.exception;

/**
 * 数量提交异常类  数量为空或负数
 * 
 * @author yaoweiguo
 * @Email yaoweiguo@ibaixiong.com
 * @Description TODO
 * @date 2015年11月18日
 *
 */
public class NumException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2303052077054185744L;

	public NumException() {
		super();
	}

	public NumException(String message) {
		super(message);
	}

	public NumException(Throwable cause) {
		super(cause);
	}

	public NumException(String message, Throwable cause) {
		super(message, cause);
	}

}
