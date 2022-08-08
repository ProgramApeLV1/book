package com.book.controller.page;


import com.book.common.base.PagePathConstant;
import com.book.model.BookInfo;
import com.book.model.BookType;
import com.book.service.IBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/bookInfoCont")
public class BookInfoPageController {

    @Autowired
    private IBookInfoService bookInfoService;

    @GetMapping(value = "/addPage")
    public ModelAndView addPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.BOOK_INFO_ADD_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/editPage")
    public ModelAndView editPage(ModelAndView modelAndView, Integer id) {
        BookInfo bookInfo = bookInfoService.getById(id);
        modelAndView.addObject("bookInfo", bookInfo);
        modelAndView.setViewName(PagePathConstant.BOOK_INFO_EDIT_PAGE);
        return modelAndView;
    }
}

