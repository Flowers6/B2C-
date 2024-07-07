package com.jjy.product.controller;

import com.jjy.model.entity.product.Category;
import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.product.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/3
 * @time : 14:37
 */
@Tag(name = "分类接口管理")
@RestController
@RequestMapping(value="/api/product/category")
@Slf4j
@SuppressWarnings({"unchecked", "rawtypes"})
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Operation(summary = "获取分类树形数据")
    @GetMapping("/findCategoryTree")
    public Result<List<Category>> findCategoryTree(){
        List<Category> list = categoryService.findCategoryTree();
        return Result.build(list,  ResultCodeEnum.SUCCESS);
    }

}
