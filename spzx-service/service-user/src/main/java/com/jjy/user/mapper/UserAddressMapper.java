package com.jjy.user.mapper;

import com.jjy.model.entity.user.UserAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 13:34
 */
@Mapper
public interface UserAddressMapper {
    /**
     * 根据用户id查询用户地址
     * @param userId
     * @return
     */
    List<UserAddress> findUserAddressList(Long userId);

    /**
     * 根据用户id获取地址
     * @param id
     * @return
     */
    UserAddress getById(Long id);
}
