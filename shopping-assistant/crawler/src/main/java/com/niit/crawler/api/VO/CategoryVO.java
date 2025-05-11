package com.niit.crawler.api.VO;

import java.util.List;

/**
* <pre>描述: 全品目VO</pre>
*/
public class CategoryVO {

	private List<Category1ToCategory2VO> category2VOList;

	/**
	 * 获取category2VOList
	 *
	 * @return category2VOList
	 */
	public List<Category1ToCategory2VO> getCategory2VOList() {
		return category2VOList;
	}

	/**
	 * 设置category2VOList
	 *
	 * @param category2VOList category2VOList
	 */
	public void setCategory2VOList(List<Category1ToCategory2VO> category2VOList) {
		this.category2VOList = category2VOList;
	}

	public CategoryVO() {
	}

	public CategoryVO(List<Category1ToCategory2VO> category2VOList) {
		this.category2VOList = category2VOList;
	}
}
