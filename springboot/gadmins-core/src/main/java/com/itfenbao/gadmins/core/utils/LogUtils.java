package com.itfenbao.gadmins.core.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/6 4:06 下午
 */
public class LogUtils {

    public static void setLogUserId(int userId) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.setAttribute("uId", userId);
    }

    public static Integer getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object uId = request.getAttribute("uId");
        return uId != null ? (Integer) uId : null;
    }
}
