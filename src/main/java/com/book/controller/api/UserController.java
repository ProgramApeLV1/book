package com.book.controller.api;


import com.book.common.base.BaseController;
import com.book.common.units.BeanUtil;
import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.controller.api.req.user.RequestUpdatePwd;
import com.book.controller.api.req.user.RequestUserAdd;
import com.book.controller.api.req.user.RequestUserEdit;
import com.book.model.User;
import com.book.model.UserRole;
import com.book.model.vo.UserVo;
import com.book.service.IUserRoleService;
import com.book.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @GetMapping(value = "/getUserList")
    public ResponseJson getUserList(Integer page, Integer rows, String userName) throws Exception {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("userName", userName);
        pageInfo.setCondition(condition);
        userService.getUserList(pageInfo);
        return ResponseJson.success(pageInfo);
    }

    @PostMapping(value = "/add")
    public ResponseJson add(@RequestBody @Valid RequestUserAdd requestUserAdd) throws Exception {
        User user = new User();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String userCode = formatter.format(LocalDateTime.now()) + String.format("%04d", new Random().nextInt(9999));
        BeanUtils.copyProperties(requestUserAdd, user);
        //二次加密
        String password = DigestUtils.md5DigestAsHex(requestUserAdd.getPassword().getBytes());
        user.setPassword(password);
        user.setUserCode(userCode);
        user.setCreateTime(LocalDateTime.now());
        user.setStatus(1);
        // 2022/5/6 保存用户信息
        userService.save(user);
        // 2022/5/6 绑定用户角色关系
        userRoleService.bindUserRole(requestUserAdd.getRoleIds(), requestUserAdd.getId());
        return ResponseJson.success("用户新增成功");
    }

    @PostMapping(value = "/edit")
    public ResponseJson edit(@RequestBody @Valid RequestUserEdit requestUserEdit) throws Exception {
        User curUser = userService.getById(requestUserEdit.getId());
        if (Objects.isNull(curUser)) {
            return ResponseJson.error("未查到当前用户!");
        }
        BeanUtil.convertBean2Bean(requestUserEdit, curUser);
        userService.updateById(curUser);
        // 绑定用户角色关系
        userRoleService.updateOrAddUserRole(requestUserEdit.getRoleIds(), curUser.getId());
        return ResponseJson.success("用户编辑成功");
    }

    @PostMapping(value = "/deleteWorkById")
    public ResponseJson deleteWorkById(String id) throws Exception {
        userService.removeById(id);
        userRoleService.removeAllUserRoleByUserId(id);
        return ResponseJson.success("删除用户成功");
    }

    @PostMapping(value = "/updatePwdByUserId")
    public ResponseJson updatePwdByUserId(@RequestBody @Valid RequestUpdatePwd request) throws Exception {
        User curUser = userService.getById(request.getUserId());
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
        UserVo userVo = userInfoByToken(request);
        return ResponseJson.success(userVo);
    }
}

