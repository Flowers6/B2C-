package com.jjy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jjy.mapper.SysRoleMapper;
import com.jjy.model.dto.system.SysRoleDto;
import com.jjy.model.entity.system.SysRole;
import com.jjy.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/27
 * @time : 15:57
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public PageInfo<SysRole> findByPage(Integer current, Integer limit, SysRoleDto sysRoleDto) {
        //使用PageHelper传入current,limit
        PageHelper.startPage(current, limit);
        //接收sysRole对象
        List<SysRole> sysRoleList = sysRoleMapper.findByPage(sysRoleDto);
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoleList);
        return pageInfo;
    }

    @Override
    public PageInfo<SysRole> resetInput(Integer current, Integer limit) {
        //使用PageHelper传入current,limit
        PageHelper.startPage(current, limit);
        //接收sysRole对象
        List<SysRole> sysRoleList = sysRoleMapper.resetInput();
        PageInfo<SysRole> pageInfo = new PageInfo<>(sysRoleList);
        return pageInfo;
    }

    @Override
    public void saveSysRole(SysRole sysRole) {
        sysRoleMapper.save(sysRole) ;
    }

    @Override
    public void updateSysRole(SysRole sysRole) {
        sysRoleMapper.update(sysRole);
    }

    @Override
    public void deleteById(Long roleId) {
        sysRoleMapper.delete(roleId);
    }


}
