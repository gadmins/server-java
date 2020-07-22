package com.itfenbao.gadmins.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.CamelCaseLinkedMap;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.itfenbao.gadmins.admin.data.dto.param.menu.AddMenuParam;
import com.itfenbao.gadmins.admin.data.dto.param.menu.UpdateMenuParam;
import com.itfenbao.gadmins.admin.data.dto.query.MenuQuery;
import com.itfenbao.gadmins.admin.data.treenode.MenuTreeNode;
import com.itfenbao.gadmins.admin.data.treenode.SysMenuTreeNode;
import com.itfenbao.gadmins.admin.data.vo.AuthFunciontVO;
import com.itfenbao.gadmins.admin.data.vo.CoreMenuData;
import com.itfenbao.gadmins.admin.data.vo.FunctionMenuVO;
import com.itfenbao.gadmins.admin.data.vo.MenuVO;
import com.itfenbao.gadmins.admin.entity.Function;
import com.itfenbao.gadmins.admin.entity.Menu;
import com.itfenbao.gadmins.admin.entity.RlMenuRole;
import com.itfenbao.gadmins.admin.mapper.MenuMapper;
import com.itfenbao.gadmins.admin.service.*;
import com.itfenbao.gadmins.config.AppConfig;
import com.itfenbao.gadmins.config.menu.MenuConfig;
import com.itfenbao.gadmins.core.AppListener;
import com.itfenbao.gadmins.core.utils.SpringBootUtils;
import com.itfenbao.gadmins.core.utils.TokenUtils;
import com.itfenbao.gadmins.core.web.service.IMenuScanService;
import com.itfenbao.gadmins.core.web.vo.Tree;
import com.itfenbao.gadmins.core.web.vo.menu.FunctionPoint;
import com.itfenbao.gadmins.core.web.vo.menu.MenuBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-13
 */
