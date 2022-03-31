package com.book.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.model.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.book.model.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户登入 Mapper 接口
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
public interface UserMapper extends BaseMapper<User> {

    List<UserVo> getUserList(Page page, Map<String, Object> condition);

    void updatePwdByUserId(@Param("id") String id, @Param("password") String password);
}
