package com.book.service;

import com.book.model.User;
import com.book.model.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关系表 服务类
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
public interface IUserRoleService extends IService<UserRole> {

    /***
     * 返回是否有对应绑定关系
     * @param roleId 角色id
     * @param userId 用户id
     * @return true or false
     * @throws Exception 异常
     */
    UserRole isExitsUserRole(String roleId, String userId) throws Exception;

    /***
     * 获取单个用户下所有的用户角色绑定关系
     * @param userId 用户Id
     * @return 关系数组
     * @throws Exception 异常
     */
    List<UserRole> getAllUserRoleByUserId(String userId) throws Exception;

    /***
     * 绑定用户角色关系
     * @param roleId 角色id
     * @param userId 用户Id
     * @throws Exception 异常
     */
    void bindUserRole(String roleId, String userId) throws Exception;

    /***
     * 绑定用户角色关系
     * @param roleIds 角色ids
     * @param userId 用户Id
     * @throws Exception 异常
     */
    void bindUserRole(List<String> roleIds, String userId) throws Exception;

    /***
     * 更新/新增 用户角色关系
     * @param roleIds 角色ids
     * @param userId 用户Id
     * @throws Exception 异常
     */
    void updateOrAddUserRole(List<String> roleIds, String userId) throws Exception;

    /***
     * 删除单个用户角色关系
     * @param roleId 角色id
     * @param userId 用户Id
     * @throws Exception 异常
     */
    void removeUserRole(String roleId, String userId) throws Exception;

    /***
     * 删除单个用户的所有角色关系
     * @param userId 用户Id
     * @throws Exception 异常
     */
    void removeAllUserRoleByUserId(String userId) throws Exception;
}
