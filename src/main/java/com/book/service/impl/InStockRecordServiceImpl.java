package com.book.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.book.common.units.PageInfo;
import com.book.model.InStockRecord;
import com.book.mapper.InStockRecordMapper;
import com.book.model.vo.InStockRecordVo;
import com.book.service.IInStockRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 归还记录 服务实现类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Service
public class InStockRecordServiceImpl extends ServiceImpl<InStockRecordMapper, InStockRecord> implements IInStockRecordService {

    @Override
    public void getReturnBookInfo(PageInfo pageInfo) {
        Page page = new Page(pageInfo.getNowpage(), pageInfo.getSize());
        List<InStockRecordVo> list = baseMapper.getReturnBookInfo(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
}
