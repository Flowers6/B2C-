package com.jjy.product.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.jjy.model.entity.product.Category;
import com.jjy.product.mapper.CategoryMapper;
import com.jjy.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author : Flowers6
 * @version : v1.0
 * @description :
 * @date : 2024/7/3
 * @time : 12:58
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @Override
    public List<Category> findOneCategory() {
        // 从Redis缓存中查询所有的一级分类数据
        String categoryListJSON = redisTemplate.opsForValue().get("category:one");
        if(!StringUtils.isEmpty(categoryListJSON)) {
            List<Category> categoryList = JSON.parseArray(categoryListJSON, Category.class);
            log.info("从Redis缓存中查询到了所有的一级分类数据");
            return categoryList ;
        }

        List<Category> categoryList = categoryMapper.findOneCategory();
        log.info("从数据库中查询到了所有的一级分类数据");
        redisTemplate.opsForValue().set("category:one" , JSON.toJSONString(categoryList) , 7 , TimeUnit.DAYS);
        return categoryList ;
    }

    @Cacheable(value = "category" , key = "'all'")
    @Override
    public List<Category> findCategoryTree() {
        //得到所有集合
        List<Category> allCategoryList = categoryMapper.findAll();

        //得到一级分类
        List<Category> oneCategoryList = allCategoryList.stream()
                .filter(item -> item.getParentId().longValue() == 0)
                .collect(Collectors.toList());

        //遍历一级分类集合，得到一级下二级分类
        oneCategoryList.forEach( oneCategory -> {
            List<Category> twoCategoryList = allCategoryList.stream()
                    .filter(item -> item.getParentId() == oneCategory.getId())
                    .collect(Collectors.toList());

            //把二级分类封装到一级分类
            oneCategory.setChildren(twoCategoryList);

            //遍历二级分类集合，得到二级下三分类集合
            twoCategoryList.forEach(twoCategory -> {
                List<Category> threeCategoryList = allCategoryList.stream()
                        .filter(item -> item.getParentId() == twoCategory.getId())
                        .collect(Collectors.toList());

                //把三级分类封装到二级分类
                twoCategory.setChildren(threeCategoryList);
            });
        });

        return oneCategoryList;
    }
}
