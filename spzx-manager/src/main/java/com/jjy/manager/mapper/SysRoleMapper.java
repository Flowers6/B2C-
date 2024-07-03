package com.jjy.manager.mapper;

import com.jjy.model.dto.system.SysRoleDto;
import com.jjy.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/27
 * @time : 15:58
 */
@Mapper
public interface SysRoleMapper {

    /**
     * 根据SysRoleDto查询角色
     * @param sysRoleDto
     * @return
     */
    List<SysRole> findByPage(SysRoleDto sysRoleDto);

    /**
     * 重置搜索框
     * @return
     */
    List<SysRole> resetInput();

    /**
     * 添加角色
     * @param sysRole
     */
    void save(SysRole sysRole);

    /**
     * 修改角色
     * @param sysRole
     */
    void update(SysRole sysRole);

    /**
     * 删除角色
     * @param roleId
     */
    void delete(Long roleId);

    /**
     * 查询所有角色
     * @return
     */
    List<SysRole> findAll();
}
