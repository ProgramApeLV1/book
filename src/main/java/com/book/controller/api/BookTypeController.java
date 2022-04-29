package com.book.controller.api;


import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.service.IBookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping("/bookTypeApi")
public class BookTypeController {

    @Autowired
    private IBookTypeService bookTypeService;

    @GetMapping(value = "/getBookTypeList")
    public ResponseJson getBookTypeList(Integer page, Integer rows, String typeName) throws Exception {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("bookName", typeName);
        pageInfo.setCondition(condition);
        bookTypeService.getBookTypeList(pageInfo);
        return ResponseJson.success(pageInfo);
    }
}

