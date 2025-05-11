package com.niit.crawler.api.VO;

import java.io.Serializable;
import java.util.List;

/**
* <pre>描述: 一级类目与二级类目对应VO</pre>
*/
public class Category1ToCategory2VO implements Serializable {

	private String category1Name;
	private List<Category2NameUrlVO> category2NameUrlVOS;

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
	 * 获取category2NameUrlVOS
	 *
	 * @return category2NameUrlVOS
	 */
	public List<Category2NameUrlVO> getCategory2NameUrlVOS() {
		return category2NameUrlVOS;
	}

	/**
	 * 设置category2NameUrlVOS
	 *
	 * @param category2NameUrlVOS category2NameUrlVOS
	 */
	public void setCategory2NameUrlVOS(List<Category2NameUrlVO> category2NameUrlVOS) {
		this.category2NameUrlVOS = category2NameUrlVOS;
	}

	public Category1ToCategory2VO() {
	}

	public Category1ToCategory2VO(String category1Name, List<Category2NameUrlVO> category2NameUrlVOS) {
		this.category1Name = category1Name;
		this.category2NameUrlVOS = category2NameUrlVOS;
	}
}
