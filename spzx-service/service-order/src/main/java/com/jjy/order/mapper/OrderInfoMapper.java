package com.jjy.order.mapper;

import com.jjy.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 13:45
 */
@Mapper
public interface OrderInfoMapper {
    /**
     * 添加数据到orderInfo
     * @param orderInfo
     */
    void save(OrderInfo orderInfo);

    /**
     * 获取订单信息
     * @param orderId
     * @return
     */
    OrderInfo getById(Long orderId);

    /**
     * 根据用户id和订单状态查询订单
     * @param userId
     * @param orderStatus
     * @return
     */
    List<OrderInfo> findUserPage(Long userId, Integer orderStatus);

    /**
     * 通过订单编号获取订单信息
     * @param orderNo
     * @return
     */
    OrderInfo getOrderInfoByOrderNo(String orderNo);
}
