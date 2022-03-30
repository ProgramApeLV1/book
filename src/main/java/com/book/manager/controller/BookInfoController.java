package com.book.manager.controller;


import com.book.common.units.PageInfo;
import com.book.service.IBookInfoService;
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
@RequestMapping("/bookInfoCont")
public class BookInfoController {

    @Autowired
    private IBookInfoService bookInfoService;

    @RequestMapping(value = "/getBookInfoList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getBookInfoList(Integer page, Integer rows, String bookName) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("bookName", bookName);
        pageInfo.setCondition(condition);
        bookInfoService.getBookInfoList(pageInfo);
        return pageInfo;
    }

    @RequestMapping(value = "/addPage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addPage(@RequestBody ModelAndView modelAndView) {
        modelAndView.setViewName("/basic/bookInfoAdd");
        return modelAndView;
    }

    @RequestMapping(value = "/editPage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView editPage(@RequestBody ModelAndView modelAndView, Integer id) {
        modelAndView.addObject("id", id);
        modelAndView.setViewName("/basic/bookInfoEdit");
        return modelAndView;
    }
}

