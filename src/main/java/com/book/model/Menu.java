package com.book.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author wyh123
 * @since 2019-01-02
 */
@TableName("os_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 平台系统菜单  该数据也来源于子系统
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
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
     * 菜单类别  0：一级菜单   1：二级菜单   2：按钮菜单 3:顶部导航菜单
     */
    private Integer resourceType;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Menu{" +
        ", id=" + id +
        ", name=" + name +
        ", code=" + code +
        ", url=" + url +
        ", description=" + description +
        ", icon=" + icon +
        ", pCode=" + pCode +
        ", seq=" + seq +
        ", status=" + status +
        ", resourceType=" + resourceType +
        ", createTime=" + createTime +
        "}";
    }
}
