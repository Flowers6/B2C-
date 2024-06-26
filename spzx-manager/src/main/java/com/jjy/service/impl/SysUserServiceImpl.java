package com.jjy.service.impl;

import cn.hutool.Hutool;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.jjy.common.exception.CustomException;
import com.jjy.mapper.SysUserMapper;
import com.jjy.model.dto.system.LoginDto;
import com.jjy.model.dto.system.SysUserDto;
import com.jjy.model.entity.system.SysUser;
import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.model.vo.system.LoginVo;
import com.jjy.service.SysUserService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.apache.el.parser.Token;
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
 * @date : 2024/6/25
 * @time : 17:18
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public LoginVo login(LoginDto loginDto) {
        // 图片验证码校验
        String captcha = loginDto.getCaptcha();//验证码的值
        String codeKey = loginDto.getCodeKey();//验证码的key

        // 根据key获取redis中验证码的值
        String redisCode = redisTemplate.opsForValue().get("user:validate" + codeKey);
        if (StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode, captcha)) {
            throw new CustomException(ResultCodeEnum.VALIDATECODE_ERROR);
        }

        //验证完成，清除验证码
        redisTemplate.delete("user:validate" + codeKey);

        // 根据用户名查询用户
        SysUser sysUser = sysUserMapper.selectUserInfoByUserName(loginDto);
        if (sysUser == null) {
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 验证密码是否正确
        String db_password = sysUser.getPassword();
        String input_password = DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes());
        if (!db_password.equals(input_password)) {
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
        }

        // 生成令牌，保存数据到Redis中
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set("user:login:" + token, JSON.toJSONString(sysUser), 2, TimeUnit.HOURS);

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);

        // 返回
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String userInfoJson= redisTemplate.opsForValue().get("user:login:" + token);
        SysUser sysUser = JSON.parseObject(userInfoJson, SysUser.class);

        if (sysUser == null) {
            throw new CustomException(ResultCodeEnum.LOGIN_AUTH);
        }

        return sysUser;
    }

    @Override
    public void logout(String token) {
        //根据token删除redis数据
        redisTemplate.delete("user:login:" + token);
    }
}
