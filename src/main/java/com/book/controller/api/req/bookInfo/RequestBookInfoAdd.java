package com.book.controller.api.req.bookInfo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 新增书本类型
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
public class RequestBookInfoAdd implements Serializable {

    /**
     * 用户编码
     */
    @NotBlank(message = "书本编码不能为空")
    private String bookCode;
    /**
     * 书名
     */
    @NotBlank(message = "书名不能为空")
    private String bookName;
    /**
     * 作者
     */
//    @NotNull(message = "作者不能为空")
    private String author;
    /**
     * 出版时间
     */
    private Date publishTime;

    /**
     * 单价
     */
    private Double unitPrice;
    /**
     * 出版社名称
     */
    @NotBlank(message = "出版社名称不能为空")
    private String publishingHouse;
    /**
     * 书本类型
     */
    @NotBlank(message = "书本类型不能为空")
    private String typeCode;
    /**
     * 0：失效 1： 启用 2：禁用
     */
    private Integer status = 2;
    /**
     * 创建时间
     */
    private Date createTime;
}
