package com.example.restaurantitaly.services;



import com.example.restaurantitaly.domain.models.service.CartServiceModel;
import com.example.restaurantitaly.domain.models.service.CategoryServiceModel;
import com.example.restaurantitaly.domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {

    List<ProductServiceModel> getAllProducts();

    void addProduct(ProductServiceModel productServiceModel , int userId);

    List<ProductServiceModel> getProductByName(CategoryServiceModel categoryServiceModel);

    List<ProductServiceModel> findByCategory(int id);

    ProductServiceModel getProductById(int id);

//    void deleteProductById(int id);

    void editById(int id, String img , String category, ProductServiceModel productServiceModel);

    CartServiceModel addItemToCart(ProductServiceModel productServiceModel);

    void setProductIsDeleted(int id);
}
