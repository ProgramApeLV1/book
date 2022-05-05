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
 * 借出记录 前端控制器
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Controller
@RequestMapping("/outStockRecordCont")
public class OutStockRecordPageController {

    @GetMapping(value = "/addPage")
    public ModelAndView addPage(@RequestBody ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.BOOROWBOOKADD_PAGE);
        return modelAndView;
    }
}

