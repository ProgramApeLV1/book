package com.book.service;

import com.book.common.units.PageInfo;
import com.book.model.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.book.model.vo.RoleVo;

import java.util.List;

/**
 * <p>
 * 角色管理表 服务类
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
public interface IRoleService extends IService<Role> {

    void getRoleList(PageInfo pageInfo);

    List<RoleVo> getRootRoleList();

}
