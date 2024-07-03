package com.jjy.manager.service;

import com.github.pagehelper.PageInfo;
import com.jjy.model.entity.product.ProductSpec;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/1
 * @time : 17:07
 */
public interface ProductSpecService {
    /**
     * 产品规格分页查询
     * @param page
     * @param limit
     * @return
     */
    PageInfo<ProductSpec> findByPage(Integer page, Integer limit);

    /**
     * 商品规格添加
     * @param productSpec
     */
    void save(ProductSpec productSpec);

    /**
     * 商品规格修改
     * @param productSpec
     */
    void updateById(ProductSpec productSpec);

    /**
     * 删除产品规格
     * @param id
     */
    void deleteById(Long id);

    /**
     * 查询所有商品数据规格
     * @return
     */
    List<ProductSpec> findAll();
}
