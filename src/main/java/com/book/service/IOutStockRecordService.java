package com.book.service;

import com.book.common.units.PageInfo;
import com.book.model.OutStockRecord;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 借出记录 服务类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
public interface IOutStockRecordService extends IService<OutStockRecord> {

    void getBoorowBookInfoList(PageInfo pageInfo);
}
