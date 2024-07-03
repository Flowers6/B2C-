package com.jjy.manager.service;

import com.github.pagehelper.PageInfo;
import com.jjy.model.dto.product.ProductDto;
import com.jjy.model.entity.product.Product;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 8:36
 */
public interface ProductService {
    /**
     * 商品分页查询
     * @param page
     * @param limit
     * @param productDto
     * @return
     */
    PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto);

    /**
     * 添加商品
     * @param product
     */
    void save(Product product);

    /**
     * 通过id查询商品信息
     * @param id
     * @return
     */
    Product getById(Long id);

    /**
     * 根据id修改商品信息
     * @param product
     */
    void updateById(Product product);

    /**
     * 根据商品id删除商品信息
     * @param id
     */
    void deleteById(Long id);

    /**
     * 修改商品审核状态
     * @param id
     * @param auditStatus
     */
    void updateAuditStatus(Long id, Integer auditStatus);

    /**
     * 修改商品上架状态
     * @param id
     * @param status
     */
    void updateStatus(Long id, Integer status);
}
