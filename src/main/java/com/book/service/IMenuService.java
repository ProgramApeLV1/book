package com.book.service;

import com.book.common.units.PageInfo;
import com.book.model.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.model.MenuTree;
import com.book.model.vo.MenuVo;

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

    MenuVo getCurMenuInfo(String unid);

    Menu getParentMenu(String pCode);
}
