package com.book.common.base;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.alibaba.fastjson.JSON;
import com.book.common.units.CookieUtils;
import com.book.common.units.ResponseJson;
import com.book.common.units.StringUtil;
import com.book.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.book.common.base.Constant.HALF_HOUR;
import static com.book.common.base.Constant.TOKEN_NAME;

@Slf4j
@Controller
@RequestMapping("/loginCont")
public class LoginController extends BaseController {

    private LineCaptcha lineCaptcha;

    @PostMapping(value = "/userLogin")
    @ResponseBody
    private ResponseJson userLogin(
            HttpServletResponse response,
            @RequestParam String loginName,
            @RequestParam String password
    ) throws Exception {
        if (StringUtils.isBlank(loginName)) {
            return ResponseJson.error("账号不能为空");
        }
        if (StringUtils.isBlank(password)) {
            return ResponseJson.error("密码不能为空");
        }
        List<User> userList = getAllUser();
        Map<String, User> userMap = new HashMap<>();
        for (User user : userList) {
            userMap.put(user.getLoginName(), user);
        }
        if (!userMap.containsKey(loginName.trim())) {
            return ResponseJson.error("该用户不存在");
        }
        //二次md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        String pwd = userMap.get(loginName).getPassword();
        if (!password.equals(pwd)) {
            return ResponseJson.error("密码错误");
        }
        User user = userMap.get(loginName);
        user.setPhone(StringUtil.phoneCutEncrypt(user.getPhone()));
        user.setIdentity(StringUtil.identityCutEncrypt(user.getIdentity()));
        // 将登录的用户信息存入缓存
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisClient.set(token, JSON.toJSONString(user), HALF_HOUR);
        CookieUtils.addCookie(response, TOKEN_NAME, token, HALF_HOUR.intValue());
        return ResponseJson.success("登录成功", user);
    }

    /**
     * @description: 用户退出
     * @author: wangyh
     * @time: 2018-07-03 10:28:59
     */
    @PostMapping("/logout")
    @ResponseBody
    public ResponseJson logout(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.removeCookie(response, TOKEN_NAME);
        return ResponseJson.success();
    }

    @GetMapping("/getVeriCodeImg")
    public void getVeriCodeImg(HttpServletResponse response, HttpSession session) {
        // 随机生成 4 位验证码
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        // 定义图片的显示大小
        lineCaptcha = CaptchaUtil.createLineCaptcha(100, 30);
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        PrintWriter out = null;
        try {
            // 调用父类的 setGenerator() 方法，设置验证码的类型
            lineCaptcha.setGenerator(randomGenerator);
            // 输出到页面
            lineCaptcha.write(response.getOutputStream());
            // 打印日志
            log.info("生成的验证码:{}", lineCaptcha.getCode());
            // 关闭流
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return responseSuccess("生成验证码图片成功!");
    }
}
