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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
