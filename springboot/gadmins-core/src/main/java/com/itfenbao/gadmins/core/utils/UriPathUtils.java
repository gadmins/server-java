package com.itfenbao.gadmins.core.utils;

import org.springframework.util.AntPathMatcher;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/14 9:09 下午
 */
public class UriPathUtils {

    private static AntPathMatcher pathMatcher = new AntPathMatcher();

    public static boolean matchURI(String pattern, String uri) {
        return pathMatcher.match("/**/" + pattern + "/**", uri);
    }

}
