package com.book.controller.api;


import cn.hutool.core.bean.BeanUtil;
import com.book.common.base.ApiCode;
import com.book.common.base.BaseController;
import com.book.common.exception.BusinessException;
import com.book.common.units.BeanUtils;
import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.controller.api.req.bookInfo.RequestBookInfoAdd;
import com.book.controller.api.req.bookInfo.RequestBookInfoEdit;
import com.book.controller.api.req.bookType.RequestBookTypeAdd;
import com.book.controller.api.req.bookType.RequestBookTypeEdit;
import com.book.model.BookInfo;
import com.book.model.BookType;
import com.book.service.IBookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 书本基础信息 前端控制器
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@RestController
@RequestMapping("/bookInfoApi")
public class BookInfoController extends BaseController {

    @Autowired
    private IBookInfoService bookInfoService;

    @GetMapping(value = "/getBookInfoList")
    public ResponseJson getBookInfoList(Integer page, Integer rows, String bookName) throws Exception {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = new HashMap<>();
        condition.put("bookName", bookName);
        pageInfo.setCondition(condition);
        bookInfoService.getBookInfoList(pageInfo);
        return ResponseJson.success(pageInfo);
    }

    @PostMapping(value = "/add")
    public ResponseJson add(@RequestBody @Valid RequestBookInfoAdd request) throws Exception {
        BookInfo bookInfo = bookInfoService.getBookInfoByCode(request.getBookCode());
        if (!Objects.isNull(bookInfo)) {
            throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "书本编码已存在");
        }
        BookInfo newBookInfo = new BookInfo();
        BeanUtil.copyProperties(request, newBookInfo);

        bookInfoService.save(newBookInfo);
        return ResponseJson.success("新增成功");
    }

    @PutMapping(value = "/edit")
    public ResponseJson edit(@RequestBody @Valid RequestBookInfoEdit request) throws Exception {
        BookInfo bookInfo = bookInfoService.getById(request.getId());
        if (Objects.isNull(bookInfo)) {
            throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "对应书本信息不存在");
        }
        BeanUtil.copyProperties(request, bookInfo);

        bookInfoService.updateById(bookInfo);
        return ResponseJson.success();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseJson delete(@PathVariable("id") String id) throws Exception {
        BookInfo bookInfo = bookInfoService.getById(id);
        if (Objects.isNull(bookInfo)) throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "书本信息不存在");
        bookInfoService.removeById(id);
        return ResponseJson.success();
    }
}

