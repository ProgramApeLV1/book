package com.book.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import static com.book.common.base.Constant.DATE_FORMAT_NYRSFM;

/**
 * <p>
 * 书本基础信息
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
public class BookTypeVo implements Serializable {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 类型编码
     */
    private String code;
    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 0：失效 1： 启用 2：禁用
     */
    private Integer status;
    /**
     * 0：失效 1： 启用 2：禁用
     */
    private String statusName;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DATE_FORMAT_NYRSFM)
    @JsonFormat(pattern = DATE_FORMAT_NYRSFM, timezone = "GMT+8")
    private Date createTime;
}
