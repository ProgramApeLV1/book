package com.book.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.model.OutStockRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.book.model.vo.OutStockRecordVo;

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

    List<OutStockRecordVo> getBoorowBookInfoList(Page page, Map<String, Object> condition);
}
