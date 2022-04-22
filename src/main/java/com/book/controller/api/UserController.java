package com.book.controller.api;


import com.book.common.base.BaseController;
import com.book.common.units.CookieUtils;
import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.common.units.StringUtil;
import com.book.controller.api.req.UpdatePwdRequest;
import com.book.model.User;
import com.book.model.vo.UserVo;
import com.book.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    public Object getUserList(Integer page, Integer rows, String userName) throws Exception {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("userName", userName);
        pageInfo.setCondition(condition);
        userService.getUserList(pageInfo);
        return pageInfo;
    }

    @PostMapping(value = "/add")
    public Object add(@RequestBody UserVo userVo) throws Exception {
        User user = new User();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String userCode = formatter.format(LocalDateTime.now()) + String.format("%04d", new Random().nextInt(9999));
        BeanUtils.copyProperties(userVo, user);
        //二次加密
        String password = DigestUtils.md5DigestAsHex(userVo.getPassword().getBytes());
        user.setPassword(password);
        user.setUserCode(userCode);
        user.setCreateTime(LocalDateTime.now());
        user.setStatus(1);
        userService.insert(user);
        return ResponseJson.success("用户新增成功");
    }

    @PostMapping(value = "/edit")
    public Object edit(@RequestBody User user) throws Exception {
        User curUser = userService.selectById(user.getId());
        if (Objects.isNull(curUser)) {
            return ResponseJson.error("未查到当前用户!");
        }
        user.setPassword(curUser.getPassword());
        userService.updateById(user);
        return ResponseJson.success("用户编辑成功");
    }

    @PostMapping(value = "/deleteWorkById")
    public Object deleteWorkById(String id) throws Exception {
        userService.deleteById(id);
        return ResponseJson.success("删除用户成功");
    }

    @PostMapping(value = "/updatePwdByUserId")
    public Object updatePwdByUserId(@RequestBody UpdatePwdRequest request) throws Exception {
        if (StringUtil.isEmpty(request.getUserId())) {
            return ResponseJson.error("用户id不能为空!");
        }
        if (StringUtil.isEmpty(request.getPassword())) {
            return ResponseJson.error("旧密码不能为空!");
        }
        if (StringUtil.isEmpty(request.getNewPassword())) {
            return ResponseJson.error("新密码不能为空!");
        }
        User curUser = userService.selectById(request.getUserId());
        if (Objects.isNull(curUser)) {
            return ResponseJson.error("未查到当前用户!");
        }
        //验证旧密码
        String password = DigestUtils.md5DigestAsHex(request.getPassword().getBytes());
        if (!password.equals(curUser.getPassword())) {
            return ResponseJson.error("旧密码验证错误!");
        }
        //二次加密
        String newPassword = DigestUtils.md5DigestAsHex(request.getNewPassword().getBytes());
        request.setNewPassword(newPassword);
        userService.updatePwdByUserId(request.getUserId(), request.getNewPassword());
        return ResponseJson.success("更新用户密码成功");
    }

    @GetMapping(value = "/userInfo")
    public ResponseJson userInfo(HttpServletRequest request) throws Exception {
        CookieUtils.getUid(request,"token");
        return ResponseJson.success();
    }
}

