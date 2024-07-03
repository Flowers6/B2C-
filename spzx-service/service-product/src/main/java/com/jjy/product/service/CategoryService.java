package com.jjy.product.service;

import com.jjy.model.entity.product.Category;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/3
 * @time : 12:58
 */
public interface CategoryService {
    /**
     * 查询所有一级分类
     * @return
     */
    List<Category> findOneCategory();
}
