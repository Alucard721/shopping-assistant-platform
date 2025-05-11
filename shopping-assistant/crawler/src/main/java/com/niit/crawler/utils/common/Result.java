package com.niit.crawler.utils.common;


/**
 * <pre>类名: Result</pre>
 * <pre>描述: 结果信息类</pre>
 */
public class Result {
	String result = null; // 结果代码
	String message = null; //成功 或 失败
	Object obj = null; //结果数据

	public Result() {
	}
    public Result(String message) {
	this.message=message;
   }
	public Result(String result, String message) {
		this.result = result;
		this.message = message;
	}

	public Result(String result, String message, Object obj) {
		this.result = result;
		this.message = message;
		this.obj = obj;
	}

	/**
	 * 获取result
	 *
	 * @return result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * 设置result
	 *
	 * @param result result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * 获取message
	 *
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置message
	 *
	 * @param message message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取obj
	 *
	 * @return obj
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * 设置obj
	 *
	 * @param obj obj
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

}
