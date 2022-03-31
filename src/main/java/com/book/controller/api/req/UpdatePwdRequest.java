package com.book.controller.api.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 修改密码
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
public class UpdatePwdRequest implements Serializable {

    /**
     * 用户主键id
     */
    private String userId;
    /**
     * 旧密码
     */
    private String password;
    /**
     * 新密码
     */
    private String newPassword;
}
