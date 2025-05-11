package com.niit.crawler.api.PO;

public class PricePO {

	// 时间戳
	private String date;

	// 价格
	private String price;

	/**
	 * 获取date
	 *
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 设置date
	 *
	 * @param date date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 获取price
	 *
	 * @return price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * 设置price
	 *
	 * @param price price
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	public PricePO(String date, String price) {
		this.date = date;
		this.price = price;
	}


}
