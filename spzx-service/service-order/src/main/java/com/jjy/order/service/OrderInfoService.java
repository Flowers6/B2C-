package com.jjy.order.service;

import com.github.pagehelper.PageInfo;
import com.jjy.model.dto.h5.OrderInfoDto;
import com.jjy.model.entity.order.OrderInfo;
import com.jjy.model.vo.h5.TradeVo;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 13:44
 */
public interface OrderInfoService {

    /**
     * 获取订单
     * @return
     */
    TradeVo getTrade();

    /**
     * 提交订单接口
     * @param orderInfoDto
     * @return
     */
    Long submitOrder(OrderInfoDto orderInfoDto);

    /**
     * 获取订单详情信息
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfo(Long orderId);

    /**
     * 立即购买
     * @param skuId
     * @return
     */
    TradeVo buy(Long skuId);

    /**
     * 获取订单分页查询
     * @param page
     * @param limit
     * @param orderStatus
     * @return
     */
    PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus);

    /**
     * 根据订单编号获取订单信息
     * @param orderNo
     * @return
     */
    OrderInfo getOrderInfoByOrderNo(String orderNo);
}
