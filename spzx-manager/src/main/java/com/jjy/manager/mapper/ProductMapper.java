package com.jjy.manager.mapper;

import com.jjy.model.dto.product.ProductDto;
import com.jjy.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 8:36
 */
@Mapper
public interface ProductMapper {
    /**
     * 分页查询
     * @param productDto
     * @return
     */
    List<Product> findByPage(ProductDto productDto);

    /**
     * 添加商品
     * @param product
     */
    void save(Product product);

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    Product selectById(Long id);

    /**
     * 根据商品id修改商品详情
     * @param product
     */
    void updateById(Product product);

    /**
     * 根据商品id删除商品详情
     * @param id
     */
    void deleteById(Long id);
}
