package com.itfenbao.gadmins.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itfenbao.gadmins.admin.entity.Log;
import com.itfenbao.gadmins.admin.mapper.LogMapper;
import com.itfenbao.gadmins.admin.service.ILogService;
import com.itfenbao.gadmins.core.utils.IpUtils;
import com.itfenbao.gadmins.core.utils.LogUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 系统日志表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService, com.itfenbao.gadmins.core.web.service.ILogService {
    @Override
    public void save(String log, String[] tags) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request == null) {
            return;
        }
        Integer uId = LogUtils.getUserId();
        if (uId == null) {
            return;
        }
        Log logBean = new Log();
        logBean.setIp(IpUtils.getIpAddr(request));
        logBean.setUri(request.getRequestURI());
        logBean.setMethod(request.getMethod());
        logBean.setLog(log);
        logBean.setTag(String.join(",", tags));
        logBean.setCreatedBy(uId);
        save(logBean);
    }
}
