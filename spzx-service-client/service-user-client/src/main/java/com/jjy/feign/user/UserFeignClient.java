package com.jjy.feign.user;

import com.jjy.model.entity.user.UserAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 17:18
 */
@FeignClient(value = "service-user")
public interface UserFeignClient {
    @GetMapping("/api/user/userAddress/getUserAddress/{id}")
    public UserAddress getUserAddress(@PathVariable Long id) ;

}
