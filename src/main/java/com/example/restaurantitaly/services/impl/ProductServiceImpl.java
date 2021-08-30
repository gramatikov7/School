package com.example.restaurantitaly.services.impl;


import com.example.restaurantitaly.domain.entities.Category;
import com.example.restaurantitaly.domain.entities.Products;
import com.example.restaurantitaly.domain.entities.User;
import com.example.restaurantitaly.domain.models.service.CartServiceModel;
import com.example.restaurantitaly.domain.models.service.CategoryServiceModel;
import com.example.restaurantitaly.domain.models.service.ProductServiceModel;
import com.example.restaurantitaly.domain.models.service.UserRegisterServiceModel;
import com.example.restaurantitaly.repositories.ProductRepository;
import com.example.restaurantitaly.services.CategoryService;
import com.example.restaurantitaly.services.ProductService;
import com.example.restaurantitaly.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;
//    private final OrderService orderService;


    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, UserService userService, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userService = userService;
        this.categoryService = categoryService;

    }


    @Override
    public List<ProductServiceModel> getAllProducts() {

        List<Products> products = productRepository.findAll();


        List<ProductServiceModel> collect =
                products
                        .stream()
                        .filter(a -> !a.isDelete())
                        .map(b -> modelMapper.map(b, ProductServiceModel.class))
                        .collect(Collectors.toList());


        return collect;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel , int userId) {

        Products product = modelMapper.map(productServiceModel, Products.class);

        UserRegisterServiceModel userById = userService.getUserById(userId);

        User user = modelMapper.map(userById, User.class);

        product.setUser(user);

        productRepository.save(product);

    }

    @Override
    public List<ProductServiceModel> getProductByName(CategoryServiceModel name) {

        Category category = modelMapper.map(name, Category.class);

        List<Products> allByCategory = productRepository.findAllByCategory(category);

        List<ProductServiceModel> allProducts = allByCategory.stream().map(a -> modelMapper.map(a, ProductServiceModel.class))
                .collect(Collectors.toList());


        return allProducts;
    }

    @Override
    public List<ProductServiceModel> findByCategory(int id) {


        return productRepository.findAllByCategoryId(id)
                .stream()
                .filter(a -> !a.isDelete())
                .map(b -> modelMapper.map(b, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel getProductById(int id) {

        Products byId = productRepository.findById(id);
        ProductServiceModel map = modelMapper.map(byId, ProductServiceModel.class);
        return map;
    }

//    @Override
//    public void deleteProductById(int id) {
////        orderService.delItemById(id);
//        productRepository.deleteById(id);
//    }

    @Override
    public void editById(int id, String img , String category, ProductServiceModel productServiceModel) {

        Products product = productRepository.getById(id);


        CategoryServiceModel categoryByType = categoryService.getCategoryByType(category);


        product.setName(productServiceModel.getName());
        product.setPrice(productServiceModel.getPrice());
        product.setCategory(modelMapper.map(categoryByType , Category.class));
        product.setDescription(productServiceModel.getDescription());
        product.setImageUrl(img);
        product.setQuantity(productServiceModel.getQuantity());

        productRepository.save(product);


    }

    @Override
    public CartServiceModel addItemToCart(ProductServiceModel productServiceModel) {


        return modelMapper.map(productServiceModel, CartServiceModel.class);

    }

    @Override
    public void setProductIsDeleted(int id) {

        Products product = productRepository.getById(id);
        product.setDelete(true);
        productRepository.save(product);
    }
}
