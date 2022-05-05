package com.book.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.book.common.units.PageInfo;
import com.book.model.OutStockRecord;
import com.book.mapper.OutStockRecordMapper;
import com.book.model.vo.OutStockRecordVo;
import com.book.service.IOutStockRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 借出记录 服务实现类
 * </p>
 *
 * @author wyh123
 * @since 2019-01-03
 */
@Service
public class OutStockRecordServiceImpl extends ServiceImpl<OutStockRecordMapper, OutStockRecord> implements IOutStockRecordService {

    @Override
    public void getBoorowBookInfoList(PageInfo pageInfo) {
        Page page = new Page(pageInfo.getNowpage(), pageInfo.getSize());
        List<OutStockRecordVo> list = baseMapper.getBoorowBookInfoList(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
}
