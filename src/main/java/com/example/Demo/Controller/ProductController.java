package com.example.Demo.Controller;


import com.example.Demo.Model.Request.Product.ProductCreateRequest;
import com.example.Demo.Model.Request.Product.ProductUpdateRequest;
import com.example.Demo.Model.Response.Response;
import com.example.Demo.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<?> getAllProducts(){
        return productService.GetAll();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable("id") Long id){
        return productService.GetOneWithId(id);
    }

    @PostMapping("/product/{name}")
    public ResponseEntity<?> getProductWithName(@PathVariable("name") String name){
        return productService.GetOneWithName(name);
    }

    @PostMapping("/product/filter/{filter}")
    public ResponseEntity<?> getProductsWithFilter(@PathVariable("filter") String filter){
        return productService.GetOneWithFilter(filter);
    }

    @PostMapping("/product/newproduct")
    public ResponseEntity<?> createProduct(@RequestBody ProductCreateRequest dto){
        return productService.Create(dto);
    }

    @PutMapping("/product/update")
    public ResponseEntity<?> updataProduct(@RequestBody ProductUpdateRequest dto){
        return productService.Update(dto);
    }

    @DeleteMapping("/product/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id")Long id){
        return productService.Delete(id);
    }

}
