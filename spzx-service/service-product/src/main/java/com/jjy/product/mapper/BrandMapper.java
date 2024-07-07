package com.jjy.product.mapper;

import com.jjy.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/4
 * @time : 16:46
 */
@Mapper
public interface BrandMapper {
    /**
     * 查询全部品牌
     * @return
     */
    List<Brand> findAll();
}
