package com.book.controller.page;


import com.book.common.base.PagePathConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * <p>
 * 书本基础信息 前端控制器
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Controller
@RequestMapping("/bookTypeCont")
public class BookTypePageController {

    @GetMapping(value = "/addPage")
    public ModelAndView addPage(@RequestBody ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.BOOKTYPEADD_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/editPage")
    public ModelAndView editPage(@RequestBody ModelAndView modelAndView, Integer id) {
        modelAndView.addObject("id", id);
        modelAndView.setViewName(PagePathConstant.BOOKTYPEEDIT_PAGE);
        return modelAndView;
    }

}

