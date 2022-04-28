package com.book.model.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
     * 父级 空则为最上级
     */
    private String pid;
    /**
     * 角色名
     */
    private String pRoleName;
    /**
     * 0:禁用 1:启用
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
}
