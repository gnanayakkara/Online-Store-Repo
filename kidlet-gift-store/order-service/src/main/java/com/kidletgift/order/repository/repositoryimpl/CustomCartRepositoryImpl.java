package com.kidletgift.order.repository.repositoryimpl;

import com.kidletgift.order.constants.OrderConstant;
import com.kidletgift.order.model.orderdoc.CartItem;
import com.kidletgift.order.model.orderdoc.OrderDoc;
import com.kidletgift.order.repository.repositoryinterface.CustomCartRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class CustomCartRepositoryImpl implements CustomCartRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomCartRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItemToCart(String userId, CartItem cartItem) throws Exception {

        mongoTemplate.updateFirst(
                Query.query(Criteria.where("userId").is(userId)),
                new Update().push("cartItems", cartItem),
                OrderConstant.ORDER_COLLECTION);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    public boolean updateCartItem(String userId, CartItem cartItem,Integer itemCount) {

        Query query = new Query();
        query.addCriteria(new Criteria("userId").is(userId));
        query.addCriteria(new Criteria("cartItems.itemId").is(cartItem.getItemId()));

        Update update = new Update().inc("cartItems.$.itemQuantity", itemCount);

        UpdateResult result = mongoTemplate.updateFirst(query, update, OrderDoc.class);
        return result.wasAcknowledged();
    }
}
