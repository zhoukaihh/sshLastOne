package com.qfedu.SSHDemo.vo;

public class Result {

	private Boolean success = false;
	
	private String message = "未知异常";

	public Boolean getSuccess() {
		return success;
	}

	public Result setSuccess(Boolean success) {
		this.success = success;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public Result setMessage(String message) {
		this.message = message;
		return this;
	}
	
	
}
