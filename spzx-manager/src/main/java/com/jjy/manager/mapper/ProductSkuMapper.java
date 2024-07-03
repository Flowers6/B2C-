package com.jjy.manager.mapper;

import com.jjy.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 9:09
 */
@Mapper
public interface ProductSkuMapper {
    /**
     * 添加产品sku
     * @param productSku
     */
    void save(ProductSku productSku);

    /**
     * 根据商品id查询商品sku
     * @param id
     * @return
     */
    List<ProductSku> selectByProductId(Long id);

    /**
     * 根据商品id修改商品sku
     * @param productSku
     */
    void updateById(ProductSku productSku);

    /**
     * 根据商品id删除商品sku
     * @param id
     */
    void deleteByProductId(Long id);
}
