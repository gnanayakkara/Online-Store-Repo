package com.kidletgift.inventory.service.inventory.serviceinterface;

import com.kidletgift.inventory.dto.inventory.InventoryDTO;
import com.kidletgift.inventory.model.inventoryDoc.InventoryDoc;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InventoryService {

    /**
     * Save item to Inventory
     * @param inventoryDTO
     * @return
     * @throws Exception
     */
    public Boolean saveInventoryItem(InventoryDTO inventoryDTO) throws Exception;

    /**
     * Get the item by name. This works as a like query. Using for search item by name
     * @param itemName
     * @return
     * @throws Exception
     */
    public List<InventoryDTO> findItemByRegexName(String itemName) throws Exception;
}
