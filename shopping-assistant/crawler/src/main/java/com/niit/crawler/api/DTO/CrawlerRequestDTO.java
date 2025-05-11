package com.niit.crawler.api.DTO;

import java.io.Serializable;

/**
* <pre>描述: 爬虫请求DTO</pre>
 * DTO是展示层和服务层之间的数据传输对象
*/
public class CrawlerRequestDTO implements Serializable {

	/**
	 * 爬取URl
	 */
	private String url;

	/**
	 * 获取url
	 *
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置url
	 *
	 * @param url url
	 */
	public void setUrl(String url) {
		this.url = url;
	}


}
