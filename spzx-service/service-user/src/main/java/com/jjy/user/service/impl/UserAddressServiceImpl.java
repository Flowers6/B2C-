package com.jjy.user.service.impl;

import com.jjy.user.mapper.UserAddressMapper;
import com.jjy.manager.utils.AuthContextUtil;
import com.jjy.model.entity.user.UserAddress;
import com.jjy.user.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 13:33
 */
@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> findUserAddressList() {
        //获取用户id
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<UserAddress> userAddressList = userAddressMapper.findUserAddressList(userId);
        return userAddressList;
    }

    @Override
    public UserAddress getById(Long id) {
        UserAddress userAddress = userAddressMapper.getById(id);
        return userAddress;
    }
}
