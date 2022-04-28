package com.book.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.common.units.PageInfo;
import com.book.common.units.StringUtil;
import com.book.model.Role;
import com.book.mapper.RoleMapper;
import com.book.model.vo.RoleVo;
import com.book.model.vo.UserVo;
import com.book.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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
//            return roleVo;
//        }).collect(Collectors.toCollection(ArrayList::new));
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
}
