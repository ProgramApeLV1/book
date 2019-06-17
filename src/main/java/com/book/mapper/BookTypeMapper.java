package com.book.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.model.BookType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.book.model.vo.BookTypeVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 书本基础信息 Mapper 接口
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
public interface BookTypeMapper extends BaseMapper<BookType> {

    List<BookTypeVo> getBookTypeList(Page page, Map<String, Object> condition);
}
