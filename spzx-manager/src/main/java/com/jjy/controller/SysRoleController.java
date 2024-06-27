package com.jjy.controller;

import com.github.pagehelper.PageInfo;
import com.jjy.model.dto.system.SysRoleDto;
import com.jjy.model.entity.system.SysRole;
import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.service.impl.SysRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/27
 * @time : 15:56
 */
@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleServiceImpl sysRoleService;

    @PostMapping("/findByPage/{current}/{limit}")
    public Result findByPage(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit,
                             @RequestBody SysRoleDto sysRoleDto) {
        PageInfo<SysRole> userInfo = sysRoleService.findByPage(current, limit, sysRoleDto);
        return Result.build(userInfo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/resetInput/{current}/{limit}")
    public Result resetInput(@PathVariable("current") Integer current,
                             @PathVariable("limit") Integer limit) {
        PageInfo<SysRole> userInfo = sysRoleService.resetInput(current, limit);
        return Result.build(userInfo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping(value = "/saveSysRole")
    public Result saveSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveSysRole(sysRole) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @PutMapping(value = "/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteById(@PathVariable Long roleId) {
        sysRoleService.deleteById(roleId);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}
