package com.example.restaurantitaly.web.controllers;


import com.example.restaurantitaly.domain.models.binding.ProductEditModel;
import com.example.restaurantitaly.domain.models.binding.ProductModel;
import com.example.restaurantitaly.domain.models.service.CategoryServiceModel;
import com.example.restaurantitaly.domain.models.service.ProductServiceModel;
import com.example.restaurantitaly.domain.models.service.UserServiceModel;
import com.example.restaurantitaly.domain.models.view.ProductViewModel;
import com.example.restaurantitaly.services.CategoryService;
import com.example.restaurantitaly.services.OrderItemService;
import com.example.restaurantitaly.services.ProductService;
import com.example.restaurantitaly.services.UserService;
import com.example.restaurantitaly.untils.CloudinaryService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {


    private final CategoryService categoryService;
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final CloudinaryService cloudinaryService;
    private final UserService userService;
    private final OrderItemService orderItemService;

    public ProductController(CategoryService categoryService, ProductService productService, ModelMapper modelMapper, CloudinaryService cloudinaryService, UserService userService, OrderItemService orderItemService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.cloudinaryService = cloudinaryService;
        this.userService = userService;
        this.orderItemService = orderItemService;
    }


    @GetMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView add(@ModelAttribute(name = "model") ProductModel productModel,
                            ModelAndView modelAndView) {


        modelAndView.addObject("model", productModel);


        return view("product/add-product");

    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView addConfig(@ModelAttribute(name = "model")  @Valid ProductModel products,
                                  BindingResult bindingResult, Principal principal) throws IOException {

        if (bindingResult.hasErrors()) {

            return view("product/add-product");

        }


        ProductServiceModel productServiceModel = modelMapper.map(products, ProductServiceModel.class);


        String categoryName = products.getCategory();
        CategoryServiceModel category = categoryService.getCategoryByType(categoryName);

        productServiceModel.setImageUrl(
                this.cloudinaryService.uploadImage(products.getImage())
        );

        productServiceModel.setCategoryType(category);


        String name = principal.getName();

        UserServiceModel user = userService.getUserByUsername(name);

        int id = user.getId();


        productService.addProduct(productServiceModel, id);

        return redirect("/menu/all");
    }



    @GetMapping("/fetch/{id}")
    @ResponseBody
//    @PreAuthorize("isAuthenticated()")
//    @PageTitle("Bicycle {name}")
    public List<ProductViewModel> getMenuByCategory(@PathVariable int id ) {

        List<ProductViewModel> byCategory = productService.findByCategory(id)
                .stream()
                .map( a -> modelMapper.map(a , ProductViewModel.class))
                .collect(Collectors.toList());


        return byCategory;
    }


    @GetMapping("/details/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView productsByCategory (@PathVariable int id , ModelAndView modelAndView) {

        ProductServiceModel productById = productService.getProductById(id);
        ProductViewModel map = modelMapper.map(productById, ProductViewModel.class);

        ModelAndView products = modelAndView.addObject("products", map);

        return view("product/product-details" , products);
    }




    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView delete(@PathVariable int id){


        productService.setProductIsDeleted(id);
        return redirect("/menu/all");

    }


    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView edit(@PathVariable int id,  ModelAndView modelAndView){
        ProductServiceModel serviceModel = productService.getProductById(id);
        ProductEditModel productEditModel = modelMapper.map(serviceModel, ProductEditModel.class);

        modelAndView.addObject("model", productEditModel);

        return view("product/edit-product", modelAndView);
    }


    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ModelAndView editConfirm(@PathVariable int id, @ModelAttribute(name = "model") ProductEditModel editModel,
                                    BindingResult bindingResult , ModelAndView modelAndView) throws IOException {


        if (bindingResult.hasErrors()){
            ProductServiceModel productById = productService.getProductById(id);
            ProductViewModel product = modelMapper.map(productById, ProductViewModel.class);
            modelAndView.addObject("model", product);

            return view("product/edit-product", modelAndView);
        }

        ProductServiceModel productServiceModel = modelMapper.map(editModel, ProductServiceModel.class);

        String img = this.cloudinaryService.uploadImage(editModel.getImage());
        String category = editModel.getCategoryType();

        productService.editById(id, img , category ,  productServiceModel);

        return redirect("/menu/all");
    }


}
