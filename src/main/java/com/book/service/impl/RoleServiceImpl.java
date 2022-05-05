package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.common.units.BeanUtil;
import com.book.common.units.PageInfo;
import com.book.model.Role;
import com.book.mapper.RoleMapper;
import com.book.model.vo.RoleVo;
import com.book.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色管理表 服务实现类
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public void getRoleList(PageInfo pageInfo) {
        Page page = new Page(pageInfo.getNowpage(), pageInfo.getSize());
        List<RoleVo> list = baseMapper.getRoleList(page, pageInfo.getCondition());
//        list = list.stream().map(roleVo -> {
//            roleVo.setCreateTime(StringUtil.localDateTimeStr(roleVo.getCreateTime()));
//            return roleVo;
//        }).collect(Collectors.toCollection(ArrayList::new));
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public List<RoleVo> getRootRoleList() {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        List<Role> roles = baseMapper.selectList(wrapper.eq(Role::getPid, "-1").eq(Role::getStatus, 1));
        return roles.stream().map(role -> {
            RoleVo roleVo = new RoleVo();
            BeanUtil.convertBean2Bean(role, roleVo);
            return roleVo;
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}
