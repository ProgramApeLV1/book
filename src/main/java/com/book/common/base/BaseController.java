package com.book.common.base;

import com.book.common.exception.BusinessException;
import com.book.common.units.*;
import com.book.model.User;
import com.book.model.UserRole;
import com.book.model.vo.UserVo;
import com.book.service.IRoleService;
import com.book.service.IUserRoleService;
import com.book.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
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
import java.util.Set;
import java.util.stream.Collectors;

import static com.book.common.base.Constant.DATE_FORMAT_NYRSFM;
import static com.book.common.base.Constant.TOKEN_NAME;


/**
 * @author wyh
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
    protected IUserRoleService userRoleService;
    @Autowired
    protected IRoleService roleService;
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
        List<User> userList = userService.list();
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
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(DATE_FORMAT_NYRSFM), true));
    }

    /**
     * 根据token获取用户信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    public UserVo userInfoByToken(HttpServletRequest request) throws Exception {
        String token = CookieUtils.getUid(request, TOKEN_NAME);
        if (StringUtil.isEmpty(token)) {
            throw new BusinessException(ApiCode.UNAUTHORIZED);
        }
        return userService.userInfoByToken(token);
    }
}
