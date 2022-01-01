package com.example.Demo.Service;

import com.example.Demo.Comman.EntityDTOConverter;
import com.example.Demo.Model.Dto.MenuDTO;
import com.example.Demo.Model.Entity.Menu;
import com.example.Demo.Model.Entity.Product;
import com.example.Demo.Model.Request.Menu.MenuCreateRequest;
import com.example.Demo.Model.Request.Menu.MenuProductsUpdateRequest;
import com.example.Demo.Model.Request.Menu.MenuUpdateRequest;
import com.example.Demo.Model.Response.Response;
import com.example.Demo.Repository.MenuRepository;
import com.example.Demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ProductRepository productRepository;

    private static final EntityDTOConverter<Menu, MenuDTO> entityToDtoConverter = new EntityDTOConverter(MenuDTO.class);

    public ResponseEntity<?> getAll(){
        try {
            List<Menu> menus = menuRepository.findAll();
            return new ResponseEntity<>(new Response(menus.stream().map(entityToDtoConverter::convert)
                    .collect(Collectors.toCollection(ArrayList::new)),true), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getOneWithId(Long id){
        try {
            var menu = menuRepository.findById(id).get();
            return new ResponseEntity<>(new Response(entityToDtoConverter.convert(menu),true), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> createMenu(MenuCreateRequest dto){
        try {
            Menu newMenu = new Menu();
            newMenu.setMenuName(dto.getMenuName());
            newMenu.setMenuPrice(dto.getMenuPrice());
            Set<Product> products = new HashSet<>();
            for (Long item:dto.getProducts()) {
                var product =productRepository.findById(item);
                if (product.isPresent())
                    products.add(product.get());
            }
            newMenu.getProducts().addAll(products);
            menuRepository.save(newMenu);
            return new ResponseEntity<>(new Response(entityToDtoConverter.convert(newMenu),true), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateMenu(MenuUpdateRequest dto){
        try {
            var menu =menuRepository.findById(dto.getMenuId());
            if (menu.isPresent()){
                var updatemenu = menu.get();
                updatemenu.setMenuName(dto.getMenuName());
                updatemenu.setMenuPrice(dto.getMenuPrice());
                menuRepository.save(updatemenu);
                return new ResponseEntity<>(new Response(entityToDtoConverter.convert(updatemenu),true), HttpStatus.OK);
            }else
                return new ResponseEntity<>("Menu not found", HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> updateMenuProducts(MenuProductsUpdateRequest dto){
        try {
            var menu =menuRepository.findById(dto.getMenuId());
            if (menu.isPresent()){
                var updatemenu = menu.get();
                Set<Product> products = new HashSet<>();
                for (Long item:dto.getProducts()) {
                    var product =productRepository.findById(item).get();
                    products.add(product);
                }
                updatemenu.getProducts().clear();
                menuRepository.save(updatemenu);
                updatemenu.getProducts().addAll(products);
                menuRepository.save(updatemenu);
                return new ResponseEntity<>(new Response(entityToDtoConverter.convert(updatemenu),true), HttpStatus.OK);
            }else
                return new ResponseEntity<>("Menu not found", HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> deleteMenu(Long id){
        try {
            var menu = menuRepository.findById(id);
            if (menu.isPresent()){
                menuRepository.delete(menu.get());
                return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
            }else
                return new ResponseEntity<>("Menu is not exist", HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

}
