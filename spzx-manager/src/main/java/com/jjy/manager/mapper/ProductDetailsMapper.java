package com.jjy.manager.mapper;

import com.jjy.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 9:10
 */
@Mapper
public interface ProductDetailsMapper {
    /**
     * 添加商品详情
     * @param productDetails
     */
    void save(ProductDetails productDetails);

    /**
     * 根据商品id查询商品详情
     * @param id
     * @return
     */
    ProductDetails selectByProductId(Long id);

    /**
     * 根据商品id修改商品详情
     * @param productDetails
     */
    void updateById(ProductDetails productDetails);

    /**
     * 根据商品id删除商品详情
     * @param id
     */
    void deleteByProductId(Long id);
}
