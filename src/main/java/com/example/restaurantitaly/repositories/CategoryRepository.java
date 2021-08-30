package com.example.restaurantitaly.repositories;

import com.example.restaurantitaly.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findCategoryById(int id);

    Optional<Category> findCategoryByCategoryType(String type);


}
