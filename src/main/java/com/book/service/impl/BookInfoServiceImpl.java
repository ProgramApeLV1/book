package com.book.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.common.units.PageInfo;
import com.book.model.BookInfo;
import com.book.mapper.BookInfoMapper;
import com.book.model.vo.BookInfoVo;
import com.book.service.IBookInfoService;
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


    @Override
    public void getBookInfoList(PageInfo pageInfo) {
        Page page = new Page(pageInfo.getNowpage(), pageInfo.getSize());
        List<BookInfoVo> list = baseMapper.getBookInfoList(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public BookInfo getBookInfoByCode(String code) {
        return baseMapper.selectOne(new LambdaQueryWrapper<BookInfo>()
                .eq(BookInfo::getBookCode, code));
    }
}
