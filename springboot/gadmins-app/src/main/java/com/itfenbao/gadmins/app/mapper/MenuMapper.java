package com.itfenbao.gadmins.app.mapper;

import com.itfenbao.gadmins.app.data.vo.MenuVO;
import com.itfenbao.gadmins.app.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-15
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<MenuVO> getAllMenu();
}
