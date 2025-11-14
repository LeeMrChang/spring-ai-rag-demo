package com.daily.cost.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.daily.cost.entity.User;
import com.daily.cost.mapper.UserMapper;
import com.daily.cost.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lichanghao
 * @since 2025-11-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
