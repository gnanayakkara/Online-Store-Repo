package com.kidletgift.product.controller.productrestservice.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductRequest {

    private String itemId;
    private String itemCategory;
    private String itemGender;
    @NotNull (message = "Item Code is mandatory")
    private String itemCode;
    private String itemName;
    private Integer itemQuantity;
    private Double itemPrice;
    private List<ItemSubGroup> itemSubGroups;
    private List<String> itemFeatures;
    private AgeCategory ageCategory;
    private String stockId;
    private List<ItemImages> itemImages;
    private ItemDimension itemDimension;
    private List<TechnicalDetails> technicalDetails;
    private List<ItemDescriptionWithImages> itemDescriptionWithImages;
    private Integer reorderLevel;

}
