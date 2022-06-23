package com.book.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

import static com.book.common.base.Constant.DATE_FORMAT_NYRSFM;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author wyh123
 * @since 2019-01-02
 */
@Data
@TableName("os_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台系统菜单  该数据也来源于子系统
     */
    @TableId(value = "unid", type = IdType.ASSIGN_UUID)
    private String unid;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单编码
     */
    private String code;
    /**
     * 菜单路径
     */
    private String url;
    /**
     * 菜单介绍
     */
    private String description;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 父级菜单id
     */
    private String pCode;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 菜单状态
     */
    private Integer status;
    /**
     * 菜单类别  0：一级菜单   1：二级菜单
     */
    private Integer level;
    /**
     * 菜单类别  0：菜单  2：按钮
     */
    private Integer resourceType;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DATE_FORMAT_NYRSFM)
    @JsonFormat(pattern = DATE_FORMAT_NYRSFM, timezone = "GMT+8")
    private Date createTime;
}
