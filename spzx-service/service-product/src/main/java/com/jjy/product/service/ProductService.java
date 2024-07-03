package com.jjy.product.service;

import com.jjy.model.entity.product.ProductSku;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/3
 * @time : 12:58
 */
public interface ProductService {
    /**
     * 根据销量，获取前10条记录
     * @return
     */
    List<ProductSku> findProductSkuBySale();
}