@Slf4j
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    IFunctionService functionService;

    @Autowired
    IAccountService accountService;

    @Autowired
    IRlAccountRoleService accountRoleService;

    @Autowired
    IRlMenuRoleService menuRoleService;

    @Autowired
    IFunctionConfigService functionConfigService;

    @Autowired
    AppListener appListener;

    private final String SCHEMA_SQL = "classpath:sql/menu_init_data.sql";

    // admin(2) 用户禁用菜单
    private List<Integer> adminNoMenuIds = Arrays.asList(6, 7, 8, 9, 10, 11, 16);

    private DataSource datasource;

    @Autowired
    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }

    @Override
    public boolean saveOrUpdate(MenuBean menuBean) {
        Menu one = this.getOne(Wrappers.<Menu>lambdaQuery().eq(Menu::getMCode, menuBean.getCode()));
        if (one == null) {
            Menu menu = createMenu(menuBean);
            if (this.save(menu)) {
                return true;
            }
        } else {
            Menu _update = new Menu();
            _update.setId(one.getId());
            _update.setSortNumber(menuBean.getSort());
            _update.setIcon(menuBean.getIcon());
            _update.setTxt(menuBean.getTitle());
            if (this.updateById(_update)) {
                return true;
            }
        }
        return false;
    }

    private Menu createMenu(MenuBean menuBean) {
        Menu menu = new Menu();
        menu.setSortNumber(menuBean.getSort());
        menu.setFuncId(menuBean.getFuncId());
        menu.setMCode(menuBean.getCode());
        menu.setTxt(menuBean.getTitle());
        menu.setIcon(menuBean.getIcon());
        if (StringUtils.isNotBlank(menuBean.getParentCode())) {
            Menu getMenu = this.getOne(Wrappers.<Menu>lambdaQuery().eq(Menu::getMCode, menuBean.getParentCode()));
            if (getMenu != null) {
                menu.setPId(getMenu.getId());
            }
        }
        menuBean.getParentCode();
        return menu;
    }

    @Override
    public CoreMenuData getCoreMenuData(Integer accountId) {
        boolean isSuperAdmin = accountService.isSuperAdmin(accountId);
        // 获取全部菜单
        List<MenuVO> allMenu = this.getAllMenu();
        // 获取全部功能
        List<AuthFunciontVO> functionVOS = null;

        if (!isSuperAdmin) {
            List<Integer> roleIds = accountRoleService.getRoleIdsByAccountId(accountId);
            List<Integer> funcPids = this.functionService.getPIdsByRoles(roleIds);
            functionVOS = this.functionService.getAuthFunciontVOS(roleIds);

            // 查询角色菜单ids
            LambdaQueryWrapper<RlMenuRole> wrapper = Wrappers.<RlMenuRole>lambdaQuery();
            if (!CollectionUtils.isEmpty(roleIds)) {
                wrapper.in(RlMenuRole::getRoleId, roleIds);
            }
            wrapper.groupBy(RlMenuRole::getMenuId);
            List<Integer> roleMenuIds = this.menuRoleService.list(wrapper)
                    .stream().map(it -> it.getMenuId()).collect(Collectors.toList());
            // 查询功能菜单ids
            LambdaQueryWrapper<Menu> menuWrapper = Wrappers.<Menu>lambdaQuery();
            if (!CollectionUtils.isEmpty(funcPids)) {
                menuWrapper.in(Menu::getFuncId, funcPids);
            }
            List<Integer> funcMenuIds = this.list(menuWrapper)
                    .stream().map((it -> it.getId())).collect(Collectors.toList());

            // TODO: 合成用户菜单，待优化
            // 1. 去重
            List<Integer> menuIds = CollUtil.addAllIfNotContains(roleMenuIds, funcMenuIds);
            // 2. 在所有菜单找到自己及父级菜单
            List<Integer> pids = CollUtil.newArrayList(menuIds);
            do {
                List<Integer> finalPids = pids;
                pids = allMenu.stream().filter(m -> finalPids.indexOf(m.getId()) > -1 && m.getPId() != null)
                        .map(m -> m.getPId()).collect(Collectors.toList());
                if (pids.size() > 0) {
                    CollUtil.addAllIfNotContains(menuIds, pids);
                }
            } while (pids.size() > 0);
            allMenu = allMenu.stream().filter(m -> menuIds.indexOf(m.getId()) > -1).collect(Collectors.toList());
        } else {
            if (accountId == 1) {
                // devadmin
                functionVOS = this.functionService.list().stream().map(i -> createAuthFunction(i)).collect(Collectors.toList());
            } else if (accountId == 2) {
                // admin
                // 过滤菜单关联功能id
                List<Integer> funcIds = allMenu.stream().
                        filter(m -> adminNoMenuIds.indexOf(m.getId()) > -1)
                        .map(m -> m.getFuncId()).filter(i -> i != null).collect(Collectors.toList());
                // 过滤功能id及子id
                funcIds = this.functionService.list(
                        Wrappers.<Function>lambdaQuery().in(Function::getId, funcIds).or().in(Function::getPId, funcIds)
                ).stream().map(i -> i.getId()).collect(Collectors.toList());

                final List<Integer> finalFuncIds = funcIds;
                functionVOS = this.functionService.list().stream()
                        .filter(i -> finalFuncIds.indexOf(i.getId()) == -1)
                        .filter(i -> i.getPId() == null || finalFuncIds.indexOf(i.getPId()) == -1)
                        .map(i -> createAuthFunction(i)).collect(Collectors.toList());

                allMenu = allMenu.stream().filter(m -> adminNoMenuIds.indexOf(m.getId()) == -1).collect(Collectors.toList());
            }
        }
        Map<String, String> defMenuTxtMap = new CamelCaseLinkedMap();
        List<SysMenuTreeNode> sysMenuTreeNodes = allMenu.stream().sorted(Comparator.comparing(Menu::getSortNumber)).map(menu -> {
            defMenuTxtMap.put(menu.getMCode(), menu.getTxt());
            SysMenuTreeNode sysMenuTreeNode = new SysMenuTreeNode();
            sysMenuTreeNode.setId(menu.getId());
            sysMenuTreeNode.setFuncId(menu.getFuncId());
            sysMenuTreeNode.setParentId(menu.getPId());
            sysMenuTreeNode.setKey(menu.getMCode());
            sysMenuTreeNode.setName(menu.getMCode());
            sysMenuTreeNode.setKey("/" + menu.getMCode());
            sysMenuTreeNode.setIcon(menu.getIcon());
            sysMenuTreeNode.setPath(menu.getFrontUrl());
            if (menu.getElink()) {
                sysMenuTreeNode.setTarget("_blank");
            }
            return sysMenuTreeNode;
        }).collect(Collectors.toList());
        List<SysMenuTreeNode> menuTree = Tree.build(sysMenuTreeNodes);
        menuTree.stream().forEach(item -> {
            item.setPath(getPath(item));
        });
        functionVOS = functionVOS.stream().filter(i -> !i.getCode().endsWith("-vm")).collect(Collectors.toList());
        return new CoreMenuData(menuTree, functionVOS, defMenuTxtMap);
    }

    private AuthFunciontVO createAuthFunction(Function i) {
        AuthFunciontVO functionVO = new AuthFunciontVO();
        functionVO.setId(i.getId());
        functionVO.setUrl(i.getFrontUrl());
        functionVO.setCode(i.getFuncCode());
        return functionVO;
    }

    /**
     * 设置父级菜单Path
     *
     * @param item
     * @return
     */
    private String getPath(SysMenuTreeNode item) {
        if (CollectionUtils.isEmpty(item.getChildren())) {
            return item.getPath();
        } else {
            return getPath(item.getChildren().get(0));
        }
    }


    @Override
    public List<MenuTreeNode> menuTree() {
        return Tree.build(this.baseMapper.getAllMenuTree());
    }

    @Override
    public List<MenuTreeNode> menuTreeAndFuncs() {
        List<MenuTreeNode> treeNodes = this.baseMapper.getAllMenuTree();
        String id = TokenUtils.getUniqueIdFromToken();
        if (Integer.parseInt(id) == 2) {
            treeNodes = treeNodes.stream().filter(menuTreeNode -> {
                if (AppConfig.Menu.Type.SYS_MENU.equals(menuTreeNode.getType())
                        || AppConfig.Menu.Type.NAV_MENU.equals(menuTreeNode.getType())
                        || AppConfig.Menu.Type.MENU.equals(menuTreeNode.getType())) {
                    return adminNoMenuIds.indexOf(menuTreeNode.getId()) == -1;
                }
                return true;
            }).collect(Collectors.toList());
        }
        return Tree.build(treeNodes, menu -> {
            if (AppConfig.Menu.Type.MENU.equals(menu.getType())) {
                if (AppConfig.Menu.Type.MENU.equals(menu.getType()) && menu.getFuncId() != null) {
                    List<Function> functions = functionService.lambdaQuery().eq(Function::getPId, menu.getFuncId()).orderByAsc(Function::getSortNumber).list();
                    Function queryFunc = functionService.getById(menu.getFuncId());
                    List<MenuTreeNode> funcs = getMenuTreeNodes(functions, queryFunc);
                    if (!CollectionUtils.isEmpty(funcs)) {
                        menu.setChildren(funcs);
                    }
                }
            }
        });
    }

    /**
     * @param functions
     * @param queryFunc
     * @return
     */
    private List<MenuTreeNode> getMenuTreeNodes(List<Function> functions, Function queryFunc) {
        if (queryFunc != null) {
            if (StringUtils.isBlank(queryFunc.getTitle())) {
                queryFunc.setTitle("查询");
            }
            functions.add(0, queryFunc);
        }
        return functions.stream().map(func -> {
            MenuTreeNode authBtn = new MenuTreeNode();
            authBtn.setType("FUNC");
            authBtn.setId(func.getId());
            authBtn.setKey(func.getFuncCode());
            authBtn.setName(func.getFuncCode());
            authBtn.setTitle(func.getTitle());
            if (func.getVirtualMenu()) {
                List<Function> childFuncs = functionService.lambdaQuery().eq(Function::getPId, func.getId()).orderByAsc(Function::getSortNumber).list();
                if (!CollectionUtils.isEmpty(childFuncs)) {
                    // FIXME: 可能存在性能问题
                    List<MenuTreeNode> childNodes = getMenuTreeNodes(childFuncs, null);
                    if (!CollectionUtils.isEmpty(childFuncs)) {
                        authBtn.setChildren(childNodes);
                    }
                }
            }
            return authBtn;
        }).collect(Collectors.toList());
    }

    @Override
    public List<MenuTreeNode> notMenuTree(List<Integer> ids) {
        return Tree.build(this.baseMapper.getAllParentMenuTree(ids));
    }

    @Override
    public List<MenuVO> getAllMenu() {
        return this.baseMapper.getAllMenu();
    }

    @Override
    public Page<FunctionMenuVO> getListByPage(MenuQuery query) {
        Page<FunctionMenuVO> page = new Page<>(query.getCurrent(), query.getPageSize());
        Wrapper wrapper = Wrappers.query().eq("_menu.type", AppConfig.Menu.Type.MENU).orderByAsc("_menu.sort_number");
        return this.baseMapper.getListByPage(page, wrapper);
    }

    @Override
    public boolean updatePidIsNULL(Integer id) {
        return SqlHelper.retBool(this.baseMapper.updatePidIsNULL(id));
    }

    @Override
    public void updateScanMenus() {
        scanMenus();
    }

    @Override
    public void resetMenus() throws SQLException {
        SpringBootUtils.executeSqlScript(datasource, SCHEMA_SQL);
        this.updateScanMenus();
        scanMenus();
    }

    @Override
    public boolean updateById(Integer id, UpdateMenuParam param) {
        if (this.count(Wrappers.<Menu>lambdaQuery().eq(Menu::getMCode, param.getMcode()).ne(Menu::getId, id)) > 0) {
            return false;
        }
        Menu menu = new Menu();
        menu.setPId(param.getParentId());
        menu.setTxt(param.getTxt());
        menu.setMCode(param.getMcode());
        menu.setIcon(param.getIcon());
        menu.setSortNumber(param.getSortNumber());
        menu.setId(id);
        updateFunction(param, menu);
        if (param.getParentId() == null) {
            this.updatePidIsNULL(menu.getId());
        }
        this.updateById(menu);
        return true;
    }

    @Override
    public boolean add(AddMenuParam param) {
        if (this.count(Wrappers.<Menu>lambdaQuery().eq(Menu::getMCode, param.getMcode())) > 0) {
            return false;
        }
        Menu menu = new Menu();
        menu.setPId(param.getParentId());
        menu.setTxt(param.getTxt());
        menu.setMCode(param.getMcode());
        menu.setIcon(param.getIcon());
        menu.setSortNumber(param.getSortNumber());
        menu.setType(param.getType());
        updateFunction(param, menu);
        this.save(menu);
        return true;
    }

    private void updateFunction(UpdateMenuParam param, Menu menu) {
        if (param.getFuncId() != null) {
            menu.setFuncId(param.getFuncId());
            Function function = new Function();
            function.setId(param.getFuncId());
            function.setElink(param.getElink());
            function.setFrontUrl(param.getUrl());
            functionService.updateById(function);
        }
    }

    /**
     * 扫描所有菜单
     */
    private void scanMenus() {
        scanMenuConfig();
        // 扫描注解菜单及功能
        List<MenuBean> menuBeans = appListener.getMenuBeans();
        menuBeans.forEach(mc -> {
            AtomicReference<Integer> funcId = new AtomicReference<>();
            // 先处理 parentCode 为空
            mc.getFunctionPoints().stream().filter(f -> StringUtils.isBlank(f.getParentCode())).forEach(fp -> {
                saveFunctionPointAndConfig(funcId, fp);
            });
            mc.getFunctionPoints().stream().filter(f -> StringUtils.isNotBlank(f.getParentCode())).forEach(fp -> {
                saveFunctionPointAndConfig(funcId, fp);
            });
            if (funcId.get() != null) {
                mc.setFuncId(funcId.get());
            }
            this.saveOrUpdate(mc);
        });
        // 扫描IMenuScanService
        Map<String, IMenuScanService> menuScanServices = SpringBootUtils.getMenuScanServices();
        menuScanServices.values().forEach(menuScanService -> {
            menuScanService.scanMenu();
        });
    }

    MenuConfig menuConfig;

    @Autowired(required = false)
    public void setMenuConfig(MenuConfig menuConfig) {
        this.menuConfig = menuConfig;
    }

    /**
     * 扫码Bean配置菜单
     */
    private void scanMenuConfig() {
        if (menuConfig != null && com.baomidou.mybatisplus.core.toolkit.CollectionUtils.isNotEmpty(menuConfig.getNavMenus())) {
            menuConfig.getSysMenus().forEach(sys -> {
                Menu menu = this.getOne(Wrappers.<Menu>lambdaQuery().eq(Menu::getMCode, sys.getCode()));
                if (menu == null) {
                    menu = new Menu();
                    menu.setMCode(sys.getCode());
                }
                menu.setType(AppConfig.Menu.Type.SYS_MENU);
                menu.setTxt(sys.getTitle());
                menu.setSortNumber(sys.getSort());
                this.saveOrUpdate(menu);
            });
            menuConfig.getNavMenus().forEach(nav -> {
                Menu parentMenu = this.getOne(Wrappers.<Menu>lambdaQuery().eq(Menu::getMCode, nav.getParentCode()));
                Menu menu = this.getOne(Wrappers.<Menu>lambdaQuery().eq(Menu::getMCode, nav.getCode()));
                if (menu == null) {
                    menu = new Menu();
                    menu.setMCode(nav.getCode());
                }
                menu.setPId(parentMenu.getId());
                menu.setType(AppConfig.Menu.Type.NAV_MENU);
                menu.setTxt(nav.getTitle());
                menu.setSortNumber(nav.getSort());
                this.saveOrUpdate(menu);
            });
        }
    }

    private void saveFunctionPointAndConfig(AtomicReference<Integer> funcId, FunctionPoint fp) {
        if (funcId.get() != null) {
            fp.setParentFuncId(funcId.get());
        }
        if (functionService.saveOrUpdate(fp)) {
            if (fp.isMenu()) {
                funcId.set(fp.getFuncId());
            }
            fp.getPointConfig().setFuncId(fp.getFuncId());
            functionConfigService.saveOrUpdate(fp.getPointConfig());
        }
    }
}
