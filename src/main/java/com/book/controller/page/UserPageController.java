package com.book.controller.page;


import com.book.common.base.BaseController;
import com.book.common.base.PagePathConstant;
import com.book.common.units.StringUtil;
import com.book.model.User;
import com.book.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping(value = "/gotoUserInfoAddPage")
    public ModelAndView gotoUserInfoAddPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.SYSTEM_USER.concat(PagePathConstant.USERADD));
        return modelAndView;
    }

    @GetMapping(value = "/gotoUserInfoEditPage")
    public ModelAndView gotoUserInfoEditPage(ModelAndView modelAndView, String id) {
        User user = userService.selectById(id);
        String phone = StringUtil.phoneCutEncrypt(user.getPhone());
        String identity = StringUtil.identityCutEncrypt(user.getIdentity());
        user.setPhone(phone);
        user.setIdentity(identity);
        modelAndView.addObject("user", user);
        modelAndView.setViewName(PagePathConstant.SYSTEM_USER.concat(PagePathConstant.USEREDIT));
        return modelAndView;
    }

    @GetMapping(value = "/gotoEditPwdPage")
    public ModelAndView gotoEditPwdPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.SYSTEM_USER.concat(PagePathConstant.EDITPWD));
        return modelAndView;
    }

}

