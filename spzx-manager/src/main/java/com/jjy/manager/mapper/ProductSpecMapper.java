package com.jjy.manager.mapper;

import com.jjy.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/1
 * @time : 17:08
 */
@Mapper
public interface ProductSpecMapper {
    /**
     * 产品规格分页查询
     * @return
     */
    List<ProductSpec> findByPage();

    /**
     * 添加产品规格
     * @param productSpec
     */
    void save(ProductSpec productSpec);

    /**
     * 修改产品规格
     * @param productSpec
     */
    void updateById(ProductSpec productSpec);

    /**
     * 删除产品规格
     * @param id
     */
    void deleteById(Long id);

    /**
     * 查询所有产品规格
     * @return
     */
    List<ProductSpec> findAll();
}
