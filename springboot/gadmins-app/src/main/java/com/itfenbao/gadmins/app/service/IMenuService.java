package com.itfenbao.gadmins.app.service;

import com.itfenbao.gadmins.app.data.vo.MenuItem;
import com.itfenbao.gadmins.app.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
public interface IMenuService extends IService<Menu> {

    List<MenuItem> getAllMenu();
}
