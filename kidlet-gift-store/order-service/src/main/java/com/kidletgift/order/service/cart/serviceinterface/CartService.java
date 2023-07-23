package com.kidletgift.order.service.cart.serviceinterface;

import com.kidletgift.order.dto.AddToCartDTO;

public interface CartService {

    /**
     * Add Item to cart. If user exists, update DB or update the session cart
     * till user logged in or create an account
     * @param addToCartDTO
     */
    public void addItemToCart(AddToCartDTO addToCartDTO);
}
