package com.example.Demo.Model.Request.Product;

import lombok.Data;

@Data
public class ProductUpdateRequest {

    private Long productId;
    private String productName;
    private Float productPrice;
    private String productWeight;

}
