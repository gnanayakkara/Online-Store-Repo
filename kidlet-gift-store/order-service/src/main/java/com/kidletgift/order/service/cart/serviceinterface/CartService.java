package com.kidletgift.order.service.cart.serviceinterface;

import com.kidletgift.order.dto.AddToCartDTO;
import com.kidletgift.order.dto.CartItemDTO;

public interface CartService {

    /**
     * Add Item to cart. If user exists, update DB or update the session cart
     * till user logged in or create an account
     *
     * @param addToCartDTO
     * @return
     */
    public CartItemDTO addItemToCart(AddToCartDTO addToCartDTO) throws Exception;
}
