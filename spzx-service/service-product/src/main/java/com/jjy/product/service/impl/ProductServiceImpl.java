package com.jjy.product.service.impl;

import com.jjy.product.mapper.ProductSkuMapper;
import com.jjy.model.entity.product.ProductSku;
import com.jjy.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/3
 * @time : 12:59
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public List<ProductSku> findProductSkuBySale() {
        List<ProductSku> list = productSkuMapper.findProductSkuBySale();
        return list;
    }
}
