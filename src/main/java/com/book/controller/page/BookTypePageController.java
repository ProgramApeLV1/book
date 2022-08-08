package com.book.controller.page;


import com.book.common.base.BaseController;
import com.book.common.base.PagePathConstant;
import com.book.model.BookType;
import com.book.service.IBookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
public class BookTypePageController extends BaseController {

    @Autowired
    private IBookTypeService bookTypeService;

    @GetMapping(value = "/addPage")
    public ModelAndView addPage(ModelAndView modelAndView) {
        modelAndView.setViewName(PagePathConstant.BOOK_TYPE_ADD_PAGE);
        return modelAndView;
    }

    @GetMapping(value = "/editPage")
    public ModelAndView editPage(ModelAndView modelAndView, Integer id) {
        BookType bookType = bookTypeService.getById(id);
        modelAndView.addObject("bookType", bookType);
        modelAndView.setViewName(PagePathConstant.BOOK_TYPE_EDIT_PAGE);
        return modelAndView;
    }

}

