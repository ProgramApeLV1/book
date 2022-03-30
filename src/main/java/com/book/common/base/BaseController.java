package com.book.common.base;

import com.book.common.units.Result;
import com.book.common.units.ResponseJson;
import com.book.model.User;
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
    private IUserService userService;

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

    /**
     * http 成功  m1
     *
     * @return
     */
    public Object renderSuccess(String msg, Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        result.setObj(obj);
        return result;
    }

    /**
     * http 失败 m1
     *
     * @return
     */
    public Object renderError(String msg, Object obj) {
        Result result = new Result();
        result.setSuccess(false);
        result.setMsg(msg);
        result.setObj(obj);
        return result;
    }


    /**
     * ajax成功
     *
     * @return {Object}
     */
    public Object renderSuccess() {
        Result result = new Result();
        result.setSuccess(true);
        return result;
    }


    /**
     * ajax失败
     *
     * @param msg 失败的消息
     * @return {Object}
     */
    public Object renderError() {
        Result result = new Result();
        result.setSuccess(false);
        return result;
    }


    /**
     * ajax成功
     *
     * @param msg 消息
     * @return {Object}
     */
    public Object renderSuccess(String msg) {
        Result result = new Result();
        result.setSuccess(true);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax失败
     *
     * @param msg 消息
     * @return {Object}
     */
    public Object renderError(String msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }

    /**
     * ajax成功
     *
     * @param obj 成功时的对象
     * @return {Object}
     */
    public Object renderSuccess(Object obj) {
        Result result = new Result();
        result.setSuccess(true);
        result.setObj(obj);
        return result;
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /**
         * 自动转换日期类型的字段格式
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    /***
     *@param  result 状态码
     * @param  message 提示语
     * @param  object 返回给客户端的对象
     * ***/
    public Object responseJson(Integer result, String message, Object object) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(result);
        rJson.setMessage(message);
        rJson.setObject(object);
        return rJson;
    }

    /***
     * @param  object 返回给客户端的对象
     * ***/
    public Object responseSuccess(String message) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(200);
        rJson.setMessage(message);
        rJson.setObject(message);
        return rJson;
    }

    /***
     * @param  message 返回给客户端的错误提示信息
     * ***/
    public Object responseError(String message) {
        ResponseJson rJson = new ResponseJson();
        rJson.setResult(500);
        rJson.setMessage(message);
        rJson.setObject(null);
        return rJson;
    }
}
