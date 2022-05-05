package com.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.model.Menu;
import com.book.model.MenuTree;
import com.book.model.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源 Mapper 接口
 * </p>
 *
 * @author wyh123
 * @since 2019-01-02
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuTree> getParentMenuTree(@Param("pCode") String pCode, @Param("status") Integer status);


    List<MenuVo> getMenuInfoList(Page page, @Param(value = "condition") Map<String, Object> condition);

    List<Menu> getParentMenuList();

    Menu getMenuInfo(@Param("pCode") String pCode);
}
