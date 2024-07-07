package com.jjy.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jjy.model.dto.h5.ProductSkuDto;
import com.jjy.model.entity.product.Product;
import com.jjy.model.entity.product.ProductDetails;
import com.jjy.model.vo.h5.ProductItemVo;
import com.jjy.product.mapper.ProductDetailsMapper;
import com.jjy.product.mapper.ProductMapper;
import com.jjy.product.mapper.ProductSkuMapper;
import com.jjy.model.entity.product.ProductSku;
import com.jjy.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ProductDetailsMapper productDetailsMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public List<ProductSku> findProductSkuBySale() {
        List<ProductSku> list = productSkuMapper.findProductSkuBySale();
        return list;
    }

    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page, limit);
        // 根据productSkuDto查询
        List<ProductSku> productSkuList = productSkuMapper.findByPage(productSkuDto);
        return new PageInfo<>(productSkuList);
    }

    @Override
    public ProductItemVo item(Long skuId) {
        //当前sku信息
        ProductSku productSku = productSkuMapper.getById(skuId);

        //当前商品信息
        Product product = productMapper.getById(productSku.getProductId());

        //同一个商品下面的sku信息列表
        List<ProductSku> productSkuList = productSkuMapper.findByProductId(productSku.getProductId());
        //建立sku规格与skuId对应关系
        Map<String,Object> skuSpecValueMap = new HashMap<>();
        productSkuList.forEach(item -> {
            skuSpecValueMap.put(item.getSkuSpec(), item.getId());
        });

        //商品详情信息
        ProductDetails productDetails = productDetailsMapper.getByProductId(productSku.getProductId());

        ProductItemVo productItemVo = new ProductItemVo();
        productItemVo.setProductSku(productSku);
        productItemVo.setProduct(product);
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);
        return productItemVo;
    }

    @Override
    public ProductSku getBySkuId(Long skuId) {
        ProductSku productSku = productSkuMapper.getById(skuId);
        return productSku;
    }
}
