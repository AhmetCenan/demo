package com.example.Demo.Model.Request.Menu;

import lombok.Data;

@Data
public class MenuUpdateRequest {

    private Long menuId;
    private String menuName;
    private Double menuPrice;

}
