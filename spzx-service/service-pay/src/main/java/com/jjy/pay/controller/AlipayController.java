package com.jjy.pay.controller;

import com.jjy.common.anno.EnableUserTokenFeignInterceptor;
import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.pay.service.impl.AlipayServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/7
 * @time : 14:19
 */
@Controller
@RequestMapping("/api/order/alipay")
public class AlipayController {

    @Autowired
    private AlipayServiceImpl alipayService;

    @Operation(summary="支付宝下单")
    @GetMapping("submitAlipay/{orderNo}")
    @ResponseBody
    public Result submitAlipay(@PathVariable(value = "orderNo") String orderNo) {
        String form = alipayService.submitAlipay(orderNo);
        return Result.build(form, ResultCodeEnum.SUCCESS);
    }
}
