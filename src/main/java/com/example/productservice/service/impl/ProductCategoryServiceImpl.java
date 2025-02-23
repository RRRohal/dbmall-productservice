package com.example.productservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.productservice.model.entity.ProductCategory;
import com.example.productservice.service.ProductCategoryService;
import com.example.productservice.mapper.ProductCategoryMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author Rohal
* @description 针对表【product_category】的数据库操作Service实现
* @createDate 2025-02-09 17:55:17
*/

@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory>
    implements ProductCategoryService{

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public List<Integer> getProductIdsByCategoryId(Integer categoryId){
        return productCategoryMapper.selectList(new QueryWrapper<ProductCategory>().eq("category_id", categoryId))
                .stream().map(ProductCategory::getProductId)
                .collect(Collectors.toList());
    }

}




