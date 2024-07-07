package com.jjy.feign.cart;

import com.jjy.model.entity.h5.CartInfo;
import com.jjy.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 14:16
 */
@FeignClient(value = "service-cart")
public interface CartFeignClient {

    @GetMapping(value = "/api/order/cart/auth/getAllCkecked")
    public List<CartInfo> getAllCkecked();

    @GetMapping(value = "/api/order/cart/auth/deleteChecked")
    public Result deleteChecked();

}
