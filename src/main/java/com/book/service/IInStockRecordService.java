package com.book.service;

import com.book.common.units.PageInfo;
import com.book.model.InStockRecord;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 归还记录 服务类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
public interface IInStockRecordService extends IService<InStockRecord> {

    void getReturnBookInfo(PageInfo pageInfo);
}
