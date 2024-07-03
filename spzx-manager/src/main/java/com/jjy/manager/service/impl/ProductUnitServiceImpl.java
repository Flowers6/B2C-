package com.jjy.manager.service.impl;

import com.jjy.manager.mapper.ProductUnitMapper;
import com.jjy.model.entity.base.ProductUnit;
import com.jjy.manager.service.ProductUnitService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/2
 * @time : 8:55
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    @Resource
    private ProductUnitMapper productUnitMapper;

    @Override
    public List<ProductUnit> findAll() {
        List<ProductUnit> list = productUnitMapper.findAll();
        return list;
    }
}
