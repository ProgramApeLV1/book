package com.book.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.model.InStockRecord;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.book.model.vo.InStockRecordVo;
import com.book.model.vo.OutStockRecordVo;

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

    List<InStockRecordVo> getReturnBookInfo(Page page, Map<String, Object> condition);

}
