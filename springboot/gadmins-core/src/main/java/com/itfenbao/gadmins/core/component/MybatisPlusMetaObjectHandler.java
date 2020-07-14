package com.itfenbao.gadmins.core.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import com.itfenbao.gadmins.core.utils.UriPathUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * TODO
 *
 * @author itfenbao
 * @version 1.0
 * @date 2020/3/30 2:17 下午
 */
@Component
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private boolean isApiUri(String uri) {
        return UriPathUtils.matchURI(AppConfig.Route.Admin.ROOT, uri) || UriPathUtils.matchURI(AppConfig.Route.App.ROOT, uri);
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, LocalDateTime.now());
        HttpServletRequest request = getRequest();
        if (request != null && isApiUri(request.getRequestURI())) {
            String uId = TokenUtils.getUniqueIdFromToken();
            if (StringUtils.isNotBlank(uId)) {
                this.strictInsertFill(metaObject, "createdBy", Integer.class, Integer.parseInt(uId));
            }
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
        HttpServletRequest request = getRequest();
        if (request != null && isApiUri(request.getRequestURI())) {
            String uId = TokenUtils.getUniqueIdFromToken();
            if (StringUtils.isNotBlank(uId)) {
                this.strictUpdateFill(metaObject, "updatedBy", Integer.class, Integer.parseInt(uId));
            }
        }
    }
}
