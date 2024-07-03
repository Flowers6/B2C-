package com.jjy.manager.mapper;

import com.jjy.model.dto.product.CategoryBrandDto;
import com.jjy.model.entity.product.Brand;
import com.jjy.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/1
 * @time : 15:46
 */
@Mapper
public interface CategoryBrandMapper {
    /**
     * 分页查询
     * @param categoryBrandDto
     * @return
     */
    List<CategoryBrand> findByPage(CategoryBrandDto categoryBrandDto);

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
     * 根据分类id查询品牌
     * @param categoryId
     * @return
     */
    List<Brand> findBrandByCategoryId(Long categoryId);
}
