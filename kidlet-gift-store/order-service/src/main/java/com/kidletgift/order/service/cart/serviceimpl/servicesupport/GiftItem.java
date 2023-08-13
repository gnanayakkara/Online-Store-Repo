package com.kidletgift.order.service.cart.serviceimpl.servicesupport;

import com.kidletgift.product.controller.productrestservice.response.*;
import lombok.Data;

import java.util.List;

@Data
public class GiftItem {

    private String itemId;
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
    private List<GiftItemOffers> itemOffers;
    private String itemColor;

}
