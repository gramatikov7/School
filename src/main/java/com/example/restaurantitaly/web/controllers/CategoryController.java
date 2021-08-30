package com.example.restaurantitaly.web.controllers;

import com.example.restaurantitaly.domain.models.binding.CategoryModel;
import com.example.restaurantitaly.domain.models.service.CategoryServiceModel;
import com.example.restaurantitaly.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;


    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView allProducts(@ModelAttribute(name = "model") CategoryModel categoryModel ,
                                   ModelAndView modelAndView){

        modelAndView.addObject("model", categoryModel);

        return view("add-category");

    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView addCategory(@ModelAttribute(name = "model") @Valid CategoryModel categoryModel ,
                                   BindingResult bindingResult){


        if(bindingResult.hasErrors()){

            return view("add-category");

        }

        CategoryServiceModel categoryServiceModel = modelMapper.map(categoryModel, CategoryServiceModel.class);

        categoryService.addCategory(categoryServiceModel);

        return redirect("/menu/all");

    }


    @GetMapping("/fetch")
    @ResponseBody
    public List<CategoryServiceModel> fetchCategories() {

        return this.categoryService.getAllCategories();
    }
}
