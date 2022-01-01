package com.example.Demo.Model.Dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private Float productPrice;
    private String productWeight;
}
