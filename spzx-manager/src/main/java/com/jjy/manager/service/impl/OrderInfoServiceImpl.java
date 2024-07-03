package com.jjy.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.jjy.manager.mapper.OrderInfoMapper;
import com.jjy.manager.mapper.OrderStatisticsMapper;
import com.jjy.model.dto.order.OrderStatisticsDto;
import com.jjy.model.entity.order.OrderStatistics;
import com.jjy.model.vo.order.OrderStatisticsVo;
import com.jjy.manager.service.OrderInfoService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 12:42
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    private OrderStatisticsMapper orderStatisticsMapper ;

    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        // 查询统计结果数据
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsDto) ;

        //日期列表
        List<String> dateList = orderStatisticsList.stream().map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd")).collect(Collectors.toList());

        //统计金额列表
        List<BigDecimal> amountList = orderStatisticsList.stream().map(OrderStatistics::getTotalAmount).collect(Collectors.toList());

        // 创建OrderStatisticsVo对象封装响应结果数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo() ;
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);

        // 返回数据
        return orderStatisticsVo;
    }
}
