package com.jjy.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.jjy.cart.service.CartService;
import com.jjy.feign.product.ProductFeignClient;
import com.jjy.manager.utils.AuthContextUtil;
import com.jjy.model.entity.h5.CartInfo;
import com.jjy.model.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/5
 * @time : 18:04
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String getCartKey(Long userId) {
        //定义key user:cart:userId
        return "user:cart:" + userId;
    }

    @Override
    public void addToCart(Long skuId, Integer skuNum) {
        // 验证登录，获取登录用户id(作为redis中hash存储的key)
        // 从threadLocal 获取
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        // 因为购物车存入redis
        // 从redis中获取购物车数据，根据skuId获取(hash类型key + field)
        Object cartInfoObj = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId));
        CartInfo cartInfo = null;
        // 如果购物车已存在添加商品，把商品数量添加
        if (cartInfoObj != null) {
            cartInfo = JSON.parseObject(cartInfoObj.toString(), CartInfo.class);
            //数量相加
            cartInfo.setSkuNum(cartInfo.getSkuNum() + skuNum);
            //设置属性,设置商品为选中状态
            cartInfo.setIsChecked(1);
            cartInfo.setUpdateTime(new Date());
//            System.out.println("添加购物车，有商品，加skuNum" + cartInfo);
        } else {
            // 如果购物车没有商品，添加商品到redis
            // 远程调用实现 根据skuId获取商品sku信息
            cartInfo = new CartInfo();
            ProductSku productSku = productFeignClient.getBySkuId(skuId);
            // 设置相关数据到cartInfo
            cartInfo.setCartPrice(productSku.getSalePrice());
            cartInfo.setSkuNum(skuNum);
            cartInfo.setSkuId(skuId);
            cartInfo.setUserId(userId);
            cartInfo.setImgUrl(productSku.getThumbImg());
            cartInfo.setSkuName(productSku.getSkuName());
            cartInfo.setIsChecked(1);
            cartInfo.setCreateTime(new Date());
            cartInfo.setUpdateTime(new Date());
//            System.out.println("添加购物车，没有商品，加sku" + cartInfo);
        }
        //添加cartInfo到redis
        redisTemplate
                .opsForHash()
                .put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
    }

    @Override
    public List<CartInfo> getCartList() {
//        System.out.println("进入getCarList");
        //获取threadLocal中的userId 作为redis中hash的key
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        //根据key获取hash值
        List<Object> valueList = redisTemplate.opsForHash().values(cartKey);

        //封装数据
        if (!CollectionUtils.isEmpty(valueList)) {
//            System.out.println("购物车不是空的");
            List<CartInfo> cartInfoList = valueList.stream().map(cartInfo ->
                            JSON.parseObject(cartInfo.toString(), CartInfo.class))
                    .sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime()))
                    .collect(Collectors.toList());
            System.out.println(cartInfoList);
            return cartInfoList;
        }


//        System.out.println("完成getCartList");
        return new ArrayList<>();
    }

    @Override
    public void deleteCart(Long skuId) {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        //获取缓存对象
        redisTemplate.opsForHash().delete(cartKey  ,String.valueOf(skuId)) ;
    }

    @Override
    public void checkCart(Long skuId, Integer isChecked) {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        //判断key是否包含field
        Boolean hasKey = redisTemplate.opsForHash().hasKey(cartKey, String.valueOf(skuId));

        if (hasKey) {
            //获取value
            String cartInfoString = redisTemplate.opsForHash().get(cartKey, String.valueOf(skuId)).toString();
            CartInfo cartInfo = JSON.parseObject(cartInfoString, CartInfo.class);
            cartInfo.setIsChecked(isChecked);
            //更新它的"选中"属性
            redisTemplate.opsForHash().put(cartKey, String.valueOf(skuId), JSON.toJSONString(cartInfo));
        }

    }

    @Override
    public void allCheckCart(Integer isChecked) {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        //根据key获取value值
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);

        //类型转换
        if (!CollectionUtils.isEmpty(objectList)) {
            List<CartInfo> cartInfoList = objectList.stream()
                    .map(cartInfoObj -> JSON.parseObject(cartInfoObj.toString(), CartInfo.class))
                    .collect(Collectors.toList());

            //每个isChecked都更新为一
            cartInfoList.forEach(cartInfo -> {
                cartInfo.setIsChecked(isChecked);
                redisTemplate.opsForHash().put(cartKey, String.valueOf(cartInfo.getSkuId()), JSON.toJSONString(cartInfo));
            });
        }
    }

    @Override
    public void clearCart() {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        //清空购物车
        redisTemplate.delete(cartKey);
    }

    @Override
    public List<CartInfo> getAllCkecked() {
        // 获取当前登录的用户数据
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = getCartKey(userId);

        //根据key获取购物车所有商品数据
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);
        if (!CollectionUtils.isEmpty(objectList)) {
            List<CartInfo> cartInfoList = objectList.stream()
                    .map(obj -> JSON.parseObject(obj.toString(), CartInfo.class))
                    //判断是否选中,返回
                    .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                    .collect(Collectors.toList());
            return cartInfoList;
        }


        return new ArrayList<>();
    }

    @Override
    public void deleteChecked() {
        Long userId = AuthContextUtil.getUserInfo().getId();
        String cartKey = this.getCartKey(userId);

        //根据key获取redis所有value值
        List<Object> objectList = redisTemplate.opsForHash().values(cartKey);

        //删除选中商品
        objectList.stream()
                .map(obj -> JSON.parseObject(obj.toString(), CartInfo.class))
                //判断是否选中,返回
                .filter(cartInfo -> cartInfo.getIsChecked() == 1)
                .forEach(cartInfo -> redisTemplate.opsForHash().delete(cartKey, String.valueOf(cartInfo.getSkuId())));

    }
}
