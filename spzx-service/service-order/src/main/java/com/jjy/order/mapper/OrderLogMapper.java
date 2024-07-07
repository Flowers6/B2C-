package com.jjy.order.mapper;

import com.jjy.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 17:00
 */
@Mapper
public interface OrderLogMapper {
    /**
     * 保存订单日志
     * @param orderLog
     */
    void save(OrderLog orderLog);
}
