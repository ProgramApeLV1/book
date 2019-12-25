package com.book.service;

import com.book.common.units.PageInfo;
import com.book.model.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户登入 服务类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
public interface IUserService extends IService<User> {

    void getUserList(PageInfo pageInfo);
}
