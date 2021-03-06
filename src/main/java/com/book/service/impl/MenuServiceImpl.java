package com.book.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.common.units.PageInfo;
import com.book.model.Menu;
import com.book.mapper.MenuMapper;
import com.book.model.MenuTree;
import com.book.service.IMenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuTree> getMenuTree() {
        String pCode = null;
        Integer status = 1;
        List<MenuTree> treeList = menuMapper.getParentMenuTree(pCode, status);
        List<MenuTree> removeTree = new ArrayList<>();
        List<MenuTree> removeTreeChild = new ArrayList<>();
        Set<String> treeSet = new HashSet<>();
        for (MenuTree tree : treeList) {
            pCode = tree.getCode();
            tree.setChildren(menuMapper.getParentMenuTree(pCode, status));
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
    public void getMenuInfoList(PageInfo pageInfo) {
        Page page = new Page(pageInfo.getNowpage(), pageInfo.getSize());
        List<Menu> list = menuMapper.getMenuInfoList(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public List<Menu> getParentMenuList() {
        return menuMapper.getParentMenuList();
    }
}
