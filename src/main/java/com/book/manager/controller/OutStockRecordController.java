package com.book.controller.controller;


import com.book.common.units.PageInfo;
import com.book.service.IOutStockRecordService;
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
 * 借出记录 前端控制器
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Controller
@RequestMapping("/outStockRecordCont")
public class OutStockRecordController {

    @Autowired
    private IOutStockRecordService outStockRecordService;

    @RequestMapping(value = "/getBoorowBookInfoList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getBoorowBookInfoList(Integer page, Integer rows, String refCode, String outCode) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("refCode", refCode);
        condition.put("outCode", outCode);
        pageInfo.setCondition(condition);
        outStockRecordService.getBoorowBookInfoList(pageInfo);
        return pageInfo;
    }

    @RequestMapping(value = "/addPage", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addPage(@RequestBody ModelAndView modelAndView) {
        modelAndView.setViewName("/bookBoorow/boorowBookInfo");
        return modelAndView;
    }
}

