package com.jjy.service;

import com.jjy.model.dto.system.AssignMenuDto;

import java.util.Map;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 13:18
 */
public interface SysRoleMenuService{
    /**
     * 查询所有菜单 和 查询角色分配过菜单id 列表
     * @param roleId
     * @return
     */
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    /**
     * 为角色分配菜单
     * @param assignMenuDto
     */
    void doAssign(AssignMenuDto assignMenuDto);
}
