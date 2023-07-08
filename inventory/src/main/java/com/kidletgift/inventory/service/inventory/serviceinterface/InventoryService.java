package com.kidletgift.inventory.service.inventory.serviceinterface;

import com.kidletgift.inventory.dto.inventory.InventoryDTO;
import com.kidletgift.inventory.exception.GiftItemException;
import com.kidletgift.inventory.exception.GiftItemNotFoundException;
import com.kidletgift.inventory.exception.GiftItemSaveOrUpdateException;
import java.util.List;

public interface InventoryService {

    /**
     * Save item to Inventory
     * @param inventoryDTO
     * @return
     * @throws Exception
     */
    public Boolean saveInventoryItem(InventoryDTO inventoryDTO) throws GiftItemException;

    /**
     * Get the item by name. This works as a like query. Using for search item by name
     * @param itemName
     * @return
     * @throws Exception
     */
    public List<InventoryDTO> findItemByRegexName(String itemName) throws Exception;

    /**
     * Generic update method to update Gift Item
     * @param inventoryDTO
     * @return
     * @throws Exception
     */
    public InventoryDTO updateGiftItem(InventoryDTO inventoryDTO) throws GiftItemException;
}
