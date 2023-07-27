package com.kidletgift.order.repository.repositoryimpl;

import com.kidletgift.order.repository.repositoryinterface.CustomOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomOrderRepostitoyImpl implements CustomOrderRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CustomOrderRepostitoyImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
