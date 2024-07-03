package com.jjy.manager.service;

import com.jjy.model.entity.system.SysMenu;
import com.jjy.model.vo.system.SysMenuVo;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 11:11
 */
public interface SysMenuService {

    /**
     * 查询菜单所有节点
     * @return
     */
    List<SysMenu> findNodes();

    /**
     * 添加菜单
     * @param sysMenu
     */
    void save(SysMenu sysMenu);

    /**
     * 修改菜单
     * @param sysMenu
     */
    void update(SysMenu sysMenu);

    /**
     * 删除菜单
     * @param id
     */
    void removeById(Long id);

    /**
     * 查询用户可用菜单
     * @return
     */
    List<SysMenuVo> findMenuByUserId();
}
