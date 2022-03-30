package com.book.controller.api;


import com.book.common.base.BaseController;
import com.book.common.units.PageInfo;
import com.book.model.User;
import com.book.model.vo.UserVo;
import com.book.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.book.common.base.Constant.ACTION_ERROR;
import static com.book.common.base.Constant.ACTION_SUCCESS;

/**
 * <p>
 * 用户登入 前端控制器
 * </p>
 *
 * @author wyh123
 * @since 2018-12-22
 */
@RestController
@RequestMapping("/userApi")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/getUserList")
    public Object getUserList(Integer page, Integer rows, String userName) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("userName", userName);
        pageInfo.setCondition(condition);
        userService.getUserList(pageInfo);
        return pageInfo;
    }

    @PostMapping(value = "/add")
    public Object add(@RequestBody UserVo userVo) {
        User user = new User();
        String userCode = "USER" + System.currentTimeMillis();
        BeanUtils.copyProperties(userVo, user);
        user.setUserCode(userCode);
        user.setCreateTime(new Date());
        user.setStatus(1);
        try {
            userService.insert(user);
            return responseSuccess(ACTION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return responseError(ACTION_ERROR);
        }
    }

    @PostMapping(value = "/edit")
    public Object edit(@RequestBody User user) {
        try {
            userService.updateById(user);
            return responseSuccess(ACTION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return responseError(ACTION_ERROR);
        }
    }

    @PostMapping(value = "/deleteWorkById")
    public Object deleteWorkById(Integer id) {
        try {
            userService.deleteById(id);
            return responseSuccess(ACTION_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return responseError(ACTION_ERROR);
        }
    }

}

