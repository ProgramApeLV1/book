package com.book.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import static com.book.common.base.Constant.DATE_FORMAT_NYRSFM;

/**
 * <p>
 * 用户登入
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
public class UserVo implements Serializable {

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
     * 0：失效 1： 启用 2：禁用
     */
    private String statusName;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DATE_FORMAT_NYRSFM)
    @JsonFormat(pattern = DATE_FORMAT_NYRSFM, timezone = "GMT+8")
    private LocalDateTime createTime;
    /**
     * 角色id
     */
    private Set<String> roleIds;
}
