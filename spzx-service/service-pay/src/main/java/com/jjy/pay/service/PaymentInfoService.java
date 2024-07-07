package com.jjy.pay.service;

import com.jjy.model.entity.pay.PaymentInfo;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/7
 * @time : 14:20
 */
public interface PaymentInfoService {
    /**
     * 保存支付信息
     * @param orderNo
     * @return
     */
    PaymentInfo savePaymentInfo(String orderNo);
}
