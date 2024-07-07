package com.jjy.user.mapper;

import com.jjy.model.entity.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/5
 * @time : 14:12
 */
@Mapper
public interface UserInfoMapper {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    UserInfo selectByUsername(@Param("username") String username);

    /**
     * 保存用户信息
     * @param userInfo
     */
    void save(UserInfo userInfo);
}
