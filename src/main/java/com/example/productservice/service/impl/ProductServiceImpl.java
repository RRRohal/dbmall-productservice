package com.example.productservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.mapper.ProductCategoryMapper;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.dto.ProductDTO;
import com.example.productservice.model.entity.Category;
import com.example.productservice.model.entity.Product;
import com.example.productservice.model.entity.ProductCategory;
import com.example.productservice.service.CategoryService;
import com.example.productservice.service.ProductCategoryService;
import com.example.productservice.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
* @author Rohal
* @description 针对表【product】的数据库操作Service实现
* @createDate 2025-02-09 17:54:27
*/

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

    @Resource
    private CategoryService categoryService;

    @Resource
    private ProductCategoryService productCategoryService;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductCategoryMapper productCategoryMapper;

    // 创建商品
    public Product createProduct(ProductDTO productDTO){
        // 检查商品分类是否存在
        List<Integer> categoryIds = new ArrayList<>();
        for(String categoryName: productDTO.getCategoryName()){
            Category category = categoryService.getCategoryByName(categoryName);
            if (category == null){
                throw new CategoryNotFoundException("分类 '"+categoryName+"' 不存在");
            }
            categoryIds.add(category.getId());
        }


        //创建商品
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setPicture(productDTO.getPicture());
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());

        //保存商品
        productMapper.insert(product);

        // 创建商品和分类的关联关系
        for(Integer categoryId: categoryIds){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductId(product.getId());
            productCategory.setCategoryId(categoryId);
            productCategoryMapper.insert(productCategory);
        }

        return product;
    }

    // 商品清单
    @Override
    public List<Product> listProducts(int page, int pageSize, String categoryName){

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        // 如果 categoryName 为空，查询所有商品
        if (categoryName == null || categoryName.trim().isEmpty()) {
            Page<Product> pageInfo = new Page<>(page, pageSize);
            return productMapper.selectPage(pageInfo, queryWrapper).getRecords();
        }

        // 查询分类Id
        Category category = categoryService.getCategoryByName(categoryName);
        if (category == null) {
            throw new CategoryNotFoundException("分类 '" + categoryName + "' 不存在");
        }

        // 查询该分类下的所有商品ID
        List<Integer> productIds = productCategoryService.getProductIdsByCategoryId(category.getId());
        if (productIds.isEmpty()) {
            return List.of(); // 没有商品，返回空列表
        }
        // 只查询这些 ID 对应的商品
        queryWrapper.in("id", productIds);

        Page<Product> pageInfo = new Page<>(page, pageSize);
        return productMapper.selectPage(pageInfo, queryWrapper).getRecords();
    }

    // 商品详情
    @Override
    public Product getProductById(int id){
        // 根据 ID 查询单个商品
        return productMapper.selectById(id);
    }

    // 搜索商品
    @Override
    public List<Product> searchProducts(String query){
        // 模糊查询商品名称或描述
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", query).or().like("description", query);
        return productMapper.selectList(queryWrapper);
    }

}




