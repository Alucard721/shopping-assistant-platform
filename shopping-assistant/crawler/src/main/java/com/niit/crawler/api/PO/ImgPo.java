package com.niit.crawler.api.PO;

/**
 * 持久化对象,对应数据库中的表
 */
public class ImgPo {

	private String imgKey = "image_url";

	private String imgUrl;

	/**
	 * 获取imgKey
	 *
	 * @return imgKey
	 */
	public String getImgKey() {
		return imgKey;
	}

	/**
	 * 设置imgKey
	 *
	 * @param imgKey imgKey
	 */
	public void setImgKey(String imgKey) {
		this.imgKey = imgKey;
	}

	/**
	 * 获取imgUrl
	 *
	 * @return imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * 设置imgUrl
	 *
	 * @param imgUrl imgUrl
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public ImgPo(String imgKey, String imgUrl) {
		this.imgKey = imgKey;
		this.imgUrl = imgUrl;
	}
}
