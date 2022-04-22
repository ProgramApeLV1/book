package com.book.common.exception;

import com.book.common.base.ApiCode;
import com.book.common.units.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @PackageName: com.example.mybatisdemo.config
 * @ClassName: GlobalExceptHandler
 * @Date: create in 2021/7/15 14:41
 * @Author: wyh
 * @Description: 全局异常增强类
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseJson exceptionHandler(Exception e) {
        return ResponseJson.error(ApiCode.UNKNOW_ERROR.getCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseJson businessExceptionHandler(BusinessException e) {
        return ResponseJson.error(e.getErrorCode(), e.getErrorMsg());
    }
}
