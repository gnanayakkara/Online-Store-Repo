package com.kidletgift.inventory.repository.inventory;

import com.kidletgift.inventory.model.inventoryDoc.InventoryDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface InventoryRepository extends MongoRepository<InventoryDoc,String> {

    @Query("{ 'itemName' : { $regex: ?0 } }")
    List<InventoryDoc> findItemByRegexpName(String itemName);

}
