package com.kidletgift.order.repository;

import com.kidletgift.order.model.orderdoc.OrderDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderDoc,String> {

    /**
     * Get the OrderDoc using userId to check if user has records or to add card
     * @param userId
     * @return
     */
    public OrderDoc findByUserId(String userId);
}
