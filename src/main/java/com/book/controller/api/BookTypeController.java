package com.book.controller.api;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.book.common.base.ApiCode;
import com.book.common.base.BaseController;
import com.book.common.exception.BusinessException;
import com.book.common.units.PageInfo;
import com.book.common.units.ResponseJson;
import com.book.controller.api.req.bookType.RequestBookTypeAdd;
import com.book.controller.api.req.bookType.RequestBookTypeEdit;
import com.book.controller.api.req.user.RequestUserAdd;
import com.book.model.BookType;
import com.book.model.Role;
import com.book.service.IBookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

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
public class BookTypeController extends BaseController {

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

    @PostMapping(value = "/add")
    public ResponseJson add(@RequestBody @Valid RequestBookTypeAdd requestBookTypeAdd) throws Exception {
        BookType bookType = bookTypeService.getBookTypeByCode(requestBookTypeAdd.getCode());
        if (!Objects.isNull(bookType)) {
            throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "书本编码已存在");
        }
        BookType newBookType = new BookType();
        newBookType.setCode(requestBookTypeAdd.getCode());
        newBookType.setTypeName(requestBookTypeAdd.getTypeName());
        newBookType.setStatus(requestBookTypeAdd.getStatus());
        newBookType.setCreateTime(new Date());
        bookTypeService.save(newBookType);
        return ResponseJson.success("新增成功");
    }

    @PutMapping(value = "/edit")
    public ResponseJson edit(@RequestBody @Valid RequestBookTypeEdit requestBookTypeEdit) throws Exception {
        BookType bookType = bookTypeService.getById(requestBookTypeEdit.getId());
        if (Objects.isNull(bookType)) {
            throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "对应书本类型不存在");
        }
        bookType.setCode(requestBookTypeEdit.getCode());
        bookType.setTypeName(requestBookTypeEdit.getTypeName());
        bookType.setStatus(requestBookTypeEdit.getStatus());
        bookTypeService.updateById(bookType);
        return ResponseJson.success();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseJson delete(@PathVariable("id") String id) throws Exception {
        BookType bookType = bookTypeService.getById(id);
        if (Objects.isNull(bookType)) throw new BusinessException(ApiCode.REQUEST_ERROR.getCode(), "书本类型不存在");
        bookTypeService.removeById(id);
        return ResponseJson.success();
    }

    @GetMapping(value = "/getTypeList")
    public ResponseJson getTypeList() throws Exception {
        List<BookType> bookTypeList = bookTypeService.list(
                new LambdaQueryWrapper<BookType>()
                        .eq(BookType::getStatus, 1)
        );
        return ResponseJson.success(bookTypeList);
    }
}

