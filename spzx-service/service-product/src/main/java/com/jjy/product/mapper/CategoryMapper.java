package com.jjy.product.mapper;

import com.jjy.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/3
 * @time : 12:59
 */
@Mapper
public interface CategoryMapper {
    /**
     * 查询所有一级分类
     * @return
     */
    List<Category> findOneCategory();

    /**
     * 查询所有分类
     * @return
     */
    List<Category> findAll();
}
