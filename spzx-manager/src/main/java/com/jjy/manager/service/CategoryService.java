package com.jjy.manager.service;

import com.jjy.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 19:38
 */
public interface CategoryService {
    /**
     * 根据id查询分类,只查一层数据
     * @param id
     * @return
     */
    List<Category> findCategoryList(Long id);

    /**
     * 导出数据
     * @param response
     */
    void exportData(HttpServletResponse response);

    /**
     * 导入数据
     * @param file
     */
    void importData(MultipartFile file);
}
