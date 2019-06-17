package com.book.service;

import com.book.common.PageInfo;
import com.book.model.Menu;
import com.baomidou.mybatisplus.service.IService;
import com.book.model.MenuTree;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-02
 */
public interface IMenuService extends IService<Menu> {

    List<MenuTree> getMenuTree();

    void getMenuInfoList(PageInfo pageInfo);

    List<Menu> getParentMenuList();

}
