package com.niit.management.utils;

import com.niit.management.api.DTO.ResponseDTO;

public class ResponseUtil {

	public static ResponseDTO success(){
		return new ResponseDTO("0","操作成功");
	}

	public static  ResponseDTO fail(){
		return new ResponseDTO("1","操作失败");
	}
}
