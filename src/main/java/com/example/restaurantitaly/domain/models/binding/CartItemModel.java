package com.example.restaurantitaly.domain.models.binding;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartItemModel extends BaseBindingModel {

    private double quantity;

    public CartItemModel() {
    }

    @NotNull(message = "Quantity can`t be null")
    @Min(value = 1 , message = "Quantity must be min 1")
    @Max(value = 100 , message = "Quantity must be max 100")
    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
