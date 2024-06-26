package com.jjy.mapper;

import com.jjy.model.dto.system.LoginDto;
import com.jjy.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

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
     * @param loginDto
     * @return
     */
    SysUser selectUserInfoByUserName(LoginDto loginDto);
}
