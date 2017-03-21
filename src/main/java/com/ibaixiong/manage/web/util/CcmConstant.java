package com.ibaixiong.manage.web.util;

/**
 * 投诉单据处理状态
 * @author zhaolei
 *
 */
public class CcmConstant {
	public static enum Status {
		START {
			/**
			 * 待处理
			 */
			@Override
			public Byte getStatus() {
				return 1;
			}
		},
		UNDERWAR {
			/**
			 * 处理中
			 */
			@Override
			public Byte getStatus() {
				return 2;
			}
		},
		END {
			/**
			 * 已处理
			 */
			@Override
			public Byte getStatus() {
				return 3;
			}
		},
		CLOSE {
			/**
			 * 已关闭
			 */
			@Override
			public Byte getStatus() {
				return 4;
			}
		},;
		public abstract Byte getStatus();
	}
}
