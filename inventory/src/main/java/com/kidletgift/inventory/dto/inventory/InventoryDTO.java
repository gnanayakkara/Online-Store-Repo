package com.kidletgift.inventory.dto.inventory;

import lombok.Data;

import java.util.List;

@Data
public class InventoryDTO {

    private String itemId;
    private String itemCategory;
    private String itemGender;
    private String itemCode;
    private String itemName;
    private Integer itemQuantity;
    private Double itemPrice;
    private List<ItemSubGroupDTO> itemSubGroups;
    private List<String> itemFeatures;
    private AgeCategoryDTO ageCategory;
    private String stockId;
    private List<ItemImagesDTO> itemImages;
    private ItemDimensionDTO itemDimension;
    private List<TechnicalDetailsDTO> technicalDetails;
    private List<ItemDescriptionWithImagesDTO> itemDescriptionWithImages;
    private ItemStatusDTO itemStatus;
    private Integer reorderLevel;

}
