package com.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.model.Role;
import com.book.model.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

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

    List<RoleVo> getRoleList(Page page, @Param(value = "condition") Map<String, Object> condition);
}
