package com.book.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.common.units.PageInfo;
import com.book.common.units.StringUtil;
import com.book.model.User;
import com.book.mapper.UserMapper;
import com.book.model.vo.UserVo;
import com.book.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
}
