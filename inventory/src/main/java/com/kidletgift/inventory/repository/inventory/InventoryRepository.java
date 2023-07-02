package com.kidletgift.inventory.repository.inventory;

import com.kidletgift.inventory.model.inventoryDoc.InventoryDoc;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<InventoryDoc,String> {

}
