package com.example.productservice.service;

import com.example.productservice.model.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Rohal
* @description 针对表【category】的数据库操作Service
* @createDate 2025-02-09 17:53:04
*/
public interface CategoryService extends IService<Category> {

    Category getCategoryByName(String name);
}
