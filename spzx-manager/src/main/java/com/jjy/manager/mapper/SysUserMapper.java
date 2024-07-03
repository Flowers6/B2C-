package com.jjy.manager.mapper;

import com.jjy.model.dto.system.LoginDto;
import com.jjy.model.dto.system.SysUserDto;
import com.jjy.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/25
 * @time : 17:19
 */
@Mapper
public interface SysUserMapper {
    /**
     * 根据用户名查询用户数据
     * @param userName
     * @return
     */
    SysUser selectUserInfoByUserName(String userName);

    /**
     * 根据用户名分页查询
     * @param sysUserDto
     * @return
     */
    List<SysUser> findByPage(SysUserDto sysUserDto);

    /**
     * 用户创建添加
     * @param sysUser
     */
    void save(SysUser sysUser);

    /**
     * 修改用户
     * @param sysUser
     */
    void update(SysUser sysUser);

    /**
     * 用户删除
     * @param userId
     */
    void delete(Long userId);
}
