package com.book.service;

import com.book.model.MenuTree;
import com.book.model.Role;
import com.book.model.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 角色菜单关系表 服务类
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    List<MenuTree> getMenu(Role role) throws Exception;

    Set<String> getMenuIdsByRoleIds(List<Role> roles) throws Exception;
}
