package com.jjy.manager.service;

import com.github.pagehelper.PageInfo;
import com.jjy.model.dto.product.CategoryBrandDto;
import com.jjy.model.entity.product.Brand;
import com.jjy.model.entity.product.CategoryBrand;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/1
 * @time : 15:44
 */
public interface CategoryBrandService {
    /**
     * 分页查询
     * @param page
     * @param limit
     * @param categoryBrandDto
     * @return
     */
    PageInfo<CategoryBrand> findByPage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    /**
     * 添加分类品牌
     * @param categoryBrand
     */
    void save(CategoryBrand categoryBrand);

    /**
     * 修改分类品牌
     * @param categoryBrand
     */
    void updateById(CategoryBrand categoryBrand);

    /**
     * 删除分类品牌
     * @param id
     */
    void deleteById(Long id);

    /**
     * 通过分类id查询品牌
     * @param categoryId
     * @return
     */
    List<Brand> findBrandByCategoryId(Long categoryId);
}
