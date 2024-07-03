package com.jjy.manager.mapper;

import com.jjy.model.dto.order.OrderStatisticsDto;
import com.jjy.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 12:08
 */
@Mapper
public interface OrderStatisticsMapper {
    /**
     * 保存订单统计数据
     * @param orderStatistics
     */
    void insert(OrderStatistics orderStatistics);

    /**
     * 获取订单统计数据集合
     * @param orderStatisticsDto
     * @return
     */
    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}
