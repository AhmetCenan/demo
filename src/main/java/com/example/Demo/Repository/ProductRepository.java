package com.example.Demo.Repository;


import com.example.Demo.Model.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {

    List<Product> findAll();
    Product findByProductName(String name);

    @Query(value = "select * from products p where p.product_name like %:filter%",nativeQuery = true)
    List<Product> findByFilter(@Param("filter")String filter);

}
