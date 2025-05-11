package com.niit.search.api.DTO;

import java.io.Serializable;

public class SearchRequestDTO implements Serializable {

	private String key;

	private String pageNum;

	private String pageSize;

	//商场编号
	private String mallFilter;

	/**
	 * 获取key
	 *
	 * @return key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置key
	 *
	 * @param key key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 获取pageNum
	 *
	 * @return pageNum
	 */
	public String getPageNum() {
		return pageNum;
	}

	/**
	 * 设置pageNum
	 *
	 * @param pageNum pageNum
	 */
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * 获取pageSize
	 *
	 * @return pageSize
	 */
	public String getPageSize() {
		return pageSize;
	}

	/**
	 * 设置pageSize
	 *
	 * @param pageSize pageSize
	 */
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取mallFilter
	 *
	 * @return mallFilter
	 */
	public String getMallFilter() {
		return mallFilter;
	}

	/**
	 * 设置mallFilter
	 *
	 * @param mallFilter mallFilter
	 */
	public void setMallFilter(String mallFilter) {
		this.mallFilter = mallFilter;
	}

	public SearchRequestDTO(String key, String pageNum, String pageSize) {
		this.key = key;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
	}
}
