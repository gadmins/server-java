package com.itfenbao.gadmins.app.service.impl;

import com.itfenbao.gadmins.app.entity.Menu;
import com.itfenbao.gadmins.app.mapper.MenuMapper;
import com.itfenbao.gadmins.app.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
