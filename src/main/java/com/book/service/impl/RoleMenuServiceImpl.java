package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.book.mapper.MenuMapper;
import com.book.model.Menu;
import com.book.model.MenuTree;
import com.book.model.Role;
import com.book.model.RoleMenu;
import com.book.mapper.RoleMenuMapper;
import com.book.service.IMenuService;
import com.book.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单关系表 服务实现类
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Autowired
    private IMenuService menuService;

    @Override
    public List<MenuTree> getMenu(Role role) throws Exception {
        List<RoleMenu> roleMenus = baseMapper.selectList(
                new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, role.getUnid())
        );
        if (CollectionUtils.isEmpty(roleMenus)) return null;
        Set<String> menuIds = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
        return menuService.getMenuTree(menuIds);
    }

    @Override
    public Set<String> getMenuIdsByRoleIds(List<Role> roles) throws Exception {
        // 获取不重复的角色id数组
        Set<String> roleIds = roles.stream().map(Role::getUnid).collect(Collectors.toSet());
        List<RoleMenu> roleMenus = baseMapper.selectList(
                new LambdaQueryWrapper<RoleMenu>().in(RoleMenu::getRoleId, roleIds)
        );
        return roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toSet());
    }
}
