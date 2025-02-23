package com.example.productservice.controller;

import com.example.productservice.model.dto.ProductDTO;
import com.example.productservice.model.entity.Product;
import com.example.productservice.service.ProductService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    // 创建商品
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO){
        Product product = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // 商品列表接口
    @PostMapping("/list")
    public List<Product> listProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String categoryName) {
        return productService.listProducts(page, pageSize, categoryName);
    }

    // 商品详情页接口，根据ID获得单个商品详细信息
    @PostMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    // 根据查询字符串搜索产品
    @PostMapping("/search")
    public List<Product> searchProducts(@RequestParam String query) {
        return productService.searchProducts(query);
    }
}
