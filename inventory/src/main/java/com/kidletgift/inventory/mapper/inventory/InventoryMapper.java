package com.kidletgift.inventory.mapper.inventory;

import com.kidletgift.inventory.controller.inventoryrestservice.request.InventoryRequest;
import com.kidletgift.inventory.dto.inventory.InventoryDTO;
import com.kidletgift.inventory.model.inventoryDoc.InventoryDoc;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    /**
     * Get InventoryDTO from the Request received in Controller
     * @param inventoryRequest
     * @return
     */
    InventoryDTO modelToDTO(InventoryRequest inventoryRequest);

    /**
     * Get InventoryDoc object to save from InventoryDTO
     * @param inventoryDTO
     * @return
     */
    InventoryDoc dtoToModel(InventoryDTO inventoryDTO);
}
