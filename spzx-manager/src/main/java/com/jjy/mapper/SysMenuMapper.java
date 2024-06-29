package com.jjy.mapper;

import com.jjy.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 11:11
 */
@Mapper
public interface SysMenuMapper {
    /**
     * 查询所有菜单
     * @return
     */
    List<SysMenu> findAll();

    /**
     * 添加菜单
     * @param sysMenu
     */
    void save(SysMenu sysMenu);

    /**
     * 添加菜单
     * @param sysMenu
     */
    void update(SysMenu sysMenu);

    /**
     * 根据id查询子菜单数
     * @param id
     * @return
     */
    int selectCountById(Long id);

    /**
     * 根据id删除菜单
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id查询可操作的菜单
     * @param userId
     * @return
     */
    List<SysMenu> findMenuByUserId(Long userId);

    /**
     * 获取父菜单
     * @param parentId
     * @return
     */
    SysMenu selectById(Long parentId);
}
