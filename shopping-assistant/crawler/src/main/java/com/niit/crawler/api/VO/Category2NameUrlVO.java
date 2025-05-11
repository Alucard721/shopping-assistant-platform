package com.niit.crawler.api.VO;

import java.io.Serializable;

/**
* <pre>描述: 二级品目名称与url对应VO</pre>
*/
public class Category2NameUrlVO implements Serializable {

	private String category2Name;
	private String category2Url;

	/**
	 * 获取category2Name
	 *
	 * @return category2Name
	 */
	public String getCategory2Name() {
		return category2Name;
	}

	/**
	 * 设置category2Name
	 *
	 * @param category2Name category2Name
	 */
	public void setCategory2Name(String category2Name) {
		this.category2Name = category2Name;
	}

	/**
	 * 获取category2Url
	 *
	 * @return category2Url
	 */
	public String getCategory2Url() {
		return category2Url;
	}

	/**
	 * 设置category2Url
	 *
	 * @param category2Url category2Url
	 */
	public void setCategory2Url(String category2Url) {
		this.category2Url = category2Url;
	}

	public Category2NameUrlVO() {
	}

	public Category2NameUrlVO(String category2Name, String category2Url) {
		this.category2Name = category2Name;
		this.category2Url = category2Url;
	}
}
