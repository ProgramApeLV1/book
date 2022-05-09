package com.book.controller.api.req.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 新增用户
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
public class RequestUserAdd implements Serializable {

    /**
     * 主键id
     */
    private String id;
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 登陆名
     */
    @NotBlank(message = "loginName不能为空")
    private String loginName;
    /**
     * 用户名
     */
    @NotBlank(message = "userName不能为空")
    private String userName;
    /**
     * 密码
     */
    @NotBlank(message = "password不能为空")
    private String password;
    /**
     * 工作职责编码
     */
    private String workDutyCode;
    /**
     * 身份证号
     */
    @NotBlank(message = "identity不能为空")
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
    @NotBlank(message = "sex不能为空")
    private String sex;
    /**
     * 年龄
     */
    @NotNull(message = "age不能为空")
    private Integer age;
    /**
     * 手机号
     */
    @NotBlank(message = "phone不能为空")
    private String phone;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 0：失效 1： 启用 2：禁用
     */
    @NotNull(message = "status不能为空")
    private Integer status;
    /**
     * 0：失效 1： 启用 2：禁用
     */
    private String statusName;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 角色id
     */
    @NotEmpty(message = "roleIds不能为空")
    private List<String> roleIds;
}
