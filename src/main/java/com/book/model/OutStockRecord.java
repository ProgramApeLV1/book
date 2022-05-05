package com.book.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 借出记录
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
@TableName("cs_out_stock_record")
public class OutStockRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 出库单号
     */
    private String outCode;
    /**
     * 关联单号
     */
    private String refCode;
    /**
     * 书号
     */
    private String bookCode;
    /**
     * 类型编码
     */
    private String typeCode;
    /**
     * 借出数量
     */
    private Integer outNum;
    /**
     * DCK:待出库,YCK:已出库
     */
    private Integer status;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     *
     */
    private Integer day;

    private String createName;
}
