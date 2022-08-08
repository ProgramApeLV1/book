package com.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.common.units.PageInfo;
import com.book.model.BookType;
import com.book.mapper.BookTypeMapper;
import com.book.model.vo.BookTypeVo;
import com.book.service.IBookTypeService;
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


    @Override
    public void getBookTypeList(PageInfo pageInfo) throws Exception {
        Page page = new Page(pageInfo.getNowpage(), pageInfo.getSize());
        List<BookTypeVo> list = baseMapper.getBookTypeList(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public BookType getBookTypeByCode(String code) throws Exception {
        return baseMapper.selectOne(
                new LambdaQueryWrapper<BookType>()
                        .eq(BookType::getCode, code)
        );
    }
}
