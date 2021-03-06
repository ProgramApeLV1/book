package com.book.model.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author wyh123
 * @since 2019-01-02
 */
@Data
public class MenuVo implements Serializable {


    /**
     * 平台系统菜单  该数据也来源于子系统
     */
    private Integer id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 子应用编码 ref os_init code
     */
    private String applicationCode;
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
     * 菜单状态
     */
    private String statusName;
    /**
     * 菜单类别  0：一级菜单   1：二级菜单   2：按钮菜单 3:顶部导航菜单
     */
    private Integer resourceType;
    /**
     * 菜单类别  0：一级菜单   1：二级菜单   2：按钮菜单 3:顶部导航菜单
     */
    private String resourceTypeName;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
