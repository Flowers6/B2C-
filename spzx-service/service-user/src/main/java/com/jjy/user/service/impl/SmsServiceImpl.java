package com.jjy.user.service.impl;

import com.jjy.common.exception.CustomException;
import com.jjy.manager.utils.HttpUtils;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.user.service.SmsService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/5
 * @time : 11:41
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void sendValidateCode(String phone) {
        String code = redisTemplate.opsForValue().get(phone);
        if (StringUtils.hasText(code)) {
            return;
        }

        //生成验证码
        code = RandomStringUtils.randomNumeric(4);

        //存放验证码至redis并设置过期时间
        redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);

        //发送验证码
        sendMessage(phone, code);
    }

    private void sendMessage(String phone, String code) {
        String host = "https://cdcxdxjk.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "07114e58d9484549bd288f3c2c2c75aa";//开通服务后 买家中心-查看AppCode
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:" + code);
        bodys.put("template_id", "CST_ptdie100");
        bodys.put("phone_number", phone);

        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }
}
