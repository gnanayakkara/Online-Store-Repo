package com.kidletgift.inventory.service.inventory.serviceinterface;

import com.kidletgift.inventory.dto.inventory.InventoryDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface InventoryService {

    /**
     * Save item to Inventory
     * @param inventoryDTO
     * @return
     * @throws Exception
     */
    public Boolean saveInventoryItem(InventoryDTO inventoryDTO) throws Exception;
}
