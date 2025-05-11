package com.niit.crawler.utils.common;


import com.niit.crawler.utils.Enum.ResultEnum;

/**
 * <pre>类名: ResponseUtils</pre>
 * <pre>描述: 构建返回信息工具类
 */
public class Response {

	public static Result success(){
		return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage());
	}

	public static Result fail(){
		return new Result(ResultEnum.FAIL.getCode(),ResultEnum.FAIL.getMessage());
	}

	public static Result success(Object object){
		return new Result(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),object);
	}
}
