package com.niit.management.api.DTO;

import java.util.List;

public class PageRequest<T> {

	private Integer pageNum;

	private Integer pageSize;

	private List<T> content;

	private boolean isFirst = false;

	private boolean hasNext = true;

	/**
	 * 获取pageNum
	 *
	 * @return pageNum
	 */
	public Integer getPageNum() {
		return pageNum;
	}

	/**
	 * 设置pageNum
	 *
	 * @param pageNum pageNum
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * 获取pageSize
	 *
	 * @return pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	/**
	 * 设置pageSize
	 *
	 * @param pageSize pageSize
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取content
	 *
	 * @return content
	 */
	public List<T> getContent() {
		return content;
	}

	/**
	 * 设置content
	 *
	 * @param content content
	 */
	public void setContent(List<T> content) {
		this.content = content;
	}

	/**
	 * 获取isFirst
	 *
	 * @return isFirst
	 */
	public boolean isFirst() {
		return isFirst;
	}

	/**
	 * 设置isFirst
	 *
	 * @param isFirst isFirst
	 */
	public void setFirst(boolean first) {
		isFirst = first;
	}

	/**
	 * 获取hasNext
	 *
	 * @return hasNext
	 */
	public boolean isHasNext() {
		return hasNext;
	}

	/**
	 * 设置hasNext
	 *
	 * @param hasNext hasNext
	 */
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
}
