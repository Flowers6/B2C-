package com.jjy.user.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.jjy.common.exception.CustomException;
import com.jjy.user.mapper.UserInfoMapper;
import com.jjy.manager.utils.AuthContextUtil;
import com.jjy.model.dto.h5.UserLoginDto;
import com.jjy.model.dto.h5.UserRegisterDto;
import com.jjy.model.entity.user.UserInfo;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.model.vo.h5.UserInfoVo;
import com.jjy.user.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/5
 * @time : 14:09
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void register(UserRegisterDto userRegisterDto) {
        //获取数据
        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String nickName = userRegisterDto.getNickName();
        String code = userRegisterDto.getCode();

        //验证码是否正确
        //获取redis中验证码
        String redisCode = redisTemplate.opsForValue().get(username);
        if (redisCode.equals(code)) {
            throw new CustomException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        //根据用户id查询用户信息
        UserInfo userInfo = userInfoMapper.selectByUsername(username);

        //用户是否存在
        if (userInfo != null) {
            throw new CustomException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }

        userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setNickName(nickName);
        userInfo.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userInfo.setPhone(username);
        userInfo.setStatus(1);
        userInfo.setSex(0);
        userInfo.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");

        //存入数据库
        userInfoMapper.save(userInfo);

        //删除验证码
        redisTemplate.delete(username);

        //创建token，存入redis

        //将token返回

    }

    @Override
    public String login(UserLoginDto userLoginDto) {

        //接收请求信息
        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();

        //检查是否为空
        if(StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password)) {
            throw new CustomException(ResultCodeEnum.DATA_ERROR);
        }

        //查询用户是否存在
        UserInfo userInfo = userInfoMapper.selectByUsername(username);
        if (userInfo == null) {
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
        }

        //验证密码是否正确
        if (!userInfo.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
        }

        //校验是否被禁用
        if(userInfo.getStatus() == 0) {
            throw new CustomException(ResultCodeEnum.ACCOUNT_STOP);
        }

        //创建token存入redis
        String token = UUID.randomUUID().toString().replace("-", "");
        redisTemplate.opsForValue().set("user:spzx:" + token, JSON.toJSONString(userInfo), 30, TimeUnit.DAYS);

        return token;
    }

    @Override
    public UserInfoVo getCurrentUserInfo(String token) {
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        UserInfoVo userInfoVo = new UserInfoVo();
        BeanUtils.copyProperties(userInfo, userInfoVo);
        return userInfoVo ;
    }
}
