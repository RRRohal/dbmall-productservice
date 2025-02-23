package com.example.productservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.productservice.model.entity.Category;
import com.example.productservice.service.CategoryService;
import com.example.productservice.mapper.CategoryMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Rohal
 * @description 针对表【category】的数据库操作Service实现
 * @createDate 2025-02-09 17:53:04
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Category getCategoryByName(String name) {
        return categoryMapper.selectOne(new QueryWrapper<Category>().eq("name", name));
    }
}




