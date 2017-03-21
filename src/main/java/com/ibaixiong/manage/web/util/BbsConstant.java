package com.ibaixiong.manage.web.util;

/**
 * 投诉单据处理状态
 * @author zhaolei
 *
 */
public class BbsConstant {
	public static enum DisplayTypeEnum {
		/**通用*/
		COMMON("common","通用"),
		PRODUCT("product","产品效果");
		
		private String key;
		
		private String value;

		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		private DisplayTypeEnum(String key, String value) {
			this.key = key;
			this.value = value;
		}
	}
}
