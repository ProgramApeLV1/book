package com.book.controller.api;


import com.book.common.base.ApiCode;
import com.book.common.exception.BusinessException;
import com.book.common.units.BeanUtil;
import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.controller.api.req.role.RequestRoleAdd;
import com.book.controller.api.req.role.RequestRoleEdit;
import com.book.model.Role;
import com.book.model.vo.RoleVo;
import com.book.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 角色管理表 前端控制器
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/roleApi")
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @GetMapping(value = "/getRoleList")
    public ResponseJson getRoleList(Integer page, Integer rows, String roleName) throws Exception {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("roleName", roleName);
        pageInfo.setCondition(condition);
        roleService.getRoleList(pageInfo);
        return ResponseJson.success(pageInfo);
    }

    @GetMapping(value = "/getRootRoleList")
    public ResponseJson getRootRoleList() throws Exception {
        List<RoleVo> roleVoList = roleService.getRootRoleList();
        return ResponseJson.success(roleVoList);
    }

    @PostMapping(value = "/save")
    public ResponseJson add(@RequestBody @Valid RequestRoleAdd requestRoleAdd) throws Exception {
        Role role = new Role();
        BeanUtil.convertBean2Bean(requestRoleAdd, role);
        role.setCreateTime(LocalDateTime.now());
        roleService.save(role);
        return ResponseJson.success();
    }

    @PutMapping(value = "/update")
    public ResponseJson put(@RequestBody @Valid RequestRoleEdit requestRoleEdit) throws Exception {
        Role role = roleService.getById(requestRoleEdit.getRoleId());
        if (Objects.isNull(role)) {
            throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "当前角色不存在!");
        }
        role.setRoleName(requestRoleEdit.getRoleName());
        role.setPid(requestRoleEdit.getPid());
        role.setStatus(requestRoleEdit.getStatus());
        roleService.updateById(role);
        return ResponseJson.success();
    }

    @DeleteMapping(value = "/{roleId}")
    public ResponseJson delete(@PathParam("roleId") String roleId) throws Exception {
        Role role = roleService.getById(roleId);
        if (Objects.isNull(role)) throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "角色id不存在");
        roleService.removeById(roleId);
        return ResponseJson.success();
    }
}

