package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.data.vo.CoreMenuData;
import com.itfenbao.gadmins.admin.data.treenode.MenuTreeNode;
import com.itfenbao.gadmins.admin.entity.Menu;

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

    /**
     * 获取前端核心菜单
     *
     * @return
     */
    CoreMenuData getCoreMenuData();

    /**
     * 获取 通用Tree组件 所有菜单数据
     *
     * @return
     */
    List<MenuTreeNode> menuTree();

    /**
     * 获取 通用Tree组件 所有菜单数据即按钮
     * @return
     */
    List<MenuTreeNode> menuTreeAndFuncs();

    /**
     * 获取 通用Tree组件 非菜单数据（系统菜单+导航菜单）
     *
     * @return
     * @param ids 需要过滤的ids
     */
    List<MenuTreeNode> notMenuTree(List<Integer> ids);
}
