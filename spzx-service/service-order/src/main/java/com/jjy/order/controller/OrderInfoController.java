package com.jjy.order.controller;

import com.github.pagehelper.PageInfo;
import com.jjy.model.dto.h5.OrderInfoDto;
import com.jjy.model.entity.order.OrderInfo;
import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.model.vo.h5.TradeVo;
import com.jjy.order.service.impl.OrderInfoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 13:44
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping(value="/api/order/orderInfo")
public class OrderInfoController {

    @Autowired
    private OrderInfoServiceImpl orderInfoService;

    @Operation(summary = "确认下单")
    @GetMapping("auth/trade")
    public Result<TradeVo> trade() {
        TradeVo tradeVo = orderInfoService.getTrade();
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "提交订单")
    @PostMapping("auth/submitOrder")
    public Result<Long> submitOrder(@RequestBody OrderInfoDto orderInfoDto) {
        Long orderId = orderInfoService.submitOrder(orderInfoDto);
        return Result.build(orderId, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取订单信息")
    @GetMapping("auth/{orderId}")
    public Result<OrderInfo> getOrderInfo(@Parameter(name = "orderId", description = "订单id", required = true) @PathVariable Long orderId) {
        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "立即购买")
    @GetMapping("auth/buy/{skuId}")
    public Result<TradeVo> buy(@PathVariable Long skuId) {
        TradeVo tradeVo = orderInfoService.buy(skuId);
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取订单分页列表")
    @GetMapping("auth/{page}/{limit}")
    public Result<PageInfo<OrderInfo>> list(
            @PathVariable Integer page,
            @PathVariable Integer limit,
            @RequestParam(required = false, defaultValue = "") Integer orderStatus) {
        PageInfo<OrderInfo> pageInfo = orderInfoService.findUserPage(page, limit, orderStatus);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "远程调用-根据订单编号获取订单信息")
    @GetMapping("auth/getOrderInfoByOrderNo/{orderNo}")
    public OrderInfo getOrderInfoByOrderNo(@PathVariable String orderNo) {
        OrderInfo orderInfo = orderInfoService.getOrderInfoByOrderNo(orderNo);
        return orderInfo;
    }

}
