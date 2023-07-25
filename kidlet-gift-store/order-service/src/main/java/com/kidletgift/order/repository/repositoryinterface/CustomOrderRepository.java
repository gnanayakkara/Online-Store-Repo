package com.kidletgift.order.repository.repositoryinterface;

import com.kidletgift.order.model.orderdoc.CartItem;

public interface CustomOrderRepository {

    /**
     * If cart not null, add Item to cart
     * @param userId
     * @param cartItem
     * @throws Exception
     */
    public void addItemToCart(String userId, CartItem cartItem) throws Exception;
}
