package com.jjy.service;

import com.github.pagehelper.PageInfo;
import com.jjy.model.dto.system.SysRoleDto;
import com.jjy.model.entity.system.SysRole;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/27
 * @time : 15:57
 */
public interface SysRoleService {
    /**
     * 根据页码和size查询角色
     * @param current 当前页
     * @param limit size
     * @param sysRoleDto
     * @return
     */
    PageInfo<SysRole> findByPage(Integer current, Integer limit, SysRoleDto sysRoleDto);

    /**
     * 重置搜索框
     * @param current
     * @param limit
     * @return
     */
    PageInfo<SysRole> resetInput(Integer current, Integer limit);

    /**
     * 添加角色
     * @param sysRole
     */
    void saveSysRole(SysRole sysRole);

    /**
     * 修改角色
     * @param sysRole
     */
    void updateSysRole(SysRole sysRole);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteById(Long roleId);
}
