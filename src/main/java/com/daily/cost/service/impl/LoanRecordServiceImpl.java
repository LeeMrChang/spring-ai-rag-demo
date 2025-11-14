package com.daily.cost.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daily.cost.entity.LoanRecord;
import com.daily.cost.mapper.LoanRecordMapper;
import com.daily.cost.service.ILoanRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 借贷记录表 服务实现类
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Service
public class LoanRecordServiceImpl extends ServiceImpl<LoanRecordMapper, LoanRecord> implements ILoanRecordService {

}
