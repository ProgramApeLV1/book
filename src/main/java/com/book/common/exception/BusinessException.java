package com.book.common.exception;

import com.book.common.base.ApiCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @PackageName: com.example.mybatisdemo.exception
 * @ClassName: BusinessException
 * @Date: create in 2021/7/15 15:07
 * @Author: wyh
 * @Description:
 */
@Setter
@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected int errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public BusinessException() {
        super();
        this.errorCode = ApiCode.INTERNAL_FAIL.getCode();
        this.errorMsg = ApiCode.INTERNAL_FAIL.getMessage();
    }

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public BusinessException(Throwable cause) {
        super(cause);
        this.errorCode = ApiCode.INTERNAL_FAIL.getCode();
        this.errorMsg = ApiCode.INTERNAL_FAIL.getMessage();
    }

    public BusinessException(ApiCode apiCode) {
        super(String.valueOf(apiCode.getCode()));
        this.errorCode = apiCode.getCode();
        this.errorMsg = apiCode.getMessage();
    }

    public BusinessException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorCode = ApiCode.INTERNAL_FAIL.getCode();
        this.errorMsg = errorMsg;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
