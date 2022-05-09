package com.book.controller.page;


import com.book.common.base.BaseController;
import com.book.common.base.PagePathConstant;
import com.book.common.units.BeanUtil;
import com.book.common.units.StringUtil;
import com.book.model.User;
import com.book.model.UserRole;
import com.book.model.vo.UserVo;
import com.book.service.IUserRoleService;
import com.book.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户登入 前端控制器
 * </p>
 *
 * @author wyh123
 * @since 2018-12-22
 */
@Controller
@RequestMapping("/userCont")
public class UserPageController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @GetMapping(value = "/gotoUserInfoAddPage")
    public ModelAndView gotoUserInfoAddPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.USERADD_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoUserInfoEditPage")
    public ModelAndView gotoUserInfoEditPage(ModelAndView modelAndView, String id) throws Exception {
        User user = userService.getById(id);
        String phone = StringUtil.phoneCutEncrypt(user.getPhone());
        String identity = StringUtil.identityCutEncrypt(user.getIdentity());
        user.setPhone(phone);
        user.setIdentity(identity);
        UserVo userVo = new UserVo();
        BeanUtil.convertBean2Bean(user, userVo);
        userVo.setRoleIds(userRoleService.getAllUserRoleByUserId(user.getId()).stream()
                .map(UserRole::getRoleId).collect(Collectors.toCollection(ArrayList::new)));
        modelAndView.addObject("user", userVo);
        modelAndView.setViewName(PagePathConstant.USEREDIT_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoEditPwdPage")
    public ModelAndView gotoEditPwdPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.EDITPWD_PAGE);
        return modelAndView;
    }

}

