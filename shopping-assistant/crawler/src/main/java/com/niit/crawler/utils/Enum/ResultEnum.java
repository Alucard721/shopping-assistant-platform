package com.niit.crawler.utils.Enum;

public enum ResultEnum {

	SUCCESS("0","返回成功"),
	FAIL("1","返回失败");

	private  String code;
	private  String message;

	ResultEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * 获取code
	 *
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取message
	 *
	 * @return message
	 */
	public String getMessage() {
		return message;
	}
}
