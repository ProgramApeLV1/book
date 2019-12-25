package com.book.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.book.common.units.PageInfo;
import com.book.model.InStockRecord;
import com.book.mapper.InStockRecordMapper;
import com.book.model.vo.InStockRecordVo;
import com.book.service.IInStockRecordService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private InStockRecordMapper inStockRecordMapper;


    @Override
    public void getReturnBookInfo(PageInfo pageInfo) {
        Page page = new Page(pageInfo.getNowpage(), pageInfo.getSize());
        List<InStockRecordVo> list = inStockRecordMapper.getReturnBookInfo(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
}
