package com.example.productservice.service;

import com.example.productservice.model.entity.Product;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProductServiceTest {

    @Resource
    private ProductService productService;

    @Test
    void testList(){
        List<Product> products = productService.listProducts(1,1, "Sticker");
        assertNotNull(products);
        System.out.println("查询到的产品列表:");
        products.forEach(product -> System.out.println("ID: " + product.getId() +
                ", 名称: " + product.getName() +
                ", 描述: " + product.getDescription() +
                ", 价格: " + product.getPrice() +
                ", 创建时间" + product.getCreatedAt()));
    }

    @Test
    void testGetById(){
        System.out.println(productService.getProductById(1).getName());
    }


}
