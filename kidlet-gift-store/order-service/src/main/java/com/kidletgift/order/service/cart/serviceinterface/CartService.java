package com.kidletgift.order.service.cart.serviceinterface;

import com.kidletgift.order.dto.CartDTO;
import com.kidletgift.order.dto.CartItemDTO;

public interface CartService {

    /**
     * Add Item to cart. If user exists, update DB or update the session cart
     * till user logged in or create an account
     *
     * @param cartDTO
     * @return
     */
    public CartItemDTO addItemToCart(CartDTO cartDTO) throws Exception;

    /**
     * Remove Item from cart
     * @param cartDTO
     * @return
     * @throws Exception
     */
    public Boolean removeItemFromCart(CartDTO cartDTO) throws Exception;
}
