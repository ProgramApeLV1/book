package com.book.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色管理表
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
@Data
public class RoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String unid;
    /**
     * 角色名
     */
    private String roleName;
    /**
     * 父级 -1为最上级
     */
    private String pid;
    /**
     * 角色名
     */
    private String pidName;
    /**
     * 0:禁用 1:启用
     */
    private Integer status;
    /**
     * 0:禁用 1:启用
     */
    private String statusName;
    /**
     * 创建时间
     */
    private String createTime;
}
