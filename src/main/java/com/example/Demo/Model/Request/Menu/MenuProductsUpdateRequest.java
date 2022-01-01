package com.example.Demo.Model.Request.Menu;

import lombok.Data;

import java.util.Set;

@Data
public class MenuProductsUpdateRequest {

    private Long menuId;
    private Set<Long> products;

}
