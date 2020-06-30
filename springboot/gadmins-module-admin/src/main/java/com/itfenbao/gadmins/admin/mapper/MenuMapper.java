package com.itfenbao.gadmins.admin.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itfenbao.gadmins.admin.data.treenode.MenuTreeNode;
import com.itfenbao.gadmins.admin.data.vo.FunctionMenuVO;
import com.itfenbao.gadmins.admin.data.vo.MenuVO;
import com.itfenbao.gadmins.admin.entity.Menu;
import org.apache.ibatis.annotations.Param;

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

//    /**
//     * 获取用户菜单
//     *
//     * @param userId
//     * @return
//     */
//    List<MenuVO> getAllMenuByUserId(Integer userId);

    List<MenuTreeNode> getAllMenuTree();

    List<MenuTreeNode> getAllParentMenuTree(@Param("ids") List<Integer> ids);

    Page<FunctionMenuVO> getListByPage(Page<FunctionMenuVO> page, @Param(Constants.WRAPPER) Wrapper wrapper);

    int updatePidIsNULL(Integer id);
}
