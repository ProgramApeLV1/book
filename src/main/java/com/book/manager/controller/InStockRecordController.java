package com.book.controller.controller;


import com.book.common.units.PageInfo;
import com.book.service.IInStockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 归还记录 前端控制器
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Controller
@RequestMapping("/inStockRecordCont")
public class InStockRecordController {

    @Autowired
    private IInStockRecordService inStockRecordService;

    @RequestMapping(value = "/getReturnBookInfo", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object getReturnBookInfo(Integer page, Integer rows, String refCode, String inCode) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("refCode", refCode);
        condition.put("inCode", inCode);
        pageInfo.setCondition(condition);
        inStockRecordService.getReturnBookInfo(pageInfo);
        return pageInfo;
    }
}

