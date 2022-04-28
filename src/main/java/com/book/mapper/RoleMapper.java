package com.book.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.model.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.book.model.vo.RoleVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色管理表 Mapper 接口
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<RoleVo> getRoleList(Page page, Map<String, Object> condition);
}
