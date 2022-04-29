package com.book.controller.api;


import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.service.IOutStockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
@RestController
@RequestMapping("/outStockRecordApi")
public class OutStockRecordController {

    @Autowired
    private IOutStockRecordService outStockRecordService;

    @GetMapping(value = "/getBoorowBookInfoList")
    public ResponseJson getBoorowBookInfoList(Integer page, Integer rows, String refCode, String outCode) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("refCode", refCode);
        condition.put("outCode", outCode);
        pageInfo.setCondition(condition);
        outStockRecordService.getBoorowBookInfoList(pageInfo);
        return ResponseJson.success(pageInfo);
    }
}

