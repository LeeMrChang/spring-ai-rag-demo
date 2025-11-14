package com.daily.cost.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daily.cost.entity.Transaction;
import com.daily.cost.mapper.TransactionMapper;
import com.daily.cost.service.ITransactionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账单表 服务实现类
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {

}
