package com.itfenbao.gadmins.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itfenbao.gadmins.admin.data.dto.param.menu.AddMenuParam;
import com.itfenbao.gadmins.admin.data.dto.param.menu.UpdateMenuParam;
import com.itfenbao.gadmins.admin.data.dto.query.MenuQuery;
import com.itfenbao.gadmins.admin.data.treenode.MenuTreeNode;
import com.itfenbao.gadmins.admin.data.vo.CoreMenuData;
import com.itfenbao.gadmins.admin.data.vo.FunctionMenuVO;
import com.itfenbao.gadmins.admin.data.vo.MenuVO;
import com.itfenbao.gadmins.admin.entity.Menu;
import com.itfenbao.gadmins.core.web.vo.menu.MenuBean;

import java.sql.SQLException;
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
     * @param menuBean
     * @return
     */
    boolean saveOrUpdate(MenuBean menuBean);

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

    List<MenuVO> getAllMenu();

    Page<FunctionMenuVO> getListByPage(MenuQuery query);

    boolean updatePidIsNULL(Integer id);

    void updateScanMenus();

    void resetMenus() throws SQLException;

    boolean updateById(Integer id, UpdateMenuParam param);

    boolean add(AddMenuParam param);

}
