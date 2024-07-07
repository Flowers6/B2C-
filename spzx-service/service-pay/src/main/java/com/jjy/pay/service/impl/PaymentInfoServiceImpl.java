package com.jjy.pay.service.impl;

import com.jjy.feign.order.OrderFeignClient;
import com.jjy.model.entity.order.OrderInfo;
import com.jjy.model.entity.order.OrderItem;
import com.jjy.model.entity.pay.PaymentInfo;
import com.jjy.pay.mapper.PaymentInfoMapper;
import com.jjy.pay.service.PaymentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/7
 * @time : 14:20
 */
@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Autowired
    private PaymentInfoMapper paymentInfoMapper;

    @Override
    public PaymentInfo savePaymentInfo(String orderNo) {
        //根据订单编号查询支付记录
        PaymentInfo paymentInfo = paymentInfoMapper.getByOrderNo(orderNo);

        //判断支付记录是否存在
        if (paymentInfo == null) {
            //远程调用获取订单信息
            OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(orderNo);
            //把orderInfo 获取数据封装到paymentInfo
            paymentInfo = new PaymentInfo();

            paymentInfo.setUserId(orderInfo.getUserId());
            paymentInfo.setPayType(orderInfo.getPayType());
            String content = "";
            for(OrderItem item : orderInfo.getOrderItemList()) {
                content += item.getSkuName() + " ";
            }
            paymentInfo.setContent(content);
            paymentInfo.setAmount(orderInfo.getTotalAmount());
            paymentInfo.setOrderNo(orderNo);
            paymentInfo.setPaymentStatus(0);
            //添加
            paymentInfoMapper.save(paymentInfo);
        }
        return paymentInfo;
    }
}
