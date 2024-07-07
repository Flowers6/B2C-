package com.jjy.cart.service;

import com.jjy.model.entity.h5.CartInfo;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/5
 * @time : 18:04
 */
public interface CartService {
    /**
     * 添加商品至购物车
     * @param skuId
     * @param skuNum
     */
    void addToCart(Long skuId, Integer skuNum);

    /**
     * 查询购物车接口
     * @return
     */
    List<CartInfo> getCartList();

    /**
     * 删除购物车中商品
     * @param skuId
     */
    void deleteCart(Long skuId);

    /**
     * 更新购物车选中状态
     * @param skuId
     * @param isChecked
     */
    void checkCart(Long skuId, Integer isChecked);

    /**
     * 更新购物车商品全部选中状态
     * @param isChecked
     */
    void allCheckCart(Integer isChecked);

    /**
     * 清空购物车
     */
    void clearCart();

    /**
     * 用于订单界面的远程调用
     * @return
     */
    List<CartInfo> getAllCkecked();

    /**
     * 删除生成了订单的购物车商品
     */
    void deleteChecked();
}
