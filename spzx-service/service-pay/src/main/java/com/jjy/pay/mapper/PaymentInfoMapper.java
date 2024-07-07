package com.jjy.pay.mapper;

import com.jjy.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/7
 * @time : 14:20
 */
@Mapper
public interface PaymentInfoMapper {
    /**
     * 根据订单编号查询支付记录
     * @param orderNo
     * @return
     */
    PaymentInfo getByOrderNo(String orderNo);

    /**
     * 保存支付记录
     * @param paymentInfo
     */
    void save(PaymentInfo paymentInfo);
}
