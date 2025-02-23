package com.example.productservice.service;

import com.example.productservice.model.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Rohal
 * @description 针对表【product_category】的数据库操作Service
 * @createDate 2025-02-09 17:55:17
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    List<Integer> getProductIdsByCategoryId(Integer categoryId);

}
