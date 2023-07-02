package com.kidletgift.inventory.service.inventory.serviceimpl;

import com.kidletgift.inventory.dto.inventory.InventoryDTO;
import com.kidletgift.inventory.mapper.inventory.InventoryMapper;
import com.kidletgift.inventory.model.inventoryDoc.InventoryDoc;
import com.kidletgift.inventory.repository.inventory.InventoryRepository;
import com.kidletgift.inventory.repository.inventory.repositoryinterface.CustomInventoryRepository;
import com.kidletgift.inventory.service.inventory.serviceinterface.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false, rollbackFor = Exception.class)
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;

    private CustomInventoryRepository customInventoryRepository;

    private InventoryMapper inventoryMapper;

    @Autowired
    InventoryServiceImpl(InventoryRepository inventoryRepository,InventoryMapper inventoryMapper,
                         CustomInventoryRepository customInventoryRepository){
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
        this.customInventoryRepository = customInventoryRepository;
    }

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

    /**
     * {@inheritDoc}
     * Add the Regex pattern from here to repository
     * Add / to before and after of itemName
     */
    @Override
    public List<InventoryDTO> findItemByRegexName(String itemName) throws Exception {

        return customInventoryRepository.findItemByRegexpName(itemName)
                .stream()
                .map(itemDoc -> inventoryMapper.docToDto(itemDoc))
                .collect(Collectors.toList());
    }
}
