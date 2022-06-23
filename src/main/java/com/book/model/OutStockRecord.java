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
    @DateTimeFormat(pattern = DATE_FORMAT_NYRSFM)
    @JsonFormat(pattern = DATE_FORMAT_NYRSFM, timezone = "GMT+8")
    private Date createTime;

    /**
     *
     */
    private Integer day;

    private String createName;
}
