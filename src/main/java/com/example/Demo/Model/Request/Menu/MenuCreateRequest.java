package com.example.Demo.Model.Request.Menu;

import com.example.Demo.Model.Entity.Product;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
public class MenuCreateRequest {

    private String menuName;
    private Double menuPrice;
    private Set<Long> products;

}
