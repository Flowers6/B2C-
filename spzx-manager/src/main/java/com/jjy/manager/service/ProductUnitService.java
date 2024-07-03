package com.jjy.manager.service;

import com.jjy.model.entity.base.ProductUnit;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 8:55
 */
public interface ProductUnitService {
    /**
     * 查找所有产品单元数据
     * @return
     */
    List<ProductUnit> findAll();
}
