package com.book.controller.api.req.bookType;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static com.book.common.base.Constant.DATE_FORMAT_NYRSFM;

/**
 * <p>
 * 修改用户
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
public class RequestBookTypeEdit implements Serializable {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空")
    private Integer id;
    /**
     * 用户编码
     */
    @NotBlank(message = "类型编码不能为空")
    private String code;
    /**
     * 登陆名
     */
    @NotBlank(message = "类型名称不能为空")
    private String typeName;
    /**
     * 0：失效 1： 启用 2：禁用
     */
    @NotNull(message = "启用状态不能为空")
    private Integer status;
}
