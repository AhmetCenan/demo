package com.example.Demo.Model.Response;

import lombok.Data;

import java.util.Set;

@Data
public class MenuResponse {

    private String menuName;
    private String menuPrice;
    private Set<ProductResponse> menuProducts;

}
