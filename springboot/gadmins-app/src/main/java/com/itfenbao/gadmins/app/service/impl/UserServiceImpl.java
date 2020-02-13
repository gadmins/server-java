package com.itfenbao.gadmins.app.service.impl;

import com.itfenbao.gadmins.app.entity.User;
import com.itfenbao.gadmins.app.mapper.UserMapper;
import com.itfenbao.gadmins.app.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
