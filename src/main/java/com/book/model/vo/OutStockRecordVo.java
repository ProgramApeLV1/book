package com.book.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 借出记录
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
public class OutStockRecordVo {

    /**
     * 主键id
     */
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
     * 书名
     */
    private String bookName;
    /**
     * 类型编码
     */
    private String typeCode;
    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 借出数量
     */
    private Integer outNum;
    /**
     * DCK:待出库,YCK:已出库
     */
    private Integer status;
    /**
     * DCK:待出库,YCK:已出库
     */
    private String statusName;
    /**
     * 天数
     */
    private Integer day;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String createName;
}
