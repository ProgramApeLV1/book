package com.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.model.BookType;
import com.book.model.vo.BookTypeVo;
import org.apache.ibatis.annotations.Param;

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

    List<BookTypeVo> getBookTypeList(Page page, @Param(value = "condition") Map<String, Object> condition);
}
