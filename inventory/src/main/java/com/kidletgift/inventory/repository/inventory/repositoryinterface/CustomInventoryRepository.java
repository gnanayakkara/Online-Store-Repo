package com.kidletgift.inventory.repository.inventory.repositoryinterface;

import com.kidletgift.inventory.model.inventoryDoc.InventoryDoc;

import java.util.List;

public interface CustomInventoryRepository {

    /**
     * Find items by name.
     * @param itemName
     * @return
     */
    public List<InventoryDoc> findItemByRegexpName(String itemName) throws Exception;

    /**
     * Generic update method to update Gift Item
     * @param inventoryDoc
     * @return
     * @throws Exception
     */
    public InventoryDoc updateGiftItem(InventoryDoc inventoryDoc) throws Exception;

}