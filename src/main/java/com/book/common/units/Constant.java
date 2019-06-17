package com.book.common.units;

import org.springframework.beans.factory.annotation.Value;

/**
 * @description: 所有的定义需要习惯性使用常量
 * @author: wyh
 * @time:
 */
public class Constant {

    public static final String BUSINESS_SOURCE_CLS = "SCLS";
    /**
     * 日志打印字符串：成功
     */
    public static final String LOG_SUCCESS = "[成功]";
    /**
     * 日志打印字符串：错误
     */
    public static final String LOG_ERROR = "[错误]";
    /**
     * 日志打印字符串：警告
     */
    public static final String LOG_WARN = "[警告]";

    public static final String ACTION_SUCCESS = "操作成功";
    public static final String ACTION_ROOT_ERROR_INFO = "您没有操作权限";
    public static final String ACTION_ERROR = "操作失败";
    public static final String ACTION_ERROR_NO_USER = "未登录,不允许操作";
    public static final String ACTION_ERROR_NO_INFO = "找不到业务信息";
    public static final String ACTION_ERROR_ECFP_STATUS = "领用单状态错误";
    public static final String ACTION_ERROR_ECFP_USER = "只能由相关的网格长进行二次分配";
    public static final String ACTION_ERROR_PARAMS_NULL = "必填参数不可为空";
}

