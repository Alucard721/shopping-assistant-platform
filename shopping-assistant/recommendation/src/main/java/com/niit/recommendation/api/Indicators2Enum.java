package com.niit.recommendation.api;

public enum Indicators2Enum {

	xl("1","销量"),
	pll("2","评论量"),
	mspf("4","描述评分"),
	fwpf("5","服务评分"),
	wlpf("6","物流评分");

	String code;
	String text;

	Indicators2Enum(String code, String text) {
		this.code = code;
		this.text = text;
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
	 * 设置code
	 *
	 * @param code code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取text
	 *
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置text
	 *
	 * @param text text
	 */
	public void setText(String text) {
		this.text = text;
	}
}
