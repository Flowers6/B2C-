package com.jjy.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.jjy.common.exception.CustomException;
import com.jjy.model.dto.system.LoginDto;
import com.jjy.model.entity.system.SysUser;
import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.model.vo.system.LoginVo;
import com.jjy.model.vo.system.SysMenuVo;
import com.jjy.model.vo.system.ValidateCodeVo;
import com.jjy.properties.UserAuthProperties;
import com.jjy.service.SysMenuService;
import com.jjy.service.impl.SysUserServiceImpl;
import com.jjy.service.impl.ValidateCodeServiceImpl;
import com.jjy.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/25
 * @time : 17:17
 */
@Tag("用户接口")
@RestController
@RequestMapping("/admin/system/index")
@EnableConfigurationProperties(value = {UserAuthProperties.class})
public class IndexController {

    @Autowired
    private SysUserServiceImpl sysUserService;

    @Autowired
    private ValidateCodeServiceImpl validateCodeService;

    @Autowired
    private SysMenuService sysMenuService;

    //查询用户可以操作菜单
    @GetMapping("/menu")
    public Result menu() {
        List<SysMenuVo> list = sysMenuService.findMenuByUserId();
        return Result.build(list, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "根据token退出登录")
    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

//    @Operation(summary = "根据token获取用户信息")
//    @GetMapping("/getUserInfo")
//    public Result<SysUser> getUserInfo(@RequestHeader(name = "token") String token) {
//        //根据token查询redis中用户信息
//        SysUser sysUser = sysUserService.getUserInfo(token);
//
//        //返回用户信息到result
//        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
//    }

    @Operation(summary = "根据token获取用户信息")
    @GetMapping("/getUserInfo")
    public Result<SysUser> getUserInfo(@RequestHeader(name = "token") String token) {
        return Result.build(AuthContextUtil.get(), ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "图片验证码方法")
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "登录的方法")
    @PostMapping("login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

}
