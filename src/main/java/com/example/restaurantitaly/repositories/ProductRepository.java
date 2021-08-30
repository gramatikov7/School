package com.example.restaurantitaly.repositories;


import com.example.restaurantitaly.domain.entities.Category;
import com.example.restaurantitaly.domain.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

    List<Products> findAllByCategory(Category category);
    List<Products> findAllByCategoryId(int id);

    Products findById(int id);
}
