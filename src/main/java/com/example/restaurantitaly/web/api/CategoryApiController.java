package com.example.restaurantitaly.web.api;


import com.example.restaurantitaly.services.CategoryService;
import com.example.restaurantitaly.services.ProductService;
import com.example.restaurantitaly.web.controllers.BaseController;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class CategoryApiController extends BaseController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public CategoryApiController(ProductService productService, ModelMapper modelMapper, CategoryService categoryService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

//
//    @GetMapping(value = "/all/{name}")
//    public List<AddProductResponseModel> getProducts(@PathVariable String name) {
//
//        List<ProductServiceModel> allProducts = productService.getAllProducts();
//
//        CategoryServiceModel categoryByName = categoryService.getCategoryByName(name);
//
//        List<ProductServiceModel> productByName = productService.getProductByName(categoryByName);
//
//        List<AddProductResponseModel> collect = productByName.stream().map(a -> modelMapper.map(a, AddProductResponseModel.class)).collect(Collectors.toList());
//
//       return collect;
//    }


}
