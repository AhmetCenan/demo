package com.example.Demo.Model.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "menu_products",schema = "public")
public class MenuProducts {

    @Column(name = "menu_id")
    private Long menuId;
    @Column(name = "product_id")
    private Long productId;

}
