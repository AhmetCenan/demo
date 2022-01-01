package com.example.Demo.Repository;

import com.example.Demo.Model.Entity.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu,Long> {

    List<Menu> findAll();

}
