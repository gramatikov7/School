package com.example.restaurantitaly.services;

import com.example.restaurantitaly.domain.models.service.CategoryServiceModel;

import java.util.List;

public interface CategoryService {

    List<CategoryServiceModel> getAllCategories();

    CategoryServiceModel getCategoryById(int id);

    CategoryServiceModel getCategoryByType(String type);

    CategoryServiceModel findByName(String name);

    void addCategory(CategoryServiceModel categoryServiceModel);
}
