package com.book.controller.api;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.book.common.base.ApiCode;
import com.book.common.base.BaseController;
import com.book.common.exception.BusinessException;
import com.book.common.units.BeanUtil;
import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.controller.api.req.role.RequestRoleAdd;
import com.book.controller.api.req.role.RequestRoleEdit;
import com.book.controller.api.req.role.RequestSaveRoleMenu;
import com.book.model.Menu;
import com.book.model.MenuTree;
import com.book.model.Role;
import com.book.model.RoleMenu;
import com.book.model.vo.RoleVo;
import com.book.service.IMenuService;
import com.book.service.IRoleMenuService;
import com.book.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
public class RoleController extends BaseController {

    @Autowired
    private IRoleMenuService roleMenuService;

    @Autowired
    private IMenuService menuService;


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

    @GetMapping(value = "/getAllRoleList")
    public ResponseJson getAllRoleList() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("status", 1);
        List<Role> roles = roleService.listByMap(params);
        List<RoleVo> roleVoList = roles.stream().map(role -> {
            RoleVo roleVo = new RoleVo();
            BeanUtil.convertBean2Bean(role, roleVo);
            return roleVo;
        }).collect(Collectors.toCollection(ArrayList::new));
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
    public ResponseJson delete(@PathVariable("roleId") String roleId) throws Exception {
        Role role = roleService.getById(roleId);
        if (Objects.isNull(role)) throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "角色id不存在");
        roleService.removeById(roleId);
        return ResponseJson.success();
    }

    @GetMapping(value = "/getMenu/{roleIds}")
    public ResponseJson getMenu(@PathVariable("roleIds") List<String> roleIds) throws Exception {
//        Role role = roleService.getById(roleId);
        List<Role> roles = roleService.listByIds(roleIds);
        if (CollectionUtils.isEmpty(roles))
            throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "角色id不存在");
        Set<String> menuIds = roleMenuService.getMenuIdsByRoleIds(roles);
        Set<String> allValidMenuIds = menuService.getAllValidMenuIds();
        if (CollectionUtils.isEmpty(allValidMenuIds))
            throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "获取所有菜单ID失败");
        List<MenuTree> menus = menuService.getCheckMenuTree(allValidMenuIds, menuIds);
        return ResponseJson.success(menus);
    }

    @PostMapping(value = "/saveRoleMenu")
    public ResponseJson saveRoleMenu(@RequestBody @Valid RequestSaveRoleMenu requestSaveRoleMenu) throws Exception {
        Role role = roleService.getById(requestSaveRoleMenu.getRoleId());
        if (Objects.isNull(role))
            throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "角色id不存在");
        // 删除所有角色关联菜单关系
        roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, role.getUnid()));
        // 绑定新的节点信息
        if (!CollectionUtils.isEmpty(requestSaveRoleMenu.getNewCheckedNodesArr())) {
            List<RoleMenu> roleMenus = new ArrayList<>();
            requestSaveRoleMenu.getNewCheckedNodesArr().forEach(menuId -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(role.getUnid());
                roleMenu.setMenuId(menuId);
                roleMenus.add(roleMenu);
            });
            roleMenuService.saveBatch(roleMenus);
        }
        return ResponseJson.success();
    }
}

