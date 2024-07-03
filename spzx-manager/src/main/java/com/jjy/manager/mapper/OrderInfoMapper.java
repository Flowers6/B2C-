package com.jjy.manager.mapper;

import com.jjy.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 12:08
 */
@Mapper
public interface OrderInfoMapper {
    /**
     * 查询订单创建时间
     * @param createTime
     * @return
     */
    OrderStatistics selectOrderStatistics(String createTime);
}
