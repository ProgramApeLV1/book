package com.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.model.InStockRecord;
import com.book.model.vo.InStockRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 归还记录 Mapper 接口
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
public interface InStockRecordMapper extends BaseMapper<InStockRecord> {

    List<InStockRecordVo> getReturnBookInfo(Page page, @Param(value = "condition") Map<String, Object> condition);

}
