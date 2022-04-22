package com.book.common.base;

import com.book.common.exception.BusinessException;
import com.book.common.units.CookieUtils;
import com.book.common.units.RedisClient;
import com.book.common.units.ResponseJson;
import com.book.common.units.StringUtil;
import com.book.model.User;
import com.book.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author laihz
 * @description: 控制层基础类
 * @since 2018-05-07 11:10
 */
public class BaseController implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * ThreadLocal确保高并发下每个请求的request，response,session都是独立的
     */
    private static ThreadLocal<ServletRequest> currentRequest = new ThreadLocal<ServletRequest>();

    private static ThreadLocal<ServletResponse> currentResponse = new ThreadLocal<ServletResponse>();

    private static ThreadLocal<HttpSession> currentSession = new ThreadLocal<HttpSession>();

    @Autowired
    protected IUserService userService;

    @Autowired
    protected RedisClient redisClient;

    /**
     * 线程安全初始化request，respose,session对象
     *
     * @param request
     * @param response
     * @param session
     */
    @ModelAttribute
    public void initReqAndRep(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        currentRequest.set(request);
        currentResponse.set(response);
        currentSession.set(session);
    }

    /**
     * @return
     * @description 获取当前登录的用户对象缓存的账号权限信息
     */
    public List<User> getAllUser() {
        List<User> userList = userService.selectList(null);
        return userList;
    }

    /**
     * 线程安全
     *
     * @return
     */
    public HttpServletRequest request() {
        return (HttpServletRequest) currentRequest.get();
    }

    /**
     * 线程安全
     *
     * @return
     */
    public HttpServletResponse response() {
        return (HttpServletResponse) currentResponse.get();
    }

    /**
     * 跳转到到登录页面(jsp接口调用)
     *
     * @return
     */
    public ModelAndView toLoginView() {
        return new ModelAndView("");
    }

    /**
     * 返回session
     *
     * @return
     */
    public static HttpSession getSession() {
        return currentSession.get();
    }

    /**
     * 移除session中的值
     *
     * @param key
     * @return
     */
    public boolean removeSessionAttr(String key) {
        HttpSession session = ((HttpServletRequest) currentRequest).getSession(false);
        if (session != null) {
            session.removeAttribute(key);
            return true;
        }
        return false;

    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    public ResponseJson currentUserInfo(HttpServletRequest request) throws Exception {
        String token = CookieUtils.getUid(request, "token");
        if (StringUtil.isEmpty(token)) {
            throw new BusinessException(ApiCode.UNAUTHORIZED);
        }

        return ResponseJson.success();
    }
}
