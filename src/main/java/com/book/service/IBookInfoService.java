package com.book.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.book.common.units.PageInfo;
import com.book.model.BookInfo;

/**
 * <p>
 * 书本基础信息 服务类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
public interface IBookInfoService extends IService<BookInfo> {

    /**
     * 获取书本信息列表
     * @param pageInfo 分页信息
     */
    void getBookInfoList(PageInfo pageInfo);

    /**
     * 获取书本信息根据书本编码
     *
     * @param code 编码
     * @return 书本信息实体类
     */
    BookInfo getBookInfoByCode(String code);
}
