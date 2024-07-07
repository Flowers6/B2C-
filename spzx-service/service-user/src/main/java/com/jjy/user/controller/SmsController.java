package com.jjy.user.controller;


import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.user.service.impl.SmsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/5
 * @time : 11:40
 */
@RestController
@RequestMapping("api/user/sms")
public class SmsController {

    @Autowired
    private SmsServiceImpl smsService ;

    @GetMapping(value = "/sendCode/{phone}")
    public Result sendValidateCode(@PathVariable String phone) {
        smsService.sendValidateCode(phone);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

}
