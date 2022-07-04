package com.book.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.common.base.ApiCode;
import com.book.common.exception.BusinessException;
import com.book.common.units.JsonUtils;
import com.book.common.units.PageInfo;
import com.book.common.units.RedisClient;
import com.book.common.units.StringUtil;
import com.book.model.User;
import com.book.mapper.UserMapper;
import com.book.model.UserRole;
import com.book.model.vo.UserVo;
import com.book.service.IUserRoleService;
import com.book.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户登入 服务实现类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisClient redisClient;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public void getUserList(PageInfo pageInfo) {
        Page page = new Page(pageInfo.getNowpage(), pageInfo.getSize());
        List<UserVo> list = baseMapper.getUserList(page, pageInfo.getCondition());
        list = list.stream().map(userVo -> {
            String phone = StringUtil.phoneCutEncrypt(userVo.getPhone());
            String identity = StringUtil.identityCutEncrypt(userVo.getIdentity());
            userVo.setPhone(phone);
            userVo.setIdentity(identity);
            return userVo;
        }).collect(Collectors.toCollection(ArrayList::new));
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public void updatePwdByUserId(String userId, String password) {
        baseMapper.updatePwdByUserId(userId, password);
    }

    @Override
    public UserVo userInfoByToken(String token) throws Exception {
        UserVo userVo = new UserVo();
        String userinfo = redisClient.get(token);
        if (StringUtil.isEmpty(userinfo)) {
            throw new BusinessException(ApiCode.NOTEXIST_USERINFO);
        }
        // 2022/4/22 去数据库捞用户数据
        userVo = JsonUtils.convertJsonToObject(userinfo, UserVo.class);
        // 获取对应角色id数组
        Set<String> roleIds = userRoleService.getAllUserRoleByUserId(userVo.getId())
                .stream().map(UserRole::getRoleId).collect(Collectors.toSet());
        userVo.setRoleIds(roleIds);
        return userVo;
    }

    @Override
    public void extendTimeByToken(String token) throws Exception {

    }
}
