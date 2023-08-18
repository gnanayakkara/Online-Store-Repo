package com.kidletgift.order.repository.repositoryinterface;

import com.kidletgift.order.model.order.CartItem;

public interface CustomCartRepository {

    /**
     * If cart not null, add Item to cart
     * @param userId
     * @param cartItem
     * @throws Exception
     */
    public void addItemToCart(String userId, CartItem cartItem) throws Exception;

    /**
     * When user add same item to cart, quantity should update
     *
     * @param userId
     * @param cartItem
     * @param  itemCount
     * @return
     */
    public boolean updateCartItemQty(String userId, CartItem cartItem,Integer itemCount);

    /**
     * Remove item from cart
     *
     * @param userId
     * @param itemId
     * @return
     * @throws Exception
     */
    public Boolean removeItemFromCart(String userId, String itemId) throws Exception;

    /**
     * Update cart item price when it was changed in Product service
     * @param userId
     * @param cartItem
     * @return
     * @throws Exception
     */
    public boolean updateCartItemPrice(String userId, CartItem cartItem) throws Exception;
}
