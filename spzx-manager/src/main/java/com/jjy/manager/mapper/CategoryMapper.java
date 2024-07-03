package com.jjy.manager.mapper;

import com.jjy.model.entity.product.Category;
import com.jjy.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 19:39
 */
@Mapper
public interface CategoryMapper {
    /**
     * 根据id查询得到一层分类list集合
     * @param id
     * @return
     */
    List<Category> selectCategoryByParentId(Long id);

    /**
     * 查询是否有下一级分类
     * @param ParentId
     * @return
     */
    int countByParentId(Long ParentId);

    /**
     * 查所有分类
     * @return
     */
    List<Category> findAll();


    /**
     * 批量插入
     * @param categoryList
     */
    void batchInsert(List<CategoryExcelVo> categoryList);
}
