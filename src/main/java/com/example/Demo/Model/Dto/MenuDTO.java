package com.example.Demo.Model.Dto;

import com.example.Demo.Model.Entity.Product;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class MenuDTO {
    private Long menuId;
    private String menuName;
    private Double menuPrice;
    private Set<Product> products = new HashSet<>();
}
