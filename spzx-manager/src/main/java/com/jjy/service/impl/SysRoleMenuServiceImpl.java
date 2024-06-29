package com.jjy.service.impl;

import com.jjy.mapper.SysMenuMapper;
import com.jjy.mapper.SysRoleMenuMapper;
import com.jjy.model.dto.system.AssignMenuDto;
import com.jjy.model.entity.system.SysMenu;
import com.jjy.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 13:18
 */
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    private SysMenuServiceImpl sysMenuService;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        //查询所有菜单
        List<SysMenu> sysMenuList = sysMenuService.findNodes();

        //查询角色分配过的菜单列表
        List<Long> roleMenuIds = sysRoleMenuMapper.findSysRoleMenuByRoleId(roleId);

        // 将数据存储到Map中进行返回
        Map<String , Object> map = new HashMap<>() ;
        map.put("sysMenuList" , sysMenuList) ;
        map.put("roleMenuIds" , roleMenuIds) ;
        return map;
    }

    @Override
    public void doAssign(AssignMenuDto assignMenuDto) {
        //删除角色之前分配的菜单数据
        sysRoleMenuMapper.deleteByRoleId(assignMenuDto);

        //保存分配的数据
        List<Map<String, Number>> menuInfo = assignMenuDto.getMenuIdList();
        if (menuInfo != null && menuInfo.size() > 0) {
            //角色分配了菜单
            sysRoleMenuMapper.doAssign(assignMenuDto);
        }
    }
}
