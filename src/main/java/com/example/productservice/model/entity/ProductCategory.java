package com.example.productservice.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName product_category
 */
@TableName(value ="product_category")
@Data
public class ProductCategory implements Serializable {
    /**
     * 产品 ID（外键）
     */
    @TableField("product_id")
    private Integer productId;

    /**
     * 分类 ID（外键）
     */
    @TableField("category_id")
    private Integer categoryId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}