package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.data.dto.query.MenuQuery;
import com.itfenbao.gadmins.admin.data.treenode.MenuTreeNode;
import com.itfenbao.gadmins.admin.data.vo.CoreMenuData;
import com.itfenbao.gadmins.admin.data.vo.FunctionMenuVO;
import com.itfenbao.gadmins.admin.entity.Menu;
import com.itfenbao.gadmins.core.web.vo.menu.MenuConfig;

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
     * 保存或更新菜单
     *
     * @param menuConfig
     * @return
     */
    boolean saveOrUpdate(MenuConfig menuConfig);

    /**
     * 获取前端核心菜单
     *
     * @param accountId
     * @return
     */
    CoreMenuData getCoreMenuData(Integer accountId);

    /**
     * 获取 通用Tree组件 所有菜单数据
     *
     * @return
     */
    List<MenuTreeNode> menuTree();

    /**
     * 获取 通用Tree组件 所有菜单数据即按钮，用于角色授权
     *
     * @return
     */
    List<MenuTreeNode> menuTreeAndFuncs();

    /**
     * 获取 通用Tree组件 非菜单数据（系统菜单+导航菜单）
     *
     * @param ids 需要过滤的ids
     * @return
     */
    List<MenuTreeNode> notMenuTree(List<Integer> ids);

    Page<FunctionMenuVO> getListByPage(MenuQuery query, Wrapper wrapper);
}
