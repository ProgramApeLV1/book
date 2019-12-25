package com.book.common.base;

import com.book.common.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartController extends BaseController {

    @RequestMapping("/")
    public ModelAndView gotoLogin(ModelAndView model) {
        model.setViewName("login");
        return model;
    }

    @RequestMapping("/index")
    public ModelAndView gotoIndex(ModelAndView model) {
        model.setViewName("index");
        return model;
    }
}
