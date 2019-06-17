package com.book.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.common.PageInfo;
import com.book.model.BookInfo;
import com.book.mapper.BookInfoMapper;
import com.book.model.Menu;
import com.book.model.vo.BookInfoVo;
import com.book.service.IBookInfoService;
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
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements IBookInfoService {

    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Override
    public void getBookInfoList(PageInfo pageInfo) {
        Page page = new Page(pageInfo.getNowpage(), pageInfo.getSize());
        List<BookInfoVo> list = bookInfoMapper.getBookInfoList(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
}
