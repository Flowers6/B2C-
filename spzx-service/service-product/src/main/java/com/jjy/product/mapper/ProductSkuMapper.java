package com.jjy.product.mapper;

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
}
