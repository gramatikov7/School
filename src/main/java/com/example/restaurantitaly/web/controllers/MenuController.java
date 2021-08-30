package com.example.restaurantitaly.web.controllers;

import com.example.restaurantitaly.domain.models.binding.CategoryModel;
import com.example.restaurantitaly.domain.models.service.CategoryServiceModel;
import com.example.restaurantitaly.domain.models.view.ProductViewModel;
import com.example.restaurantitaly.services.CategoryService;
import com.example.restaurantitaly.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public MenuController(ProductService productService, CategoryService categoryService, ModelMapper modelMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView allProducts(ModelAndView modelAndView) {


        List<CategoryServiceModel> allCategories = categoryService.getAllCategories();

        ModelAndView products = modelAndView.addObject("categories", allCategories);

        return view("menu/menu", products);

    }


    @GetMapping("/details/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView menuByCategory (@PathVariable int id , ModelAndView modelAndView) {

        List<ProductViewModel> byCategory = productService.findByCategory(id)
                .stream()
                .map( a -> modelMapper.map(a , ProductViewModel.class))
                .collect(Collectors.toList());

        ModelAndView products = modelAndView.addObject("products", byCategory);

        return view("menu/menu-by-category" , products);
    }

    @GetMapping("/fetch")
    @ResponseBody
    public List<CategoryModel> allCategory(){

        List<CategoryServiceModel> allCategories = categoryService.getAllCategories();

        List<CategoryModel> collect = allCategories
                .stream()
                .map(a -> modelMapper.map(a, CategoryModel.class))
                .collect(Collectors.toList());

        return collect;


    }
}
