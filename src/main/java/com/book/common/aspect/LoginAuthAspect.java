package com.book.common.aspect;

import com.book.common.units.BeanUtils;
import com.book.common.units.CookieUtils;
import com.book.common.units.RedisClient;
import com.book.model.vo.UserVo;
import com.book.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static com.book.common.base.Constant.*;

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
public class LoginAuthAspect implements HandlerInterceptor {

//    @Resource
//    IUserService userService;

    @Pointcut("@within(com.book.controller.api.*)")
    public void pointcut() {
    }

    /***
     * 在请求处理之前进行调用(Controller方法调用之前)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
            String token = CookieUtils.getUid(request, TOKEN_NAME);
            if (!StringUtils.isEmpty(token)) {
                //手动注入bean
                IUserService userService = BeanUtils.getBean(IUserService.class);
                UserVo userVo = userService.userInfoByToken(token);
                if (Objects.isNull(userVo)) {
                    log.error("当前请求路径{},token获取用户信息失败!", request.getRequestURI());
                    response.sendRedirect(basePath);
                    return false;
                }
                //手动注入bean
                RedisClient redisClient = BeanUtils.getBean(RedisClient.class);
                // 2022/7/4 可以更新当前存在redis缓存的用户信息过期时长
                redisClient.expire(token, HALF_HALF_HOUR);
                //延长cookie中的时长
                CookieUtils.addCookie(response, TOKEN_NAME, token, HALF_HALF_HOUR.intValue());
                return true;
            }
            log.error("当前请求路径{},token已过期!", request.getRequestURI());
            response.sendRedirect(basePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /***
     * 整个请求结束之后被调用，也就是在DispatchServlet渲染了对应的视图之后执行（主要用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
