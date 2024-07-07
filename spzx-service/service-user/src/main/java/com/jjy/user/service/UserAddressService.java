package com.jjy.user.service;

import com.jjy.model.entity.user.UserAddress;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 13:33
 */
public interface UserAddressService {
    /**
     * 查询当前用户地址
     * @return
     */
    List<UserAddress> findUserAddressList();

    /**
     * 通过用户id获取地址
     * @param id
     * @return
     */
    UserAddress getById(Long id);
}
