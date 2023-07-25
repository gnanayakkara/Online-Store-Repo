package com.kidletgift.order.repository.repositoryimpl;

import com.kidletgift.order.constants.OrderConstant;
import com.kidletgift.order.model.orderdoc.CartItem;
import com.kidletgift.order.model.orderdoc.OrderDoc;
import com.kidletgift.order.repository.repositoryinterface.CustomOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CustomOrderRepostitoyImpl implements CustomOrderRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    CustomOrderRepostitoyImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItemToCart(String userId, CartItem cartItem) throws Exception {

        mongoTemplate.updateFirst(
                Query.query(Criteria.where("_id").is(userId)),
                new Update().push("cartItems", cartItem),
                OrderConstant.ORDER_COLLECTION);
    }
}
