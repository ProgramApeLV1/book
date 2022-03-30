package com.book.manager.controller;


import com.book.common.units.PageInfo;
import com.book.service.IBookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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
public class BookTypeController {

    @Autowired
    private IBookTypeService bookTypeService;

    @RequestMapping(value = "/getBookTypeList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getBookTypeList(Integer page, Integer rows, String typeName) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("bookName", typeName);
        pageInfo.setCondition(condition);
        bookTypeService.getBookTypeList(pageInfo);
        return pageInfo;
    }

    @RequestMapping(value = "/addPage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addPage(@RequestBody ModelAndView modelAndView) {
        modelAndView.setViewName("/basic/bookTypeAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/editPage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editPage(@RequestBody ModelAndView modelAndView, Integer id) {
        modelAndView.addObject("id", id);
        modelAndView.setViewName("/basic/bookTypeEdit");
        return modelAndView;
    }

}

