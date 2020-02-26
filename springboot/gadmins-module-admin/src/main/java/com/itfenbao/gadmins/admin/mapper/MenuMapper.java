package com.itfenbao.gadmins.admin.mapper;

import com.itfenbao.gadmins.admin.data.treenode.MenuTreeNode;
import com.itfenbao.gadmins.admin.data.vo.MenuVO;
import com.itfenbao.gadmins.admin.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    List<MenuTreeNode> getAllMenuTree();

    List<MenuTreeNode> getAllParentMenuTree(@Param("ids") List<Integer> ids);
}
