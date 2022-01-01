package com.example.Demo.Service;

import com.example.Demo.Comman.EntityDTOConverter;
import com.example.Demo.Model.Dto.MenuDTO;
import com.example.Demo.Model.Dto.ProductDTO;
import com.example.Demo.Model.Entity.Menu;
import com.example.Demo.Model.Entity.Product;
import com.example.Demo.Model.Request.Product.ProductCreateRequest;
import com.example.Demo.Model.Request.Product.ProductUpdateRequest;
import com.example.Demo.Model.Response.Response;
import com.example.Demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private static final EntityDTOConverter<Product, ProductDTO> entityToDtoConverter = new EntityDTOConverter(ProductDTO.class);

    public ResponseEntity<?> GetAll(){
        try {
            List<Product> products = productRepository.findAll();
            return new ResponseEntity<>(new Response(products.stream().map(entityToDtoConverter::convert)
                    .collect(Collectors.toCollection(ArrayList::new)), true), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> GetOneWithId(Long id){
        try {
            var product = productRepository.findById(id).get();
            return new ResponseEntity<>(new Response(entityToDtoConverter.convert(product), true), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> GetOneWithName(String name){
        try {
            var product = productRepository.findByProductName(name);
            return new ResponseEntity<>(new Response(entityToDtoConverter.convert(product), true), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> GetOneWithFilter(String filter){
        try {
            var products = productRepository.findByFilter(filter);
            return new ResponseEntity<>(new Response(products.stream().map(entityToDtoConverter::convert)
                    .collect(Collectors.toCollection(ArrayList::new)), true), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> Create(ProductCreateRequest dto){
        try {
            Product product = new Product();
            product.setProductName(dto.getProductName());
            product.setProductPrice(dto.getProductPrice());
            product.setProductWeight(dto.getProductWeight());
            productRepository.save(product);
            return new ResponseEntity<>(new Response(entityToDtoConverter.convert(product), true), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> Update(ProductUpdateRequest dto){
        try {
            var product = productRepository.findById(dto.getProductId()).get();
            product.setProductName(dto.getProductName());
            product.setProductPrice(dto.getProductPrice());
            product.setProductWeight(dto.getProductWeight());
            productRepository.save(product);
            return new ResponseEntity<>(new Response(entityToDtoConverter.convert(product), true), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> Delete(Long id){
        try {
            var product = productRepository.findById(id);
            if (product.isPresent()){
                productRepository.delete(product.get());
                return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Product is not exist", HttpStatus.BAD_REQUEST);
        }catch (Exception ex){
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

}
