package com.book.controller.api.req.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 修改角色
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
public class RequestRoleEdit implements Serializable {

    /***
     * 主键
     */
    private String roleId;
    /**
     * 角色名
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;
    /**
     * 父级 -1为最上级
     */
    private String pid = "-1";
    /**
     * 0:禁用 1:启用
     */
    @NotNull(message = "状态不能为空")
    private Integer status;
}
