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
 * 书本基础信息
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@TableName("cs_library_stock")
public class LibraryStock implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 书号
     */
    private String bookCode;
    /**
     * 类型编码
     */
    private String typeCode;
    /**
     * 库存数量
     */
    private Integer sumStock;
    /**
     * 可借数量
     */
    private Integer canStock;
    /**
     * 借出数量
     */
    private Integer outStock;
    /**
     * 0：失效 1： 启用 2：禁用
     */
    private Integer status;
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

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getSumStock() {
        return sumStock;
    }

    public void setSumStock(Integer sumStock) {
        this.sumStock = sumStock;
    }

    public Integer getCanStock() {
        return canStock;
    }

    public void setCanStock(Integer canStock) {
        this.canStock = canStock;
    }

    public Integer getOutStock() {
        return outStock;
    }

    public void setOutStock(Integer outStock) {
        this.outStock = outStock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "LibraryStock{" +
        ", id=" + id +
        ", bookCode=" + bookCode +
        ", typeCode=" + typeCode +
        ", sumStock=" + sumStock +
        ", canStock=" + canStock +
        ", outStock=" + outStock +
        ", status=" + status +
        ", createTime=" + createTime +
        "}";
    }
}
