package com.jjy.user.service;

import com.jjy.model.dto.h5.UserLoginDto;
import com.jjy.model.dto.h5.UserRegisterDto;
import com.jjy.model.vo.h5.UserInfoVo;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/5
 * @time : 14:09
 */
public interface UserInfoService {
    /**
     * 用户注册接口
     * @param userRegisterDto
     */
    void register(UserRegisterDto userRegisterDto);

    /**
     * 用户登录接口
     * @param userLoginDto
     * @return
     */
    String login(UserLoginDto userLoginDto);

    /**
     * 获取登录用户的信息
     * @param token
     * @return
     */
    UserInfoVo getCurrentUserInfo(String token);
}
