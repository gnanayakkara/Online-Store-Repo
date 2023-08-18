package com.kidletgift.order.service.cart.serviceimpl.servicesupport;

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
    private List<GiftItemImages> itemImages;
    private GiftItemStatus itemStatus;
    private List<GiftItemOffers> itemOffers;
    private String itemColor;

}
