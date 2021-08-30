package com.example.restaurantitaly.domain.models.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductModel extends BaseBindingModel {

    private String name;
    private double quantity;
    private double price;
    private String description;
    private String category;
    private MultipartFile image;

    public ProductModel() {

    }

    @NotEmpty(message = "Name can not be empty")
    @Length(min = 4, max = 20 , message = "Name must be between 5 and 20 symblos")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @Min(value = 0 , message = "Price can not be negative.")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NotNull
    @Min(value = 0 , message = "Quantity can not be negative.")
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @NotEmpty(message = "Description can not be negative")
    @Length(min = 5,  message = "Description length must be min 5 symbols")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Image is required")
    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }


    @NotNull(message = "Category is required")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
