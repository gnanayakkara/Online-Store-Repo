package com.kidletgift.order.repository;

import com.kidletgift.order.model.order.OrderDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<OrderDoc,String> {

    /**
     * Get the OrderDoc using userId to check if user has records or to add card
     * @param userId
     * @return
     */
    public Optional<OrderDoc> findByUserId(String userId);
}
