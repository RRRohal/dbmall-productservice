package com.example.productservice.service;

import com.example.productservice.model.dto.ProductDTO;
import com.example.productservice.model.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Rohal
* @description 针对表【product】的数据库操作Service
* @createDate 2025-02-09 17:54:27
*/
public interface ProductService extends IService<Product> {

    Product createProduct(ProductDTO productDTO);

    List<Product> listProducts(int page, int pageSize, String categoryName);

    Product getProductById(int id);

    List<Product> searchProducts(String query);

}
