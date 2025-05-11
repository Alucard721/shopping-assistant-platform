package com.niit.crawler.api.VO;

import java.io.Serializable;

/**
* <pre>描述: 一级类目名和url</pre>
 * 视图对象,把某个指定页面的数据封装起来
*/
public class Category1NameUrlVO implements Serializable {

	private String category1Name;
	private String category1Url;

	/**
	 * 获取category1Name
	 *
	 * @return category1Name
	 */
	public String getCategory1Name() {
		return category1Name;
	}

	/**
	 * 设置category1Name
	 *
	 * @param category1Name category1Name
	 */
	public void setCategory1Name(String category1Name) {
		this.category1Name = category1Name;
	}

	/**
	 * 获取category1Url
	 *
	 * @return category1Url
	 */
	public String getCategory1Url() {
		return category1Url;
	}

	/**
	 * 设置category1Url
	 *
	 * @param category1Url category1Url
	 */
	public void setCategory1Url(String category1Url) {
		this.category1Url = category1Url;
	}

	public Category1NameUrlVO() {
	}

	public Category1NameUrlVO(String category1Name, String category1Url) {
		this.category1Name = category1Name;
		this.category1Url = category1Url;
	}
}
