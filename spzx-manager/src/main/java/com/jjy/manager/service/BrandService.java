package com.jjy.manager.service;

import com.github.pagehelper.PageInfo;
import com.jjy.model.entity.product.Brand;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 22:09
 */
public interface BrandService {
    /**
     * 分页查询品牌
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    /**
     * 添加品牌
     * @param brand
     */
    void save(Brand brand);

    /**
     * 修改品牌
     * @param brand
     */
    void update(Brand brand);

    /**
     * 删除品牌
     * @param id
     */
    void remove(Long id);

    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();
}
