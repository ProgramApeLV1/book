package com.book.controller.page;


import com.book.common.base.PagePathConstant;
import com.book.model.Role;
import com.book.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 * 角色管理表 前端控制器
 * </p>
 *
 * @author wyh123
 * @since 2019-12-25
 */
@Controller
@RequestMapping("/roleCont")
public class RolePageController {

    @Autowired
    private IRoleService roleService;

    @GetMapping(value = "/gotoRoleInfoAddPage")
    public ModelAndView gotoRoleInfoAddPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.ROLEADD_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoRoleInfoEditPage")
    public ModelAndView gotoRoleInfoEditPage(ModelAndView modelAndView, String id) {
        Role role = roleService.getById(id);
        modelAndView.addObject("role", role);
        modelAndView.setViewName(PagePathConstant.ROLEEDIT_PAGE);
        return modelAndView;
    }
}

