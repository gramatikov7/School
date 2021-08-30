package com.example.restaurantitaly.domain.models.response;

import com.example.restaurantitaly.domain.entities.Category;
import org.springframework.web.multipart.MultipartFile;

public class AddProductResponseModel{

        private String name;
        private double quantity;
        private double price;
        private String description;
        private Category category;
        private MultipartFile image;

    public AddProductResponseModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
