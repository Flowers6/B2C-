package com.jjy.feign.order;

import com.jjy.model.entity.order.OrderInfo;
import com.jjy.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/7
 * @time : 14:51
 */
@FeignClient(value = "service-order")
@Component
public interface OrderFeignClient {

    @GetMapping("/api/order/orderInfo/auth/getOrderInfoByOrderNo/{orderNo}")
    public OrderInfo getOrderInfoByOrderNo(@PathVariable String orderNo) ;

}
