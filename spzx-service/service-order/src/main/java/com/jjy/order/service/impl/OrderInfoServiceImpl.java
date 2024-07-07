package com.jjy.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jjy.common.exception.CustomException;
import com.jjy.feign.cart.CartFeignClient;
import com.jjy.feign.product.ProductFeignClient;
import com.jjy.feign.user.UserFeignClient;
import com.jjy.manager.utils.AuthContextUtil;
import com.jjy.model.dto.h5.OrderInfoDto;
import com.jjy.model.entity.h5.CartInfo;
import com.jjy.model.entity.order.OrderInfo;
import com.jjy.model.entity.order.OrderItem;
import com.jjy.model.entity.order.OrderLog;
import com.jjy.model.entity.product.ProductSku;
import com.jjy.model.entity.user.UserAddress;
import com.jjy.model.entity.user.UserInfo;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.model.vo.h5.TradeVo;
import com.jjy.order.mapper.OrderInfoMapper;
import com.jjy.order.mapper.OrderItemMapper;
import com.jjy.order.mapper.OrderLogMapper;
import com.jjy.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/6
 * @time : 13:45
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private CartFeignClient cartFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;

    @Override
    public TradeVo getTrade() {
        //调取远程接口获取购物车选中商品列表
        List<CartInfo> cartInfoList = cartFeignClient.getAllCkecked();

        //创建list,用于封装订单项
        ArrayList<OrderItem> orderItemList = new ArrayList<>();

        for (CartInfo cartInfo : cartInfoList) {
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);
        }

        //获取订单支付总金额
        BigDecimal totalAmount = new BigDecimal(0);

        for (CartInfo cartInfo : cartInfoList) {
            totalAmount = totalAmount.add(cartInfo.getCartPrice().multiply(new BigDecimal(cartInfo.getSkuNum())));
        }

        TradeVo tradeVo = new TradeVo();
        tradeVo.setOrderItemList(orderItemList);
        tradeVo.setTotalAmount(totalAmount);

        return tradeVo;
    }

    @Override
    public Long submitOrder(OrderInfoDto orderInfoDto) {
        // orderInfoDto获取所有订单项 List<OrderItem> orderItemList
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();

        // 判断List 为空,抛出异常
        if (CollectionUtils.isEmpty(orderItemList)) {
            throw new CustomException(ResultCodeEnum.DATA_ERROR);
        }

        // 不为空,遍历list 校验orderItem库存是否足够
        for (OrderItem orderItem : orderItemList) {
            //调用远程接口获取商品信息
            ProductSku productSku = productFeignClient.getBySkuId(orderItem.getSkuId());
            if (productSku == null) {
                throw new CustomException(ResultCodeEnum.DATA_ERROR);
            }
            if (orderItem.getSkuNum().intValue() > productSku.getStockNum().intValue()) {
                throw new CustomException(ResultCodeEnum.STOCK_LESS);
            }
        }

        // 添加数据到order_info表
        // 封装数据到orderInfo对象
        OrderInfo orderInfo = new OrderInfo();
        UserInfo userInfo = AuthContextUtil.getUserInfo();
        //订单编号
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis()));
        //用户id
        orderInfo.setUserId(userInfo.getId());
        //用户昵称
        orderInfo.setNickName(userInfo.getNickName());
        //用户收货地址信息
        // 远程调用获取用户收货地址信息
        UserAddress userAddress = userFeignClient.getUserAddress(orderInfoDto.getUserAddressId());

        orderInfo.setReceiverName(userAddress.getName());
        orderInfo.setReceiverPhone(userAddress.getPhone());
        orderInfo.setReceiverTagName(userAddress.getTagName());
        orderInfo.setReceiverProvince(userAddress.getProvinceCode());
        orderInfo.setReceiverCity(userAddress.getCityCode());
        orderInfo.setReceiverDistrict(userAddress.getDistrictCode());
        orderInfo.setReceiverAddress(userAddress.getFullAddress());
        //订单金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setCouponAmount(new BigDecimal(0));
        orderInfo.setOriginalTotalAmount(totalAmount);
        orderInfo.setFeightFee(orderInfoDto.getFeightFee());
        orderInfo.setPayType(2);
        orderInfo.setOrderStatus(0);

        orderInfoMapper.save(orderInfo);

        // 添加数据到order_item表
        for (OrderItem orderItem : orderItemList) {
            //设置对应的订单id
            orderItem.setOrderId(orderInfo.getId());
            orderItemMapper.save(orderItem);
        }

        // 添加数据到order_info表
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(0);
        orderLog.setNote("提交订单");
        orderLogMapper.save(orderLog);

        // 从购物车中删除生成了订单的商品
        // 远程调用
        cartFeignClient.deleteChecked();

        // 返回订单id
        return orderInfo.getId();
    }

    @Override
    public OrderInfo getOrderInfo(Long orderId) {
        return orderInfoMapper.getById(orderId);
    }

    @Override
    public TradeVo buy(Long skuId) {
        // 查询商品
        ProductSku productSku = productFeignClient.getBySkuId(skuId);
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = new OrderItem();
        orderItem.setSkuId(skuId);
        orderItem.setSkuName(productSku.getSkuName());
        orderItem.setSkuNum(1);
        orderItem.setSkuPrice(productSku.getSalePrice());
        orderItem.setThumbImg(productSku.getThumbImg());
        orderItemList.add(orderItem);

        // 计算总金额
        BigDecimal totalAmount = productSku.getSalePrice();
        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(totalAmount);
        tradeVo.setOrderItemList(orderItemList);

        // 返回
        return tradeVo;
    }

    @Override
    public PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus) {
        PageHelper.startPage(page, limit);
        //查询订单信息
        Long userId = AuthContextUtil.getUserInfo().getId();
        List<OrderInfo> orderInfoList = orderInfoMapper.findUserPage(userId, orderStatus);

        //查询订单项
        orderInfoList.forEach(orderInfo -> {
            //根据订单id，查询订单项
            List<OrderItem> orderItemList = orderItemMapper.findByOrderId(orderInfo.getId());
            //封装
            orderInfo.setOrderItemList(orderItemList);
        });
        return new PageInfo<>(orderInfoList);
    }

    @Override
    public OrderInfo getOrderInfoByOrderNo(String orderNo) {
        OrderInfo orderInfo = orderInfoMapper.getOrderInfoByOrderNo(orderNo);
        List<OrderItem> orderItemList = orderItemMapper.findByOrderId(orderInfo.getId());
        orderInfo.setOrderItemList(orderItemList);
        return orderInfo;
    }
}
