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
 * 归还记录
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
public class InStockRecordVo {


    private Integer id;
    /**
     * 入库单号
     */
    private String inCode;
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
    private String typeName;
    /**
     * 归还数量
     */
    private Integer inNum;
    /**
     * DRK:待入库,YRK:已入库
     */
    private Integer status;
    /**
     *
     */
    private String statusName;
    /**
     * 归还时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date returnTime;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     *
     */
    private String createName;

    /**
     *
     */
    private String returnName;

    /**
     *
     */
    private Integer day;
}
