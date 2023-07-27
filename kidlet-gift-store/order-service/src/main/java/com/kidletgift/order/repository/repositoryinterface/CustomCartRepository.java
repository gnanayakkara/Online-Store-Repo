package com.kidletgift.order.repository.repositoryinterface;

import com.kidletgift.order.model.orderdoc.CartItem;

public interface CustomCartRepository {

    /**
     * If cart not null, add Item to cart
     * @param userId
     * @param cartItem
     * @throws Exception
     */
    public void addItemToCart(String userId, CartItem cartItem) throws Exception;

    /**
     * When user add same item to cart, quentity should update
     *
     * @param userId
     * @param cartItem
     * @param  itemCount
     * @return
     */
    public boolean updateCartItem(String userId, CartItem cartItem,Integer itemCount);

    /**
     * Remove item from cart
     *
     * @param userId
     * @param itemId
     * @return
     * @throws Exception
     */
    public Boolean removeItemFromCart(String userId, String itemId) throws Exception;
}
