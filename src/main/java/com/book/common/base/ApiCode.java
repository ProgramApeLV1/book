package com.book.common.base;


public enum ApiCode {

    /**
     * 操作成功
     **/
    SUCCESS(200, "操作成功"),
    /**
     * 请求参数有误
     */
    REQUEST_ERROR(400, "错误请求"),
    /**
     * 非法访问
     **/
    UNAUTHORIZED(401, "未认证授权或认证授权失败"),
    /**
     * 没有权限
     **/
    NOT_PERMISSION(403, "请求资源不允许访问"),
    /**
     * 你请求的资源不存在
     **/
    NOT_FOUND(404, "请求资源不存在"),
    /**
     * 系统异常服务器内部错误
     **/
    INTERNAL_FAIL(500, "系统繁忙，请稍后再试"),
    /**
     * 系统异常服务器内部错误
     **/
    REFRESH_TOKEN(3001, "令牌刷新"),
    /**
     * 令牌刷新
     **/
    INVALID_TOKEN(3002, "令牌已无效，请使用已刷新的令牌"),
    /**
     * 验证码
     **/
    INVALID_VERIFYCODE(3003, "验证码错误"),
    /**
     * 未知错误
     **/
    UNKNOW_ERROR(3004, "未知错误"),

    /**
     * 上传错误
     **/
    UPLOAD_ERROR(3005, "上传错误"),

    /**
     * 连接超时
     **/
    CONNECTITON_ERROR(3006, "服务调用超时,请重试");

    private final int code;
    private final String message;

    ApiCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public static ApiCode getApiCode(int code) {
        ApiCode[] ecs = ApiCode.values();
        for (ApiCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}