package com.book.controller.api;


import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.service.IInStockRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/inStockRecordApi")
public class InStockRecordController {

    @Autowired
    private IInStockRecordService inStockRecordService;

    @GetMapping(value = "/getReturnBookInfo")
    public ResponseJson getReturnBookInfo(Integer page, Integer rows, String refCode, String inCode) throws Exception {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("refCode", refCode);
        condition.put("inCode", inCode);
        pageInfo.setCondition(condition);
        inStockRecordService.getReturnBookInfo(pageInfo);
        return ResponseJson.success(pageInfo);
    }
}

