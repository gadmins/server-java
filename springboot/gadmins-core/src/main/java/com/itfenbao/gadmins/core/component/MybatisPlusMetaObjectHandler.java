package com.itfenbao.gadmins.core.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

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

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createdAt", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createdBy", Integer.class, Integer.parseInt(TokenUtils.getUniqueIdFromToken()));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updatedAt", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updatedBy", Integer.class, Integer.parseInt(TokenUtils.getUniqueIdFromToken()));
    }
}
