package com.book.service;

import com.book.common.units.PageInfo;
import com.book.model.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.model.MenuTree;
import com.book.model.User;
import com.book.model.vo.MenuVo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-02
 */
public interface IMenuService extends IService<Menu> {

    /***
     * 获取菜单树
     * @return 菜单树数组
     * @throws Exception 异常
     */
    List<MenuTree> getMenuTree() throws Exception;

    /***
     * 获取菜单树
     * @param menuIds 菜单id数组
     * @return 菜单树数组
     * @throws Exception 异常
     */
    List<MenuTree> getMenuTree(Set<String> menuIds) throws Exception;

    /***
     * 获取菜单信息数组（分页）
     * @param pageInfo 分页对象
     * @throws Exception 异常
     */
    void getMenuInfoList(PageInfo pageInfo) throws Exception;

    /***
     * 获取顶级菜单数组
     * @return 菜单数组
     * @throws Exception 异常
     */
    List<Menu> getParentMenuList() throws Exception;

    /***
     * 获取当前菜单信息
     * @param unid 菜单id
     * @return 菜单Vo类
     * @throws Exception 异常
     */
    MenuVo getCurMenuInfo(String unid) throws Exception;

    /***
     * 获取上级菜单信息
     * @param pCode 父级菜单编码
     * @return 父级菜单信息
     * @throws Exception 异常
     */
    Menu getParentMenu(String pCode) throws Exception;

    /***
     * 根据用户获取菜单树
     * @param user 用户信息
     * @return 菜单树
     * @throws Exception 异常
     */
    List<MenuTree> getMenuTreeByUser(User user) throws Exception;

    /***
     * 根据菜单Id数组 获取菜单树
     *
     * @param menuIds 菜单ID数组
     * @return 组成菜单树
     * @throws Exception 异常
     */
    List<MenuTree> getMenuTreeByMenuIds(Set<String> menuIds) throws Exception;

    /***
     * 将所有菜单id整合成树，并标记每个节点是否已选
     *
     * @param menuIds 所有菜单ID数组
     * @param checkedMenuIds 已选菜单ID数组
     * @return 组成菜单树
     * @throws Exception 异常
     */
    List<MenuTree> getCheckMenuTree(Set<String> menuIds, Set<String> checkedMenuIds) throws Exception;

    /***
     * 获取状态为有效的菜单Id
     * @return 菜单数组
     */
    Set<String> getAllValidMenuIds() throws Exception;
}
