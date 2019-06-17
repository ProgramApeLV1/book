package com.book.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.model.Menu;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.book.model.MenuTree;
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

    List<MenuTree> getParentMenuTree(@Param("pCode") String pCode,@Param("status") Integer status);


    List<Menu> getMenuInfoList(Page page, Map<String, Object> condition);

    List<Menu> getParentMenuList();

}
