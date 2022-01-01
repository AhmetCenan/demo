package com.example.Demo.Controller;

import com.example.Demo.Model.Request.Menu.MenuCreateRequest;
import com.example.Demo.Model.Request.Menu.MenuProductsUpdateRequest;
import com.example.Demo.Model.Request.Menu.MenuUpdateRequest;
import com.example.Demo.Model.Response.Response;
import com.example.Demo.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class MenuController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    public ResponseEntity<?> getAllMenus(){
        return menuService.getAll();
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<?> getOneMenu(@PathVariable("id")Long id){
        return menuService.getOneWithId(id);
    }

    @PostMapping("/menu/new")
    public ResponseEntity<?> newMenu(@RequestBody MenuCreateRequest dto){
        return menuService.createMenu(dto);
    }

    @PutMapping("/menu/update")
    public ResponseEntity<?> updateMenu(@RequestBody MenuUpdateRequest dto){
        return menuService.updateMenu(dto);
    }

    @PutMapping("/menu/products")
    public ResponseEntity<?> updateMenuProducts(@RequestBody MenuProductsUpdateRequest dto){
        return menuService.updateMenuProducts(dto);
    }

    @DeleteMapping("/menu/delete/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable("id") Long id){
        return menuService.deleteMenu(id);
    }

}
