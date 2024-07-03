package com.jjy.manager.task;

import cn.hutool.core.date.DateUtil;
import com.jjy.manager.mapper.OrderInfoMapper;
import com.jjy.manager.mapper.OrderStatisticsMapper;
import com.jjy.model.entity.order.OrderStatistics;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 11:31
 */
@Component
public class OrderStatisticsTask {

    /**
     * @Scheduled 注解 + cron表达式
     * 设置执行规则
     * 测试定时任务
     */
    /*@Scheduled(cron = "0/5 * * * * ?")
    public void testHello() {
        //每隔5秒执行一次方法
        System.out.println(new Time(System.currentTimeMillis()));
    }*/

    @Resource
    private OrderInfoMapper orderInfoMapper;

    @Resource
    private OrderStatisticsMapper orderStatisticsMapper;

    /*每天凌晨两点执行*/
    @Scheduled(cron = "0 0 2 * * ?")
//    @Scheduled(cron = "0/5 * * * *  ?")
    public void orderTotalAmountStatistics() {
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createTime);
        if(orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics) ;
        }
    }

}
