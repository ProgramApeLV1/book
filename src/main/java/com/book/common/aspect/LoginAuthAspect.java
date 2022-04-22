package com.book.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @packageName: com.book.common.aspect
 * @className: LoginAuthAspect
 * @date: create in 2022/4/11 16:33
 * @author: wyh
 * @description:
 */
@Aspect
@Slf4j
@Configuration
public class LoginAuthAspect {

    @Pointcut("@within(com.book.controller.api.*)")
    public void pointcut() {
    }


}
