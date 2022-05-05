package com.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.model.OutStockRecord;
import com.book.model.vo.OutStockRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 借出记录 Mapper 接口
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
public interface OutStockRecordMapper extends BaseMapper<OutStockRecord> {

    List<OutStockRecordVo> getBoorowBookInfoList(Page page, @Param(value = "condition") Map<String, Object> condition);
}
