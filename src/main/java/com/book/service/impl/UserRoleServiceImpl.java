package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.model.User;
import com.book.model.UserRole;
import com.book.mapper.UserRoleMapper;
import com.book.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public UserRole isExitsUserRole(String roleId, String userId) throws Exception {
        // 2022/5/6 判断是否有该用户角色关系
        return baseMapper.selectOne(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getRoleId, roleId)
                .eq(UserRole::getUserId, userId));
    }

    @Override
    public List<UserRole> getAllUserRoleByUserId(String userId) throws Exception {
        return baseMapper.selectList(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, userId));
    }

    @Override
    public void bindUserRole(String roleId, String userId) throws Exception {
        UserRole userRole = isExitsUserRole(roleId, userId);
        // 2022/5/6 有则不新增绑定操作，无则新增
        if (Objects.isNull(userRole)) {
            userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            baseMapper.insert(userRole);
        }
    }

    @Override
    public void bindUserRole(List<String> roleIds, String userId) throws Exception {
        updateOrAddUserRole(roleIds, userId);
    }

    @Override
    public void updateOrAddUserRole(List<String> roleIds, String userId) throws Exception {
        // 2022/5/6 获取传入的角色id数组的所有用户角色关系
        List<UserRole> userRoles = baseMapper.selectList(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, userId));
        // 2022/5/6 比较是否传入的关系有需要增加或删除的
        if (!CollectionUtils.isEmpty(userRoles)) {
            // 2022/5/6 删除不存在于roleIds的数据
            Set<String> roleIdsSet = new HashSet<>(roleIds);
            List<String> deleteList = new ArrayList<>();
            userRoles.forEach(userRole -> {
                if (!roleIdsSet.contains(userRole.getRoleId())) {
                    deleteList.add(userRole.getId());
                }
            });
            if(!CollectionUtils.isEmpty(deleteList)) baseMapper.deleteBatchIds(deleteList);
            // 2022/5/6 比对后进行新增和删除
            Map<String, UserRole> userRoleMap = userRoles.stream().collect(Collectors.toMap(UserRole::getRoleId, UserRole -> UserRole));
            roleIds.forEach(roleId -> {
                if (!userRoleMap.containsKey(roleId)) {
                    UserRole userRole = new UserRole();
                    userRole.setUserId(userId);
                    userRole.setRoleId(roleId);
                    baseMapper.insert(userRole);
                }
            });
        } else {
            // 2022/5/6 进行新增
            roleIds.forEach(roleId -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                baseMapper.insert(userRole);
            });
        }
    }

    @Override
    public void removeUserRole(String roleId, String userId) throws Exception {
        UserRole userRole = isExitsUserRole(roleId, userId);
        // 2022/5/6 有则不新增绑定操作，无则新增
        if (!Objects.isNull(userRole)) {
            baseMapper.delete(new LambdaQueryWrapper<UserRole>().eq(UserRole::getId, userRole.getId()));
        }
    }

    @Override
    public void removeAllUserRoleByUserId(String userId) throws Exception {
        List<UserRole> userRoles = getAllUserRoleByUserId(userId);
        if (!CollectionUtils.isEmpty(userRoles)) {
            baseMapper.deleteBatchIds(userRoles.stream()
                    .map(UserRole::getId).collect(Collectors.toCollection(ArrayList::new)));
        }
    }
}
