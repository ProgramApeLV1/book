package com.book.common;

import java.io.Serializable;

/**
 * @description：操作结果集
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 5576237395711742681L;
	
    public static final int SUCCESS = 1;
    
    public static final int FAILURE = -1;

    private boolean success = false;

    private String msg = "";

    private Object obj = null;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
    public Result(boolean flag, String msg){
        this.success=flag;
        this.msg=msg;
    }
    public Result(){

    }
}
