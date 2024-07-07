package com.jjy.product.mapper;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/4
 * @time : 17:36
 */

import com.jjy.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    /**
     * 根据id获取product
     * @param productId
     * @return
     */
    Product getById(Long productId);
}
