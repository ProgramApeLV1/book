package com.book.common.base;

import com.book.common.base.BaseController;
import com.book.common.units.StringUtil;
import com.book.model.User;
import com.book.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/loginCont")
public class LoginController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    @ResponseBody
    private Object userLogin(String loginName, String password) {
        if (StringUtils.isBlank(loginName)) {
            return responseSuccess("账号不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return responseSuccess("密码不能为空");
        }
        List<User> userList = getAllUser();
        Map<String, User> userMap = new HashMap<>();
        for (User user : userList) {
            userMap.put(user.getLoginName(), user);
        }
        if (!userMap.containsKey(loginName.trim())) {
            return responseError("该用户不存在");
        }
        //二次md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        String pwd = userMap.get(loginName).getPassword();
        if (!password.equals(pwd)) {
            return responseError("密码错误");
        }
        User user = userMap.get(loginName);
        user.setPhone(StringUtil.phoneCutEncrypt(user.getPhone()));
        user.setIdentity(StringUtil.identityCutEncrypt(user.getIdentity()));
        return responseSuccess("登录成功", user);
    }

    /**
     * @description: 用户退出
     * @author: wangyh
     * @time: 2018-07-03 10:28:59
     */
    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView model) {
//    	request().getSession(false).removeAttribute(Constant.X_USER_SESSION);
        model.setViewName("login");
        return model;
    }
}
