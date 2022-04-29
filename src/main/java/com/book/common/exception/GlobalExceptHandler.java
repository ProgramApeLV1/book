package com.book.common.exception;

import com.book.common.base.ApiCode;
import com.book.common.units.ResponseJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.stream.Collectors;

/**
 * @PackageName: com.example.mybatisdemo.config
 * @ClassName: GlobalExceptHandler
 * @Date: create in 2021/7/15 14:41
 * @Author: wyh
 * @Description: 全局异常增强类
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseJson exceptionHandler(Exception e, HttpServletRequest request) {
        log.error("异常接口:{}\n异常信息:{}", request.getRequestURI(), e.getMessage(), e);
        return ResponseJson.error(ApiCode.UNKNOW_ERROR.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = BusinessException.class)
    public ResponseJson businessExceptionHandler(BusinessException e, HttpServletRequest request) {
        log.error("异常接口:{}\n异常信息:{}", request.getRequestURI(), e.getMessage(), e);
        return ResponseJson.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 参数异常校验处理
     *
     * @param e       异常对象
     * @param request httpservletrequest
     * @param rsp     返回结果
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus
    public ResponseJson paramNullException(Exception e, HttpServletRequest request, HttpServletResponse rsp) {
        String errorMsg = "参数验证错误";
        log.error("异常接口:{}\n异常信息:{}", request.getRequestURI(), e.getMessage(), e);
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) e;
            bindingResult = methodArgumentNotValidException.getBindingResult();
        }
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) e;
            errorMsg = constraintViolationException.getMessage();
        }
        if (bindingResult != null) {
            errorMsg = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        }
        return ResponseJson.error(ApiCode.REQUEST_ERROR.getCode(), errorMsg);
    }

    @ExceptionHandler(SocketTimeoutException.class)
    @ResponseStatus
    public ResponseJson connectionException(Exception e, HttpServletRequest request, HttpServletResponse rsp) {
//  connect timed out java.net.SocketTimeoutException Socket TCP建立连接时三次握手超时，如果建立连接的时间超过了设置的Socket连接的超时时间触发TimeoutException异常 网络延迟、网络断开、网卡异常、服务端性能、客户端异常等等
//  Read timed out java.net.SocketTimeoutException 如果输入缓冲队列RecvQ中没有数据，read操作会一直阻塞而挂起线程，直到有新的数据到来并且已经超过了设置的读超时时间时触发 客户端或者服务端进程崩溃、对方机器突然重启、网络断开等
        log.error("异常接口:{}\n异常信息:{}", request.getRequestURI(), e.getMessage(), e);
        return ResponseJson.error(ApiCode.CONNECTITON_ERROR);
    }

    @ExceptionHandler({ConnectException.class, SocketException.class})
    @ResponseStatus
    public ResponseJson socketException(Exception e, HttpServletRequest request, HttpServletResponse rsp) {
//  Connection refused java.net.ConnectException 访问服务端IP不通或者端口服务没有启用 网络异常、服务down掉等
//  Connection reset or connection reset by peer java.net.SocketException 客户端或者服务端其中一方退出，但退出时并未关闭该连接，另一方仍然在从连接中读数据则抛出该异常（发送的第一个数据包引发该异常Connect reset by peer 服务端并发连接数达到负载主动断开连接；客户端关闭但服务端仍读写数据
        log.error("异常接口:{}\n异常信息:{}", request.getRequestURI(), e.getMessage(), e);
        return ResponseJson.error(ApiCode.CONNECTITON_ERROR);
    }
}
