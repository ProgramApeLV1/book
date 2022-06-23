package com.book.controller.page;


import com.book.common.base.PagePathConstant;
import com.book.model.Role;
import com.book.model.vo.RoleVo;
import com.book.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;

import static com.book.common.base.Constant.DATE_FORMAT_NYRSFM;

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
        modelAndView.setViewName(PagePathConstant.ROLE_ADD_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoRoleInfoEditPage")
    public ModelAndView gotoRoleInfoEditPage(ModelAndView modelAndView, String id) {
        Role role = roleService.getById(id);
        modelAndView.addObject("role", role);
        modelAndView.setViewName(PagePathConstant.ROLE_EDIT_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoRoleMenuConfigPage")
    public ModelAndView gotoRoleMenuConfigPage(ModelAndView modelAndView, String id) {
        Role role = roleService.getById(id);
        RoleVo roleVo = new RoleVo();
        roleVo.setUnid(role.getUnid());
        roleVo.setPid(role.getPid());
        roleVo.setRoleName(role.getRoleName());
        roleVo.setStatus(role.getStatus());
        roleVo.setCreateTime(DateTimeFormatter.ofPattern(DATE_FORMAT_NYRSFM).format(role.getCreateTime()));
        modelAndView.addObject("role", roleVo);
        modelAndView.setViewName(PagePathConstant.ROLE_MENU_CONFIG_PAGE);
        return modelAndView;
    }
}

