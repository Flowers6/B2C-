package com.jjy.product.mapper;

import com.jjy.model.dto.h5.ProductSkuDto;
import com.jjy.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/3
 * @time : 13:00
 */
@Mapper
public interface ProductSkuMapper {

    /**
     * 查询sale_num前20的商品数据
     * @return
     */
    List<ProductSku> findProductSkuBySale();

    /**
     * 商品列表搜索
     * @param productSkuDto
     * @return
     */
    List<ProductSku> findByPage(ProductSkuDto productSkuDto);

    /**
     * 根据id查询sku信息
     * @param skuId
     * @return
     */
    ProductSku getById(Long skuId);

    /**
     * 根据商品id获取当前商品sku列表
     * @param productId
     * @return
     */
    List<ProductSku> findByProductId(Long productId);
}
