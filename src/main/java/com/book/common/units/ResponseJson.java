package com.book.common.units;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseJson implements Serializable {

	private static final long serialVersionUID = 5576237395711742681L;

	private int result = 200;

	private String message = "操作成功";
	
	private Object data = null;
}
