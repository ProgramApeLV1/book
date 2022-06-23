package com.book.controller.page;

import com.book.common.base.BaseController;
import com.book.common.base.PagePathConstant;
import com.book.common.units.PageInfo;
import com.book.common.units.StringUtils;
import com.book.model.Menu;
import com.book.model.MenuTree;
import com.book.model.vo.MenuVo;
import com.book.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/menuCont")
public class MenuPageController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @GetMapping(value = "/gotoBookInfoPage")
    public ModelAndView gotoBookInfoPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.BOOK_INFO_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoBookTypePage")
    public ModelAndView gotoBookTypePage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.BOOK_TYPE_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoBoorowBookPage")
    public ModelAndView gotoBoorowBookPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.BOOROW_BOOK_INFO_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoReturnBookPage")
    public ModelAndView gotoReturnBookPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.RETURN_BOOK_INFO_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoMenuInfoPage")
    public ModelAndView gotoMenuInfoPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.MENU_LIST_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoMenuInfoAddPage")
    public ModelAndView gotoMenuInfoAddPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.MENU_ADD_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoMenuInfoEditPage")
    public ModelAndView gotoMenuInfoAddPage(ModelAndView modelAndView, String unid) throws Exception {
        MenuVo menu = menuService.getCurMenuInfo(unid);
        modelAndView.addObject("menu", menu);
        modelAndView.setViewName(PagePathConstant.MENU_EDIT_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoUserInfoPage")
    public ModelAndView gotoUserInfoPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.USER_LIST_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/gotoRoleInfoPage")
    public ModelAndView gotoRoleInfoPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.ROLE_LIST_PAGE);
        return modelAndView;
    }
}
