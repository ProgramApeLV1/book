package com.book.service;

import com.book.common.units.PageInfo;
import com.book.model.BookInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 书本基础信息 服务类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
public interface IBookInfoService extends IService<BookInfo> {

    void getBookInfoList(PageInfo pageInfo);
}
