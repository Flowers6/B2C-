package com.jjy.service;

import com.jjy.model.dto.system.LoginDto;
import com.jjy.model.dto.system.SysUserDto;
import com.jjy.model.entity.system.SysUser;
import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.system.LoginVo;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/25
 * @time : 17:18
 */
public interface SysUserService {
    /**
     * 用户请求登录业务
     * @param loginDto
     * @return
     */
    LoginVo login(LoginDto loginDto);

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    SysUser getUserInfo(String token);

    /**
     * 根据token退出登录，删除redis中token对应数据
     * @param token
     */
    void logout(String token);
}
