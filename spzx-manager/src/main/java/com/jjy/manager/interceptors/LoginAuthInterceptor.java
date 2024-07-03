package com.jjy.manager.interceptors;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.jjy.model.entity.system.SysUser;
import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.manager.utils.AuthContextUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/26
 * @time : 18:14
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求方式
        String method = request.getMethod();
        if (method.equals("OPTIONS")) {
            return true;
        }

        // 获取token
        String token = request.getHeader("token");

        // 判断token是否为空，并验证token是否合法
        if (StrUtil.isEmpty(token)) {
            responseNoLoginInfo(response);
            return false;
        }

        String userinfo = redisTemplate.opsForValue().get("user:login:" + token);
        if (StrUtil.isEmpty(userinfo)) {
            responseNoLoginInfo(response);
            return false;
        }

        // 将用户数据存储到threadLocal
        AuthContextUtil.set(JSON.parseObject(userinfo, SysUser.class));

        // 重置redis中token的有效时间
        redisTemplate.expire("user:login:" + token, 30, TimeUnit.MINUTES);

        return true;
    }

    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        AuthContextUtil.remove();  // 移除threadLocal中的数据
    }

}
