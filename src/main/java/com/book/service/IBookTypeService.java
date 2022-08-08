package com.book.service;

import com.book.common.units.PageInfo;
import com.book.model.BookType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 书本基础信息 服务类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
public interface IBookTypeService extends IService<BookType> {

    /**
     * 获取书本类型（分页）
     * @param pageInfo 分页实体
     * @throws Exception 异常
     */
    void getBookTypeList(PageInfo pageInfo) throws Exception;

    /***
     * 根据类型编码获取对应类型信息
     * @param code 类型编码
     * @return 类型实体
     * @throws Exception 异常
     */
    BookType getBookTypeByCode(String code) throws Exception;
}
