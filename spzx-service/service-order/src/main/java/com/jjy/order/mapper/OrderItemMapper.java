package com.jjy.order.mapper;

import com.jjy.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 16:59
 */
@Mapper
public interface OrderItemMapper {
    /**
     * 保存订单项
     * @param orderItem
     */
    void save(OrderItem orderItem);

    /**
     * 根据订单id查询商品列
     * @param id
     * @return
     */
    List<OrderItem> findByOrderId(Long id);
}
