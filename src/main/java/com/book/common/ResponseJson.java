package com.book.common;

import java.io.Serializable;

public class ResponseJson implements Serializable {

	private static final long serialVersionUID = 5576237395711742681L;

	private int result = 200;

	private String message = "操作成功";
	
	private Object object = null;

	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}
