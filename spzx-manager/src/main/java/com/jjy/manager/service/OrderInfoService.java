package com.jjy.manager.service;

import com.jjy.model.dto.order.OrderStatisticsDto;
import com.jjy.model.vo.order.OrderStatisticsVo;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 12:42
 */
public interface OrderInfoService {
    /**
     * 获取订单统计数据
     * @param orderStatisticsDto
     * @return
     */
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);
}
