package com.kidletgift.inventory.mapper.inventory;

import com.kidletgift.inventory.controller.inventoryrestservice.request.InventoryRequest;
import com.kidletgift.inventory.controller.inventoryrestservice.response.GiftItem;
import com.kidletgift.inventory.dto.inventory.InventoryDTO;
import com.kidletgift.inventory.model.inventoryDoc.InventoryDoc;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface InventoryMapper {

    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    /**
     * Get InventoryDTO from the Request received in Controller
     * @param inventoryRequest
     * @return
     */
    InventoryDTO requestToDTO(InventoryRequest inventoryRequest);

    /**
     * Get InventoryDoc object to save from InventoryDTO
     * @param inventoryDTO
     * @return
     */
    InventoryDoc dtoToModel(InventoryDTO inventoryDTO);

    /**
     * Get InventoryDTO object from InventoryDocument
     * @param inventoryDoc
     * @return
     */
    InventoryDTO docToDto(InventoryDoc inventoryDoc);

    /**
     * Get inventoryItem to return to client request
     * @param inventoryDTO
     * @return
     */
    GiftItem dtoToGiftItem(InventoryDTO inventoryDTO);
}
