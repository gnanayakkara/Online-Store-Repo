package com.kidletgift.order.repository;

import com.kidletgift.order.model.orderdoc.CartItem;
import com.kidletgift.order.model.orderdoc.OrderDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CartRepository extends MongoRepository<OrderDoc,String> {

    /**
     * Get cart items by userId
     * @param userId
     * @return
     */
    @Query(value="{ 'userId' : ?0 }", fields="{ 'cartItems' : 1}")
    public OrderDoc findCartItemsByUserId(String userId);

}
