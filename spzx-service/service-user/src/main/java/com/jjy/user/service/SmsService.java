package com.jjy.user.service;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/5
 * @time : 11:41
 */
public interface SmsService {
    /**
     * 发送短信验证
     * @param phone
     */
    void sendValidateCode(String phone);
}
