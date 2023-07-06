package com.kidletgift.inventory.repository.inventory.repositoryimpl;

import com.kidletgift.inventory.config.MongoDBConnectionFactory;
import com.kidletgift.inventory.model.inventoryDoc.InventoryDoc;
import com.kidletgift.inventory.repository.inventory.repositoryinterface.CustomInventoryRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CustomInventoryRepositoryImpl implements CustomInventoryRepository {

    private final MongoDBConnectionFactory mongoDBConnectionFactory;

    private final MongoTemplate mongoTemplate;

    @Autowired
    CustomInventoryRepositoryImpl(MongoDBConnectionFactory mongoDBConnectionFactory, MongoTemplate mongoTemplate){
        this.mongoDBConnectionFactory = mongoDBConnectionFactory;
        this.mongoTemplate = mongoTemplate;
    }

    public MongoCollection<InventoryDoc> getInventoryCollection() throws Exception {
        return mongoDBConnectionFactory.getMongoDB().getCollection("store_inventory",InventoryDoc.class);
    }

    /**
     * Improve this method as a main search for item search and add aggregation pipeline and limit data
     * {@inheritDoc}
     */
    @Override
    public List<InventoryDoc> findItemByRegexpName(String itemName) throws Exception {

        List<InventoryDoc> inventoryDocList = new ArrayList<>();

        /*Bson filter = Filters.regex("itemName",itemName+".");
        getInventoryCollection().find(filter).into(inventoryDocList);*/

        Query query = new Query();
        query.addCriteria(Criteria.where("itemName").regex(itemName+"."));
        inventoryDocList = mongoTemplate.find(query,InventoryDoc.class);

        return inventoryDocList;
    }

}
