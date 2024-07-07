package com.jjy.product.service;

import com.jjy.model.entity.product.Brand;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/4
 * @time : 16:45
 */
public interface BrandService {
    /**
     * 查询所有品牌
     *
     * @return
     */
    List<Brand> findAll();
}
