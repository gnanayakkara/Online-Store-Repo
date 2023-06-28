package com.kidletgift.inventory.service.inventory.serviceimpl;

import com.kidletgift.inventory.dto.inventory.InventoryDTO;
import com.kidletgift.inventory.mapper.inventory.InventoryMapper;
import com.kidletgift.inventory.model.inventoryDoc.InventoryDoc;
import com.kidletgift.inventory.repository.inventory.InventoryRepository;
import com.kidletgift.inventory.service.inventory.serviceinterface.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryMapper inventoryMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean saveInventoryItem(InventoryDTO inventoryDTO) throws Exception {

        InventoryDoc inventoryDoc = inventoryRepository.save(inventoryMapper.dtoToModel(inventoryDTO));

        if(inventoryDoc.getItemId() != null){
            return true;
        }
        return false;
    }
}
