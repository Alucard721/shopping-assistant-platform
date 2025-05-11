package com.niit.management.api.DTO;

public class ResponseDTO {

	String code;
	String returnMessage;

	/**
	 * 获取code
	 *
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置code
	 *
	 * @param code code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取returnMessage
	 *
	 * @return returnMessage
	 */
	public String getReturnMessage() {
		return returnMessage;
	}

	/**
	 * 设置returnMessage
	 *
	 * @param returnMessage returnMessage
	 */
	public void setReturnMessage(String returnMessage) {
		this.returnMessage = returnMessage;
	}

	public ResponseDTO() {
	}

	public ResponseDTO(String code, String returnMessage) {
		this.code = code;
		this.returnMessage = returnMessage;
	}
}
