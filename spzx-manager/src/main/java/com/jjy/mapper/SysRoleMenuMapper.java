package com.jjy.mapper;

import com.jjy.model.dto.system.AssignMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 13:19
 */
@Mapper
public interface SysRoleMenuMapper {
    /**
     * 查询角色分配过的菜单列表
     * @param roleId
     * @return
     */
    List<Long> findSysRoleMenuByRoleId(Long roleId);

    /**
     * 删除角色之前分配的菜单数据
     * @param assignMenuDto
     */
    void deleteByRoleId(AssignMenuDto assignMenuDto);

    /**
     * 保存分配的数据
     * @param assignMenuDto
     */
    void doAssign(AssignMenuDto assignMenuDto);

    /**
     * 将父菜单设置为半开is_half = 1
     * @param menuId
     */
    void updateSysRoleMenuIsHalf(Long menuId);
}
