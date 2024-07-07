package com.jjy.product.controller;

import com.github.pagehelper.PageInfo;
import com.jjy.model.dto.h5.ProductSkuDto;
import com.jjy.model.entity.product.ProductSku;
import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.model.vo.h5.ProductItemVo;
import com.jjy.product.service.ProductService;
import com.jjy.product.service.impl.ProductServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/4
 * @time : 16:51
 */
@RequestMapping("/api/product")
@RestController
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("{page}/{limit}")
    public Result findByPage(@PathVariable("page") Integer page,
                             @PathVariable("limit") Integer limit,
                             ProductSkuDto productSkuDto) {
        PageInfo<ProductSku> pageInfo = productService.findByPage(page, limit, productSkuDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("item/{skuId}")
    public Result<ProductItemVo> item(@Parameter(name = "skuId", description = "商品skuId", required = true) @PathVariable Long skuId) {
        ProductItemVo productItemVo = productService.item(skuId);
        return Result.build(productItemVo , ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/getBySkuId/{skuId}")
    public ProductSku getBySkuId(@PathVariable("skuId") Long skuId) {
        ProductSku productSku = productService.getBySkuId(skuId);
        return productSku;
    }

}
