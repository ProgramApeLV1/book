package com.book.controller.api.req.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 新增角色
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Data
public class RequestSaveRoleMenu implements Serializable {

    /**
     * 角色Id
     */
    @NotBlank(message = "角色Id不能为空")
    private String roleId;
    /**
     * 所有被选择的节点
     */
    private List<String> newCheckedNodesArr;
}
