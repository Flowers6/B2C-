package com.jjy.manager.controller;

import com.jjy.model.entity.product.Category;
import com.jjy.model.vo.common.Result;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.manager.service.impl.CategoryServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 19:36
 */
@RestController
@RequestMapping("/admin/product/category")
public class CategoryController {

    @Resource
    private CategoryServiceImpl categoryService;

    //导入
    @PostMapping("/importData")
    public Result importData(MultipartFile file) {
        //获取上传文件
        categoryService.importData(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //导出
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }

    //分类查询，每次查询一层数据
    @GetMapping("/findCategoryList/{id}")
    public Result findCategoryList(@PathVariable("id") Long parentId) {
          List<Category> list = categoryService.findCategoryList(parentId);
          return Result.build(list, ResultCodeEnum.SUCCESS);
    }

}
