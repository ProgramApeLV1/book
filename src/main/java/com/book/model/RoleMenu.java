package com.book.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单关系表
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
@Data
@TableName("os_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "unid", type = IdType.ASSIGN_UUID)
    private String unid;
    /**
     * 角色id
     */
    @TableField("role_id")
    private Integer roleId;
    /**
     * 菜单id
     */
    @TableField("menu_id")
    private Integer menuId;
}
