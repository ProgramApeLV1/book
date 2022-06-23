package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.common.base.ApiCode;
import com.book.common.exception.BusinessException;
import com.book.common.units.PageInfo;
import com.book.common.units.StringUtils;
import com.book.model.*;
import com.book.mapper.MenuMapper;
import com.book.model.vo.MenuVo;
import com.book.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.book.common.base.Constant.DATE_FORMAT_NYRSFM;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-02
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleMenuService roleMenuService;
    @Autowired
    private IMenuService menuService;

    private static final SimpleDateFormat sp = new SimpleDateFormat(DATE_FORMAT_NYRSFM);

    @Override
    public List<MenuTree> getMenuTree() {
        String pCode = null;
        Integer status = 1;
        List<MenuTree> treeList = baseMapper.getParentMenuTree(pCode, status);
        List<MenuTree> removeTree = new ArrayList<>();
        List<MenuTree> removeTreeChild = new ArrayList<>();
        Set<String> treeSet = new HashSet<>();
        for (MenuTree tree : treeList) {
            pCode = tree.getCode();
            tree.setChildren(baseMapper.getParentMenuTree(pCode, status));
        }
        for (MenuTree tree : treeList) {
            if (!treeSet.add(tree.getPCode() + tree.getText())) {
                removeTree.add(tree);
            }
            for (MenuTree treeChild : tree.getChildren()) {
                if (!treeSet.add(String.valueOf(treeChild.getAttributes()))) {
                    removeTreeChild.add(treeChild);
                }
            }
            tree.getChildren().removeAll(removeTreeChild);
            removeTreeChild.clear();
        }
        treeList.removeAll(removeTree);
        return treeList;
    }

    @Override
    public List<MenuTree> getMenuTree(Set<String> menuIds) {
        List<Menu> menus = baseMapper.selectList(
                new LambdaQueryWrapper<Menu>().in(Menu::getUnid, menuIds)
                        .eq(Menu::getStatus, 1)
        );

        return null;
    }

    @Override
    public void getMenuInfoList(PageInfo pageInfo) {
        Page<MenuVo> page = new Page<MenuVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<MenuVo> list = baseMapper.getMenuInfoList(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public List<Menu> getParentMenuList() {
        return baseMapper.getParentMenuList();
    }

    @Override
    public MenuVo getCurMenuInfo(String unid) {
        MenuVo res = new MenuVo();
        Menu menu = baseMapper.selectById(unid);
        BeanUtils.copyProperties(menu, res);
        return res;
    }

    @Override
    public Menu getParentMenu(String pCode) {
        return baseMapper.getMenuInfo(pCode);
    }

    @Override
    public List<MenuTree> getMenuTreeByUser(User user) throws Exception {
        // 2022/5/9 获取用户所有角色列表
        List<UserRole> userRoles = userRoleService.getAllUserRoleByUserId(user.getId());
        if (CollectionUtils.isEmpty(userRoles)) {
            logger.error("该用户ID{}未查询到相关角色ID", user.getId());
            return null;
        }
        Set<String> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        List<Role> roles = roleService.listByIds(roleIds);
        // 过滤掉无效角色
        roles = roles.stream().filter(role -> role.getStatus().equals(1)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roles)) {
            logger.error("该用户无可用相关角色ID");
            return null;
        }
        // 2022/5/9 根据角色去获取菜单列表(去重后的)
        Set<String> menuIds = roleMenuService.getMenuIdsByRoleIds(roles);
        if (CollectionUtils.isEmpty(menuIds)) {
            logger.error("未查询到相关菜单ID");
            return null;
        }
        return menuService.getMenuTreeByMenuIds(menuIds);
    }

    @Override
    public List<MenuTree> getMenuTreeByMenuIds(Set<String> menuIds) throws Exception {
        List<Menu> menus = baseMapper.selectList(
                new LambdaQueryWrapper<Menu>().in(Menu::getUnid, menuIds)
                        .eq(Menu::getStatus, 1)
        );

        //找到树根节点
        List<MenuTree> menuInfos = menus.stream()
                .filter(menu -> StringUtils.isEmpty(menu.getPCode()) || "NONE".equals(menu.getPCode()))
                .map(menu -> {
                    MenuTree menuTree = new MenuTree();
                    menuTree.setUnid(menu.getUnid());
                    menuTree.setCode(menu.getCode());
                    menuTree.setText(menu.getName());
                    menuTree.setIconCls(menu.getIcon());
                    menuTree.setPCode(menu.getPCode());
                    menuTree.setAttributes(menu.getUrl());
                    menuTree.setCreateTime(sp.format(menu.getCreateTime()));
                    return menuTree;
                }).collect(Collectors.toList());
        //进行轮询插入子节点
        menuInfos = menuInfos.stream().map(menuTree -> assembleMenuTree(menuTree, menus, new HashSet<>())).collect(Collectors.toList());
        return menuInfos;
    }

    @Override
    public List<MenuTree> getCheckMenuTree(Set<String> menuIds, Set<String> checkedMenuIds) throws Exception {
        List<Menu> menus = baseMapper.selectList(
                new LambdaQueryWrapper<Menu>().in(Menu::getUnid, menuIds)
                        .eq(Menu::getStatus, 1)
        );
        //找到树根节点
        List<MenuTree> menuInfos = menus.stream()
                .filter(menu -> StringUtils.isEmpty(menu.getPCode()) || "NONE".equals(menu.getPCode()))
                .map(menu -> {
                    MenuTree menuTree = copyMenuToMenuTree(menu);
                    if (checkedMenuIds.contains(menu.getUnid()) && !isExitsSonMenu(menuTree, menus)) {
                        //判断是否勾选
                        menuTree.setChecked(true);
                    }
                    return menuTree;
                })
                .collect(Collectors.toList());
        //进行轮询插入子节点
        menuInfos = menuInfos.stream().map(menuTree -> assembleMenuTree(menuTree, menus, checkedMenuIds)).collect(Collectors.toList());
        return menuInfos;
    }

    @Override
    public Set<String> getAllValidMenuIds() throws Exception {
        List<Menu> menus = baseMapper.selectList(
                new LambdaQueryWrapper<Menu>().eq(Menu::getStatus, 1)
        );
        return menus.stream().map(Menu::getUnid).collect(Collectors.toSet());
    }

    /***
     *
     * @param menuInfo 父级节点菜单信息
     * @param menus 所有菜单节点
     * @param checkedMenuIds 已勾选菜单ID(无则新new个对象即可)
     * @return 拼接父级节点的下级菜单返回
     */
    public MenuTree assembleMenuTree(MenuTree menuInfo, List<Menu> menus, Set<String> checkedMenuIds) {
        // 2022/6/10 将与上级节点相关的菜单拼接
        String currentCode = menuInfo.getCode();
        List<MenuTree> childrens = menus.stream()
                .filter(menu -> menu.getPCode().equals(currentCode))
                .map(menu -> {
                    MenuTree menuTree = copyMenuToMenuTree(menu);
                    if (checkedMenuIds.contains(menu.getUnid()) && !isExitsSonMenu(menuTree, menus)) {
                        //判断是否勾选
                        menuTree.setChecked(true);
                    }
                    // 2022/6/14 判断是否子节点是否还有下级节点
                    if (isExitsSonMenu(menuTree, menus)) {
                        menuTree = assembleMenuTree(menuTree, menus, checkedMenuIds);
                    }
                    return menuTree;
                })
                .collect(Collectors.toList());
        menuInfo.setChildren(childrens);
        return menuInfo;
    }

    /***
     * 判断是否有下级节点
     * @param menuInfo 当前节点
     * @param menus 所有节点数组
     * @return true or false
     */
    public boolean isExitsSonMenu(MenuTree menuInfo, List<Menu> menus) {
        return menus.stream().anyMatch(menu -> menuInfo.getCode().equals(menu.getPCode()));
    }


    /***
     * 复制menu数据到menutree
     * @param menu 菜单信息
     * @return 菜单树数组
     */
    public MenuTree copyMenuToMenuTree(Menu menu) {
        MenuTree menuTree = new MenuTree();
        menuTree.setUnid(menu.getUnid());
        menuTree.setCode(menu.getCode());
        menuTree.setText(menu.getName());
        menuTree.setIconCls(menu.getIcon());
        menuTree.setPCode(menu.getPCode());
        menuTree.setAttributes(menu.getUrl());
        menuTree.setCreateTime(sp.format(menu.getCreateTime()));
        return menuTree;
    }
}
