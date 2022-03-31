package com.book.model;

import com.baomidou.mybatisplus.enums.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 用户登入
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
@TableName("os_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 用户编码{系统级别：自动生成并且唯一,运用于云平台中}
     */
    private String userCode;
    /**
     * 登陆名
     */
    private String loginName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 工作职责编码
     */
    private String workDutyCode;
    /**
     * 身份证号
     */
    private String identity;
    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 职员工号
     */
    private String jobNumber;
    /**
     * 性别 0:男 1:女
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户类型 
     */
    private String userType;
    /**
     * 0：失效 1： 启用 2：禁用
     */
    private Integer status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /***
     * 用户对应的角色集合
     */
//    Set<Role> roles;
}
