package com.jjy.product.service;

import com.github.pagehelper.PageInfo;
import com.jjy.model.dto.h5.ProductSkuDto;
import com.jjy.model.entity.product.ProductSku;
import com.jjy.model.vo.h5.ProductItemVo;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/3
 * @time : 12:58
 */
public interface ProductService {
    /**
     * 根据销量，获取前10条记录
     * @return
     */
    List<ProductSku> findProductSkuBySale();

    /**
     * 品牌分页查询
     * @param page
     * @param limit
     * @param productSkuDto
     * @return
     */
    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    /**
     * 商品详情接口
     * @param skuId
     * @return
     */
    ProductItemVo item(Long skuId);

    /**
     * 远程调用skuId返回sku对象接口哦
     * @param skuId
     * @return
     */
    ProductSku getBySkuId(Long skuId);
}
