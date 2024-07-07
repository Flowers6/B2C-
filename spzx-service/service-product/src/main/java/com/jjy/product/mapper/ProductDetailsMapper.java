package com.jjy.product.mapper;

import com.jjy.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/4
 * @time : 17:38
 */
@Mapper
public interface ProductDetailsMapper {
    /**
     * 根据商品id获取商品详情
     * @param productId
     * @return
     */
    ProductDetails getByProductId(Long productId);
}
