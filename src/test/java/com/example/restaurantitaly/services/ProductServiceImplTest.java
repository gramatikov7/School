package com.example.restaurantitaly.services;


import com.example.restaurantitaly.domain.entities.Products;
import com.example.restaurantitaly.domain.models.service.ProductServiceModel;
import com.example.restaurantitaly.repositories.ProductRepository;
import com.example.restaurantitaly.services.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    UserService userService;

    @Mock
    CategoryService categoryService;

    @Mock
    ModelMapper modelMapper;

    Products products;
    ProductServiceModel productServiceModel;

    @InjectMocks
    ProductServiceImpl service;



    @Before
    public void initTests() {

        products = new Products();
        products.setName("Cola");
        products.setPrice(10);
        products.setQuantity(100);

        productServiceModel = new ProductServiceModel();



    }


    @Test
    public void getAllProducts_if_all_Is_Okay() {

        Mockito.when(productRepository.findAll()).thenReturn(List.of(products));
        Mockito.when(modelMapper.map(products , ProductServiceModel.class))
                .thenReturn(productServiceModel);


        List<ProductServiceModel> allProducts = service.getAllProducts();


        assertEquals(List.of(productServiceModel) , allProducts);

    }

}