package com.book.service.impl;

import com.book.model.LibraryStock;
import com.book.mapper.LibraryStockMapper;
import com.book.service.ILibraryStockService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 书本基础信息 服务实现类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Service
public class LibraryStockServiceImpl extends ServiceImpl<LibraryStockMapper, LibraryStock> implements ILibraryStockService {

}
