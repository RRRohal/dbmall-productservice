package com.example.productservice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
@MapperScan("com.example.productservice.mapper")
public class ProductserviceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProductserviceApplication.class, args);
    }

}
