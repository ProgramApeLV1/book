package com.book.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.common.PageInfo;
import com.book.model.BookType;
import com.book.mapper.BookTypeMapper;
import com.book.model.vo.BookTypeVo;
import com.book.service.IBookTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 书本基础信息 服务实现类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Service
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements IBookTypeService {

    @Autowired
    private BookTypeMapper bookTypeMapper;

    @Override
    public void getBookTypeList(PageInfo pageInfo) {
        Page page = new Page(pageInfo.getNowpage(), pageInfo.getSize());
        List<BookTypeVo> list = bookTypeMapper.getBookTypeList(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
}
