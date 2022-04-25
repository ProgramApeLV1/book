package com.book.common.units;

import com.book.common.base.ApiCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseJson implements Serializable {

    private static final long serialVersionUID = 5576237395711742681L;

    private int result = 200;

    private String message = "操作成功";

    private Object data = null;

    /***
     *@param  result 状态码
     * @param  message 提示语
     * @param  object 返回给客户端的对象
     * ***/
    public static ResponseJson responseJson(Integer result, String message, Object object) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(result);
        rJson.setMessage(message);
        rJson.setData(object);
        return rJson;
    }

    /***
     * ***/
    public static ResponseJson success() {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(ApiCode.SUCCESS.getCode());
        rJson.setMessage(ApiCode.SUCCESS.getMessage());
        rJson.setData(null);
        return rJson;
    }

    /**
     * @param apiCode 枚举
     * @return
     */
    public static ResponseJson success(ApiCode apiCode) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(apiCode.getCode());
        rJson.setMessage(apiCode.getMessage());
        rJson.setData(null);
        return rJson;
    }

    /**
     * @param object 返回值
     * @return
     */
    public static ResponseJson success(Object object) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(ApiCode.SUCCESS.getCode());
        rJson.setMessage(ApiCode.SUCCESS.getMessage());
        rJson.setData(object);
        return rJson;
    }

    /**
     * @param apiCode 枚举
     * @param object 返回值
     * @return
     */
    public static ResponseJson success(ApiCode apiCode, Object object) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(apiCode.getCode());
        rJson.setMessage(apiCode.getMessage());
        rJson.setData(null);
        return rJson;
    }

    /***
     * @param  message 返回给客户端的对象
     * ***/
    public static ResponseJson success(String message) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(ApiCode.SUCCESS.getCode());
        rJson.setMessage(message);
        rJson.setData(null);
        return rJson;
    }

    /***
     * @param  message 返回给客户端的对象
     * @param object 返回值
     * ***/
    public static ResponseJson success(String message, Object object) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(ApiCode.SUCCESS.getCode());
        rJson.setMessage(message);
        rJson.setData(object);
        return rJson;
    }

    public static ResponseJson error() {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(ApiCode.REQUEST_ERROR.getCode());
        rJson.setMessage(ApiCode.REQUEST_ERROR.getMessage());
        rJson.setData(null);
        return rJson;
    }

    public static ResponseJson error(ApiCode apiCode) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(apiCode.getCode());
        rJson.setMessage(apiCode.getMessage());
        rJson.setData(null);
        return rJson;
    }

    public static ResponseJson error(String message) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(ApiCode.REQUEST_ERROR.getCode());
        rJson.setMessage(message);
        rJson.setData(null);
        return rJson;
    }

    public static ResponseJson error(int code, String message) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(code);
        rJson.setMessage(message);
        rJson.setData(null);
        return rJson;
    }
}
