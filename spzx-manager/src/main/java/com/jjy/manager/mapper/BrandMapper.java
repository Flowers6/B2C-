package com.jjy.manager.mapper;

import com.jjy.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/6/29
 * @time : 22:10
 */
@Mapper
public interface BrandMapper {
    /**
     * 分页查询
     * @return
     */
    List<Brand> findByPage();

    /**
     * 添加品牌
     * @param brand
     */
    void save(Brand brand);

    /**
     * 修改品牌
     * @param brand
     */
    void update(Brand brand);

    /**
     * 删除品牌
     * @param id
     */
    void remove(Long id);

    /**
     * 查询所有品牌
     * @return
     */
    List<Brand> findAll();
}
