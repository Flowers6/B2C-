package com.jjy.manager.mapper;

import com.jjy.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 8:56
 */
@Mapper
public interface ProductUnitMapper {

    /**
     * 查询所有产品单元数据
     * @return
     */
    List<ProductUnit> findAll();
}
