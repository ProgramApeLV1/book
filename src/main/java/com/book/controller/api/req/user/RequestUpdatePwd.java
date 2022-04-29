package com.book.controller.api.req.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 修改密码
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
public class RequestUpdatePwd implements Serializable {

    /**
     * 用户主键id
     */
    @NotBlank(message = "用户id不能为空")
    private String userId;
    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    private String password;
    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
