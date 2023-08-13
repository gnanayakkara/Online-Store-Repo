package com.kidletgift.product.repository.product;

import com.kidletgift.product.model.product.ProductDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductDoc,String> {

    /**
     * Find Gift Item by primary key
     * @param itemId
     * @return
     */
    public ProductDoc findByItemId(String itemId);

    /**
     * Find Gift Item by list of itemIds
     * @param itemIds
     * @return
     */
    public List<ProductDoc> findByItemIdIn(List<String> itemIds);

    /**
     * Find Gift Item by item code
     * @param itemCode
     * @return
     */
    public ProductDoc findByItemCode(String itemCode);

}
