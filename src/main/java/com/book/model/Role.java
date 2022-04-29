package com.book.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("os_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "unid", type = IdType.UUID)
    private String unid;
    /**
     * 角色名
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 父级 空则为最上级
     */
    private String pid;
    /**
     * 0:禁用 1:启用
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
}
