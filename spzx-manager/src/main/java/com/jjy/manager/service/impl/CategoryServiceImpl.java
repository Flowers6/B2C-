package com.jjy.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.jjy.common.exception.CustomException;
import com.jjy.manager.listener.ExcelListener;
import com.jjy.manager.mapper.CategoryMapper;
import com.jjy.model.entity.product.Category;
import com.jjy.model.vo.common.ResultCodeEnum;
import com.jjy.model.vo.product.CategoryExcelVo;
import com.jjy.manager.service.CategoryService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 19:38
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findCategoryList(Long parentId) {
        //根据id查询得到一层数据list集合
        List<Category> categoryList = categoryMapper.selectCategoryByParentId(parentId);

        //遍历返回的list集合，判断每个分类是否有下一层分类，有 设置 has_children true 没有 false
        if (!CollectionUtils.isEmpty(categoryList)) {
            // 遍历分类的集合，获取每一个分类数据
            categoryList.forEach(item -> {

                // 查询该分类下子分类的数量
                int count = categoryMapper.countByParentId(item.getId());
                if(count > 0) {
                    item.setHasChildren(true);
                } else {
                    item.setHasChildren(false);
                }

            });
        }
        return categoryList;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            //设置响应头信息和其他信息
            // 设置响应结果类型
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");

            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("分类数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

            //调用mapper的方法，查询所有的分类，返回list集合
            List<Category> categoryList = categoryMapper.findAll();

            //转换Category为CategoryExcelVo

            List<CategoryExcelVo> categoryExcelVoList = new ArrayList<>(categoryList.size());
            for (Category category : categoryList) {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category, categoryExcelVo);
                categoryExcelVoList.add(categoryExcelVo);
            }

            //调用easyExcel写方法
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet().doWrite(categoryExcelVoList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException(ResultCodeEnum.DATA_ERROR);
        }
    }

    @Override
    public void importData(MultipartFile file) {
        try {
            //监听器
            ExcelListener<CategoryExcelVo> excelListener = new ExcelListener(categoryMapper);
            EasyExcel.read(file.getInputStream(), CategoryExcelVo.class, excelListener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(ResultCodeEnum.DATA_ERROR);
        }
    }
}
