package com.daily.cost.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daily.cost.entity.Budget;
import com.daily.cost.mapper.BudgetMapper;
import com.daily.cost.service.IBudgetService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 预算表 服务实现类
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Service
public class BudgetServiceImpl extends ServiceImpl<BudgetMapper, Budget> implements IBudgetService {

}
