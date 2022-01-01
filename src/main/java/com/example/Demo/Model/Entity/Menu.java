package com.example.Demo.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "menus",schema = "public")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private String menuName;
    private Double menuPrice;
    @ManyToMany(cascade=CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(
            name = "menu_products",
            joinColumns = @JoinColumn(name = "menuId"),
            inverseJoinColumns = @JoinColumn(name = "productId"))
    private Set<Product> products = new HashSet<>();
}
