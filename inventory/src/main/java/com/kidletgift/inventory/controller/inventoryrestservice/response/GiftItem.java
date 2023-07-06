package com.kidletgift.inventory.controller.inventoryrestservice.response;

import lombok.Data;
import java.util.List;

@Data
public class GiftItem {

    private String itemCategory;
    private String itemGender;
    private String itemCode;
    private String itemName;
    private Integer itemQuantity;
    private Double itemPrice;
    private List<GiftItemSubGroup> itemSubGroups;
    private List<String> itemFeatures;
    private GiftAgeCategory ageCategory;
    private List<GiftItemImages> itemImages;
    private GiftItemDimension itemDimension;
    private List<GiftItemTechnicalDetails> technicalDetails;
    private List<GiftItemDescriptionWithImages> itemDescriptionWithImages;
    private GiftItemStatus itemStatus;

}
