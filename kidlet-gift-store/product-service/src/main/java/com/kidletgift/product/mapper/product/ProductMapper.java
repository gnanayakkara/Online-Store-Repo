package com.kidletgift.product.mapper.product;

import com.kidletgift.product.controller.productrestservice.request.ProductRequest;
import com.kidletgift.product.controller.productrestservice.response.GiftItem;
import com.kidletgift.product.dto.product.ProductDTO;
import com.kidletgift.product.model.product.ProductDoc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    /**
     * Get InventoryDTO from the Request received in Controller
     * @param productRequest
     * @return
     */
    ProductDTO requestToDTO(ProductRequest productRequest);

    /**
     * Get InventoryDoc object to save from InventoryDTO
     * @param productDTO
     * @return
     */
    ProductDoc dtoToModel(ProductDTO productDTO);

    /**
     * Get InventoryDTO object from InventoryDocument
     * @param productDoc
     * @return
     */
    ProductDTO modelToDto(ProductDoc productDoc);

    /**
     * Get inventoryItem to return to client request
     * @param productDTO
     * @return
     */
    GiftItem dtoToGiftItem(ProductDTO productDTO);

    /**
     * For existing Document update with to be updated values provided by DTO
     * @param productDoc
     * @param productDTO
     */
    void updateModelWithToBeUpdatedValues(@MappingTarget ProductDoc productDoc, ProductDTO productDTO);
}
