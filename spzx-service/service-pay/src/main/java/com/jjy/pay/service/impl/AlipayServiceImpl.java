package com.jjy.pay.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeWapMergePayRequest;
import com.alipay.api.response.AlipayTradeWapMergePayResponse;
import com.jjy.common.exception.CustomException;
import com.jjy.model.entity.pay.PaymentInfo;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.pay.service.AlipayService;
import com.jjy.pay.utils.AlipayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/7
 * @time : 14:19
 */
@Service
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AlipayProperties alipayProperties;

    @Autowired
    private PaymentInfoServiceImpl paymentInfoService;

    //支付下单
    @Override
    public String submitAlipay(String orderNo) {
        //保存支付记录
        PaymentInfo paymentInfo = paymentInfoService.savePaymentInfo(orderNo);

        //调用支付宝接口
        //构建参数，进行调用
        AlipayTradeWapMergePayRequest alipayRequest = new AlipayTradeWapMergePayRequest();

        // 同步回调
        alipayRequest.setReturnUrl(alipayProperties.getReturnPaymentUrl());

        // 异步回调
        alipayRequest.setNotifyUrl(alipayProperties.getNotifyPaymentUrl());

        // 准备请求参数 ，声明一个map 集合
        HashMap<String, Object> map = new HashMap<>();
        map.put("out_trade_no",paymentInfo.getOrderNo());
        map.put("product_code","QUICK_WAP_WAY");
        //map.put("total_amount",paymentInfo.getAmount());
        map.put("total_amount",new BigDecimal("0.01"));
        map.put("subject",paymentInfo.getContent());
        alipayRequest.setBizContent(JSON.toJSONString(map));

        //调用支付宝服务接口
        try {
            AlipayTradeWapMergePayResponse response = alipayClient.pageExecute(alipayRequest);
            if (response.isSuccess()) {
                String form = response.getBody();
                return form;
            } else {
                throw new CustomException(ResultCodeEnum.DATA_ERROR);
            }
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
    }
}
