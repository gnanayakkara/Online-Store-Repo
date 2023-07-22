package com.kidletgift.product.repository.product.repositoryimpl;

import com.kidletgift.product.config.MongoDBConnectionFactory;
import com.kidletgift.product.exception.GiftItemSaveOrUpdateException;
import com.kidletgift.product.model.productdoc.ProductDoc;
import com.kidletgift.product.repository.product.repositoryinterface.CustomProductRepository;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final MongoDBConnectionFactory mongoDBConnectionFactory;

    private final MongoTemplate mongoTemplate;

    @Autowired
    CustomProductRepositoryImpl(MongoDBConnectionFactory mongoDBConnectionFactory, MongoTemplate mongoTemplate){
        this.mongoDBConnectionFactory = mongoDBConnectionFactory;
        this.mongoTemplate = mongoTemplate;
    }

    public MongoCollection<ProductDoc> getProductCollection() throws Exception {
        return mongoDBConnectionFactory.getMongoDB().getCollection("store_product", ProductDoc.class);
    }

    /**
     * Improve this method as a main search for item search and add aggregation pipeline and limit data
     * {@inheritDoc}
     */
    @Override
    public List<ProductDoc> findItemByRegexpName(String itemName) throws Exception {

        List<ProductDoc> productDocList = new ArrayList<>();

        /*Bson filter = Filters.regex("itemName",itemName+".");
        getProductCollection().find(filter).into(productDocList);*/

        Query query = new Query();
        query.addCriteria(Criteria.where("itemName").regex(itemName+"."));
        productDocList = mongoTemplate.find(query, ProductDoc.class);

        return productDocList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDoc updateGiftItem(ProductDoc productDoc) throws GiftItemSaveOrUpdateException {

        Query query = new Query().addCriteria(Criteria.where("_id").is(productDoc.getItemId()));
        FindAndReplaceOptions options = new FindAndReplaceOptions().upsert();

        return mongoTemplate.findAndReplace(query, productDoc,options, ProductDoc.class, "store_product", ProductDoc.class);

    }

}
