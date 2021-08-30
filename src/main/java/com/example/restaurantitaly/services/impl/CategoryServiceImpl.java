package com.example.restaurantitaly.services.impl;


import com.example.restaurantitaly.domain.entities.Category;
import com.example.restaurantitaly.domain.models.service.CategoryServiceModel;
import com.example.restaurantitaly.repositories.CategoryRepository;
import com.example.restaurantitaly.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<CategoryServiceModel> getAllCategories() {
        List<Category> all = categoryRepository.findAll();
        List<CategoryServiceModel> categories = all
                .stream()
                .map(a -> modelMapper.map(a, CategoryServiceModel.class))
                .collect(Collectors.toList());

        return categories;
    }


    @Override
    public CategoryServiceModel getCategoryById(int id) {
        Category categoryById = categoryRepository.findCategoryById(id);
        CategoryServiceModel map = modelMapper.map(categoryById, CategoryServiceModel.class);
        return map;
    }

    @Override
    public CategoryServiceModel getCategoryByType(String type) {

        Category category = categoryRepository.findCategoryByCategoryType(type).get();

        return modelMapper.map(category , CategoryServiceModel.class);
    }


    @Override
    public CategoryServiceModel findByName(String name) {

        Category categoryByType = categoryRepository.findCategoryByCategoryType(name).get();
        CategoryServiceModel currCategory = modelMapper.map(categoryByType, CategoryServiceModel.class);

        return currCategory;
    }

    @Override
    public void addCategory(CategoryServiceModel categoryServiceModel) {
        Optional<Category> categoryType = categoryRepository.findCategoryByCategoryType(categoryServiceModel.getCategoryType());

        if(categoryType.isEmpty()){
            Category category = modelMapper.map(categoryServiceModel, Category.class);

            categoryRepository.save(category);
        }



    }
}
