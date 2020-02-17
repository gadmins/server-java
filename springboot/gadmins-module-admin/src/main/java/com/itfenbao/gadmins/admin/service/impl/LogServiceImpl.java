package com.itfenbao.gadmins.admin.service.impl;

import com.itfenbao.gadmins.admin.entity.Log;
import com.itfenbao.gadmins.admin.mapper.LogMapper;
import com.itfenbao.gadmins.admin.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
