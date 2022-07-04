package com.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.book.common.units.PageInfo;
import com.book.model.User;
import com.book.model.vo.UserVo;

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

    void updatePwdByUserId(String userId, String password);

    UserVo userInfoByToken(String token) throws Exception;

    void extendTimeByToken(String token) throws Exception;
}
